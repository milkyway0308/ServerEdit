package skywolf46.ServerEdit.Modules.CraftScript.Commands;

import org.bukkit.command.CommandSender;
import skywolf46.CommandAnnotation.API.MinecraftAbstractCommand;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.ServerEdit.Modules.CraftScript.ScriptStorage;
import skywolf46.ServerEdit.Modules.CraftScript.System.ScriptStarter.ScriptThreadManager;

public class ReloadScriptCommand extends MinecraftAbstractCommand {
    @Override
    public boolean onCommand(CommandArgument commandArgument) {
        CommandSender cs = commandArgument.get(CommandSender.class);
        ScriptStorage.reload(cs);
        return false;
    }

    @Override
    public int getCommandPriority() {
        return 0;
    }
}
