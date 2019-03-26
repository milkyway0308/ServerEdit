package skywolf46.ServerEdit.Modules.Commands.ItemEditor.ItemInfo;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.CommandAnnotation.Data.CommandData;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;

import java.util.ArrayList;
import java.util.List;

// /sei item clear
public class ClearItemCommand extends CommandItemModule {
    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        if (!CommandChecker.checkItem(arg))
            return;
        Player p = arg.get(Player.class);
        CommandData cda = arg.get(CommandData.class);
        if (cda.length() < 1) {
            p.sendMessage("§7ServerEdit§c | §c" + cda.getCommand() + " " + cda.getRealCommandArgument(0,2));
        } else {
            ItemStack item = p.getItemInHand();
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(null);
            meta.setLore(new ArrayList<>());
            List<Enchantment> enchs = new ArrayList<>(meta.getEnchants().keySet());
            for(Enchantment ench : enchs)
                meta.removeEnchant(ench);
            List<ItemFlag> fl = new ArrayList<>(meta.getItemFlags());
            for(ItemFlag f : fl)
                meta.removeItemFlags(f);
            meta.setUnbreakable(false);
            item.setItemMeta(meta);
            p.sendMessage("§7ServerEdit§c | §aItem data cleared.");
        }
    }

    @Override
    public String getModuleName() {
        return "Command|Item{ClearItem}";
    }
}
