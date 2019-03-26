package skywolf46.ServerEdit.Modules.Commands.ItemEditor.ItemInfo.Flags;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.CommandAnnotation.Data.CommandData;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;

public class AddFlagCommand extends CommandItemModule {
    // /sei lore add <lore>
    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        if (!CommandChecker.checkItem(arg))
            return;
        Player p = arg.get(Player.class);
        CommandData cda = arg.get(CommandData.class);
        if (cda.length() < 1) {
            p.sendMessage("§7ServerEdit§c | §c" + cda.getCommand() + " " + cda.getRealCommandArgument(0,3) + " <flag name>");
        } else {
            try {
                ItemFlag flag = ItemFlag.valueOf(cda.getCommandArgument(0).toUpperCase());
                ItemStack item = p.getItemInHand();
                ItemMeta meta = item.getItemMeta();
                meta.addItemFlags(flag);
                item.setItemMeta(meta);
                p.sendMessage("§7ServerEdit§c | §aItemFlag added.");
            }catch (Exception ex){
                p.sendMessage("§7ServerEdit§c | §cFlag not exists. Use §b" + cda.getCommand() + " " + cda.getRealCommandArgument(0,2) + " flags §cto view all flags.");
            }

        }
    }

    @Override
    public String getModuleName() {
        return "Command|Item{AddFlag}";
    }
}