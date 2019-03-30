package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Minecraft.Invoker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.ToVariableConnectCondition;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Minecraft.Abstract.MinecraftInvoker;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Minecraft.Variable.PlayerVariable;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.IntegerClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.StringClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Data.IterateableScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SendMessageInvoker extends MinecraftInvoker {
    @Override
    protected void apply(CompileStatus cl, int index) {
        if (cl.length() == 1)
            throw new IllegalStateException("[CraftScript|Compile Error] SendMessage invoker need 1 or more argument");
    }


    @Override
    public String getClassName() {
        return "SendMessage";
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        StringBuilder sb = new StringBuilder();
        boolean isPlayerParam = false;
        List<Player> target = new ArrayList<>();
        for (int i = 1; i < st.length(); i++) {
            CraftScriptClass cls = st.get(i);
            if (cls instanceof ToVariableConnectCondition) {
                if (i == 1)
                    throw new IllegalStateException("[CraftScript|Runtime Error] Detected no-parameter sendMessage");
                isPlayerParam = true;
                continue;
            }
            if (isPlayerParam) {
                if (cls instanceof IterateableScriptClass) {
                    IterateableScriptClass iter = (IterateableScriptClass) cls;
                    if (iter.isTypeOf(StringClass.class)) {
                        Iterator<StringClass> it = iter.getIterator();
                        while (it.hasNext()) {
                            StringClass cl = it.next();
                            Player p = Bukkit.getPlayerExact(cl.toString());
                            if (p != null)
                                target.add(p);
                        }
                    }else if(iter.isTypeOf(PlayerVariable.class)){
                        Iterator<PlayerVariable> it = iter.getIterator();
                        while (it.hasNext()) {
                            PlayerVariable cl = it.next();
                            if (cl.getPlayer().isOnline())
                                target.add(cl.getPlayer());
                        }
                    }
                } else if (cls instanceof StringClass) {
                    Player p = Bukkit.getPlayerExact(cls.toString());
                    if (p != null)
                        target.add(p);
                }else if(cls instanceof PlayerVariable){
                    target.add(((PlayerVariable) cls).getPlayer());
                }
            } else
                sb.append(cls.toString())
                        .append(" ");
        }
        String text = sb.substring(0, sb.length() - 1);
        text = ChatColor.translateAlternateColorCodes('&', text);
        for (Player p : target)
            p.sendMessage(text);
        return new IntegerClass(target.size());
    }
    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s, i, c) -> {
            if (s.equalsIgnoreCase("whisper"))
                return new SendMessageInvoker();
            return null;
        };
    }
}
