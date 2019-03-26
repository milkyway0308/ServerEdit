package skywolf46.ServerEdit.Modules.Commands.BookEditor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.CommandAnnotation.Data.CommandData;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;

public class DismantleBookCommand extends CommandItemModule {
    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        if (!CommandChecker.checkItem(arg))
            return;
        Player p = arg.get(Player.class);
        CommandData cda = arg.get(CommandData.class);
        ItemStack hand = p.getItemInHand();
        if(hand.getType() != Material.WRITTEN_BOOK)
            p.sendMessage("§7ServerEdit§c | §cMust have to hold a written book item.");
        else{
            BookMeta bm = (BookMeta) hand.getItemMeta();
            bm.setTitle(null);
            bm.setAuthor(null);
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            book.setItemMeta(bm);
            p.setItemInHand(hand);
            p.sendMessage("§7ServerEdit§c | §aBook dismantled.");
        }
    }

    @Override
    public String getModuleName() {
        return "Command|Book{Dismantle}";
    }
}
