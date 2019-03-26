package skywolf46.ServerEdit.Modules.Commands.ItemEditor.Lores;

import org.bukkit.entity.Player;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.CommandAnnotation.Data.CommandData;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;

public class LoreHelpCommand extends CommandItemModule {

    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        Player p = arg.get(Player.class);
        CommandData cda = arg.get(CommandData.class);
        p.sendMessage("§cServerEdit §6§l| §9Lore Section");
        p.sendMessage("§7" + cda.getCommand() + " " + cda.getRealCommandArgument(0) + " add <lore>");
        p.sendMessage("§7" + cda.getCommand() + " " + cda.getRealCommandArgument(0) + " remove <lore index>");
        p.sendMessage("§7" + cda.getCommand() + " " + cda.getRealCommandArgument(0) + " replace|rp <lore index> <lore>");
        p.sendMessage("§7" + cda.getCommand() + " " + cda.getRealCommandArgument(0) + " clear");
    }

    @Override
    public String getModuleName() {
        return "Command|Item{LoreHelp}";
    }
}
