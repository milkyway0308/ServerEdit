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

public class ChangeBookOwnerCommand extends CommandItemModule {
    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        if (!CommandChecker.checkItem(arg))
            return;
        Player p = arg.get(Player.class);
        CommandData cda = arg.get(CommandData.class);

        if (cda.length() < 1){
            p.sendMessage("§7ServerEdit§c | §c" + cda.getCommand() + " " + cda.getRealCommandArgument(0) + " <owner>");
        }else{
            ItemStack item = p.getItemInHand();
            ItemMeta meta = item.getItemMeta();
            if(!(meta instanceof BookMeta)){
                p.sendMessage("§7ServerEdit§c | §cMust have to hold book type item.");
                return;
            }
            BookMeta bm = (BookMeta) meta;
            bm.setAuthor(ChatColor.translateAlternateColorCodes('&',cda.getCommandArgument(0,cda.length())) + "§7");
            item.setItemMeta(bm);
            p.sendMessage("§7ServerEdit§c | §aBook owner changed.");
        }
    }

    @Override
    public String getModuleName() {
        return "Command|Book{ChangeOwner}";
    }
}
