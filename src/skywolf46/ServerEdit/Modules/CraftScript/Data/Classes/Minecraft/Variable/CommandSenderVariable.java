package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Minecraft.Variable;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Minecraft.Abstract.MinecraftVariable;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.StringClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

import java.util.concurrent.atomic.AtomicBoolean;

public class CommandSenderVariable extends MinecraftVariable {
    private CommandSender c;
    private static AtomicBoolean init = new AtomicBoolean(false);

    public CommandSenderVariable(CommandSender cs) {
        if (!init.get()) {
            register((fromTo, c) -> {
                if(fromTo[0].equalsIgnoreCase("player"))
                    return new PlayerVariable((Player) ((CommandSenderVariable)c).getSender());
                else if(fromTo[1].equalsIgnoreCase("string"))
                    return new StringClass(((CommandSenderVariable)c).getSender().getName());
                return null;
            }, "Player", "String");
        }
        this.c = cs;
    }

    @Override
    public String getClassName() {
        return "CommandSender";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {

    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s, i, c) -> null;
    }

    public CommandSender getSender() {
        return c;
    }
}
