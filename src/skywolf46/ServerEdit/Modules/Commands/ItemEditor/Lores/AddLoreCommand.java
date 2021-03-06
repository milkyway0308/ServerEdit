package skywolf46.ServerEdit.Modules.Commands.ItemEditor.Lores;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.CommandAnnotation.Data.CommandData;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;

import java.util.ArrayList;
import java.util.List;

public class AddLoreCommand extends CommandItemModule {
    // /sei lore add <lore>
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
            List<String> lr = meta.hasLore() ? meta.getLore() : new ArrayList<>();
            lr.add(ChatColor.translateAlternateColorCodes('&',cda.getCommandArgument(0,cda.length())));
            meta.setLore(lr);
            item.setItemMeta(meta);
            p.sendMessage("§7ServerEdit§c | §aLore added.");
        }
    }
    @Override
    public String getModuleName() {
        return "Command|Item{AddLore}";
    }
}
