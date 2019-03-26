package skywolf46.ServerEdit.Modules.Commands.ItemEditor.ItemInfo;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.CommandAnnotation.Data.CommandData;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;

public class SetUnbreakableCommand extends CommandItemModule {
    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        if (!CommandChecker.checkItem(arg))
            return;
        Player p = arg.get(Player.class);
        CommandData cda = arg.get(CommandData.class);
        if (cda.length() < 1) {
            p.sendMessage("§7ServerEdit§c | §c" + cda.getCommand() + " " + cda.getRealCommandArgument(0, 2) + "<true/false>");
        } else {
            ItemStack item = p.getItemInHand();
            ItemMeta meta = item.getItemMeta();
            CommandChecker.parseBoolean(cda.getCommandArgument(0),
                    inte -> {
                        meta.setUnbreakable(inte);
                        item.setItemMeta(meta);
                        if(inte)
                            p.sendMessage("§7ServerEdit§c | §aUnbreakable ability applied.");
                        else
                            p.sendMessage("§7ServerEdit§c | §aUnbreakable ability released.");
                    }, ex -> {
                        p.sendMessage("§7ServerEdit§c | §cUnbreakable status is not boolean.");
                    });

        }
    }

    // Developer's bizzare project : Bug is UNBREAKABLE
    @Override
    public String getModuleName() {
        return "Command|Item{SetUnbreakable}";
    }
}
