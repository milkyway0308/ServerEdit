package skywolf46.ServerEdit.Modules.Commands.BookEditor;

import org.bukkit.entity.Player;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.CommandAnnotation.Data.CommandData;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;

public class BookHelpCommand extends CommandItemModule {
    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        Player p = arg.get(Player.class);
        CommandData cda = arg.get(CommandData.class);
        p.sendMessage("§cServerEdit §6§l| §9Book Editor Section");
        p.sendMessage("§7" + cda.getCommand() + " owner|changeowner|co <owner>");
        p.sendMessage("§7" + cda.getCommand() + " edit|editpage|ep <page> <content>");
        p.sendMessage("§7" + cda.getCommand() + " dismantle|dm");
    }

    @Override
    public String getModuleName() {
        return "Command|Book{MainHelp}";
    }
}
