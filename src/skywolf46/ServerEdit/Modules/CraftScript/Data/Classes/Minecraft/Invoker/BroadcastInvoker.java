package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Minecraft.Invoker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.InConditionHelper;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Invokers.InvokerScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.BooleanClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.StringClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

import java.util.ArrayList;
import java.util.List;

public class BroadcastInvoker extends InvokerScriptClass {
    @Override
    protected void apply(CompileStatus cl, int index) {
        if (cl.length() == 1)
            throw new IllegalStateException("[CraftScript|Compile Error] Broadcast invoker need 1 or more argument");
    }


    @Override
    public String getClassName() {
        return "BroadcastInvoker";
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        StringBuilder sb = new StringBuilder();
        List<World> worlds = new ArrayList<>();
        boolean isWorldParam = false;
        for (int i = 1; i < st.length(); i++) {
            CraftScriptClass cls = st.get(i);
            if (cls instanceof InConditionHelper) {
                if (i == 1)
                    throw new IllegalStateException("[CraftScript|Runtime Error] Detected no-parameter broadcast");
                isWorldParam = true;
                continue;
            }
            if (isWorldParam) {
                if (cls instanceof StringClass) {
                    World w = Bukkit.getWorld(cls.toString());
                    if (w != null)
                        worlds.add(w);
                }
            } else
                sb.append(cls.toString())
                        .append(" ");
        }
        String text = sb.substring(0, sb.length() - 1);
        text = ChatColor.translateAlternateColorCodes('&', text);
        if (isWorldParam) {
            if (worlds.size() > 0) {
                for (World w : worlds)
                    for (Player p : w.getPlayers())
                        p.sendMessage(text);
                System.out.println(text);
                return BooleanClass.TRUE;
            }
            return BooleanClass.FALSE;
        } else
            Bukkit.broadcastMessage(text);
        return BooleanClass.TRUE;
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s, i, c) -> {
            if (s.equalsIgnoreCase("broadcast"))
                return new BroadcastInvoker();
            return null;
        };
    }
}
