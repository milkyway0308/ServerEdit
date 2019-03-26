package skywolf46.ServerEdit.Modules.Commands.ItemEditor.Display;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.CommandAnnotation.Data.CommandData;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;

public class SetDisplayCommand extends CommandItemModule {
    // /sei name <name>
    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        if (!CommandChecker.checkItem(arg))
            return;
        Player p = arg.get(Player.class);
        CommandData cda = arg.get(CommandData.class);
        if (cda.length() < 1){
            p.sendMessage("§7ServerEdit§c | §c" + cda.getCommand() + " " + cda.getRealCommandArgument(0) + " <lore>");
        }else{
            ItemStack item = p.getItemInHand();
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',cda.getCommandArgument(0,cda.length())));
            item.setItemMeta(meta);
            p.sendMessage("§7ServerEdit§c | §aItem name changed.");
        }

    }

    @Override
    public String getModuleName() {
        return "Command|Item{SetDisplay}";
    }
}
