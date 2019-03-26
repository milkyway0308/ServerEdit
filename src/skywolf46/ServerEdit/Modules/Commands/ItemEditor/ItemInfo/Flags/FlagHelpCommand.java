package skywolf46.ServerEdit.Modules.Commands.ItemEditor.ItemInfo.Flags;

import org.bukkit.entity.Player;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.CommandAnnotation.Data.CommandData;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;

public class FlagHelpCommand extends CommandItemModule {
    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        Player p = arg.get(Player.class);
        CommandData cda = arg.get(CommandData.class);
        p.sendMessage("§cServerEdit §6§l| §9Flag Edit Section");
        p.sendMessage("§7" + cda.getCommand() + " " + cda.getRealCommandArgument(0,2) + " add <flag>");
        p.sendMessage("§7" + cda.getCommand() + " " + cda.getRealCommandArgument(0,2) + " remove <flag>");
        p.sendMessage("§7" + cda.getCommand() + " " + cda.getRealCommandArgument(0,2) + " clear");

        p.sendMessage("§7" + cda.getCommand() + " " + cda.getRealCommandArgument(0,2) + " list|flags|flaglist");
    }

    @Override
    public String getModuleName() {
        return "Command|Item{FlagHelp}";
    }
}
