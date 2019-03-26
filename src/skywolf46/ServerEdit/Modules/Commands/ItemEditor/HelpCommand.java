package skywolf46.ServerEdit.Modules.Commands.ItemEditor;

import org.bukkit.entity.Player;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.CommandAnnotation.Data.CommandData;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;

public class HelpCommand extends CommandItemModule {
    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        Player p = arg.get(Player.class);
        CommandData cda = arg.get(CommandData.class);
        p.sendMessage("§cServerEdit §6§l| §9Item Editor Section");
        p.sendMessage("§7" + cda.getCommand() + " name|display <itemName>");
        p.sendMessage("§7" + cda.getCommand() + " item|it");
        p.sendMessage("§7" + cda.getCommand() + " lore|lr");
    }

    @Override
    public String getModuleName() {
        return "Command|Item{HelpCommand}";
    }
}
