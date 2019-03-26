package skywolf46.ServerEdit.Modules.Commands.ItemEditor.ItemInfo.Flags;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.CommandAnnotation.Data.CommandData;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;

import java.util.ArrayList;

public class ClearFlagCommand extends CommandItemModule {
    // /sei lore add <lore>
    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        if (!CommandChecker.checkItem(arg))
            return;
        Player p = arg.get(Player.class);
        CommandData cda = arg.get(CommandData.class);
        ItemStack item = p.getItemInHand();
        ItemMeta meta = item.getItemMeta();
        for (ItemFlag fl : new ArrayList<>(meta.getItemFlags()))
            meta.removeItemFlags(fl);
        item.setItemMeta(meta);
        p.sendMessage("§7ServerEdit§c | §aItemFlag cleared.");
    }

    @Override
    public String getModuleName() {
        return "Command|Item{ClearFlag}";
    }
}