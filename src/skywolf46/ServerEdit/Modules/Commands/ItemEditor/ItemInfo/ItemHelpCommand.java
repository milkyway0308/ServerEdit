package skywolf46.ServerEdit.Modules.Commands.ItemEditor.ItemInfo;

import org.bukkit.entity.Player;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.CommandAnnotation.Data.CommandData;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;

public class ItemHelpCommand extends CommandItemModule {
    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        Player p = arg.get(Player.class);
        CommandData cda = arg.get(CommandData.class);
        p.sendMessage("§cServerEdit §6§l| §9Item Info Edit Section");
        p.sendMessage("§7" + cda.getCommand() + " " + cda.getRealCommandArgument(0) + " itemchange|change|ic <Material|ID>");
        p.sendMessage("§7" + cda.getCommand() + " " + cda.getRealCommandArgument(0) + " unbreakable|unbreak|ub <true/false>");

        p.sendMessage("§7" + cda.getCommand() + " " + cda.getRealCommandArgument(0) + " clear|cl");
        p.sendMessage("§7" + cda.getCommand() + " " + cda.getRealCommandArgument(0) + " flag|fl");
   }
    @Override
    public String getModuleName() {
        return "Command|Item{ItemHelp}";
    }
}
