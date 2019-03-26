package skywolf46.ServerEdit.Modules.Commands.BookEditor;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.CommandAnnotation.Data.CommandData;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;

public class EditPageContentCommand extends CommandItemModule {
    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        if (!CommandChecker.checkItem(arg))
            return;
        Player p = arg.get(Player.class);
        CommandData cda = arg.get(CommandData.class);

        if (cda.length() < 1) {
            p.sendMessage("§7ServerEdit§c | §c" + cda.getCommand() + " " + cda.getRealCommandArgument(0) + " <page> <content>");
        } else {
            ItemStack item = p.getItemInHand();
            ItemMeta meta = item.getItemMeta();
            if (!(meta instanceof BookMeta)) {
                p.sendMessage("§7ServerEdit§c | §cMust have to hold book type item.");
                return;
            }
            CommandChecker.parseInteger(cda.getCommandArgument(0), (in) -> {
                BookMeta bm = (BookMeta) meta;
                if (in <= 0)
                    p.sendMessage("§7ServerEdit§c | §cPage index must have to higher than 0.");
                else if (in > bm.getPageCount())
                    p.sendMessage("§7ServerEdit§c | §cPage index must have to lower than page max index. Current book pages: §b" + bm.getPageCount());
                else {
                    bm.setPage(in-1, ChatColor.translateAlternateColorCodes('&', cda.getCommandArgument(1, cda.length())));
                    item.setItemMeta(bm);
                    p.sendMessage("§7ServerEdit§c | §aBook content of page of §b" + in + "§a changed.");
                }
            }, ex -> {
                p.sendMessage("§7ServerEdit§c | §cPage index is not integer.");
            });
        }
    }

    @Override
    public String getModuleName() {
        return "Command|Book{EditPageContent}";
    }
}
