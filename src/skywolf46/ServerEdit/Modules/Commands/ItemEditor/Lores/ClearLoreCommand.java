package skywolf46.ServerEdit.Modules.Commands.ItemEditor.Lores;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.CommandAnnotation.Data.CommandData;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;

import java.util.ArrayList;

public class ClearLoreCommand extends CommandItemModule {
    // /sei lore clear
    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        if (!CommandChecker.checkItem(arg))
            return;
        Player p = arg.get(Player.class);
        CommandData cda = arg.get(CommandData.class);
        if (cda.length() < 1){
            p.sendMessage("§7ServerEdit§c | §c" + cda.getCommand() + " " + cda.getRealCommandArgument(0,2) + " <lore>");
        }else{
            ItemStack item = p.getItemInHand();
            ItemMeta meta = item.getItemMeta();
            meta.setLore(new ArrayList<>());
            item.setItemMeta(meta);
            p.sendMessage("§7ServerEdit§c | §aLore cleared.");
        }
    }
    @Override
    public String getModuleName() {
        return "Command|Item{ClearLore}";
    }
}
