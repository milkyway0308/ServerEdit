package skywolf46.ServerEdit.Modules.Commands.ItemEditor.ItemInfo.Flags;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;

public class FlagListCommand extends CommandItemModule {
    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        if (!CommandChecker.checkItem(arg))
            return;
        Player p = arg.get(Player.class);
        p.sendMessage("§cServerEdit §6§l| §9Flag list");
        for(ItemFlag fl : ItemFlag.values())
            p.sendMessage("§7- §6" + fl.toString());

    }

    @Override
    public String getModuleName() {
        return "Command|Item{FlagList}";
    }
}
