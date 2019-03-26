package skywolf46.ServerEdit.Modules.Commands.ItemEditor.Lores;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.CommandAnnotation.Data.CommandData;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;

import java.util.ArrayList;
import java.util.List;

public class ReplaceLoreCommand extends CommandItemModule {
    // /sei lore replace <index> <lore>
    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        if (!CommandChecker.checkItem(arg))
            return;
        Player p = arg.get(Player.class);
        CommandData cda = arg.get(CommandData.class);
        if (cda.length() < 2) {
            p.sendMessage("§7ServerEdit§c | §c" + cda.getCommand() + " " + cda.getRealCommandArgument(0,2) + " <lore index> <lore>");
        } else {
            CommandChecker.parseInteger(cda.getCommandArgument(0), (in) -> {
                ItemStack item = p.getItemInHand();
                ItemMeta meta = item.getItemMeta();
                List<String> lr = meta.hasLore() ? meta.getLore() : new ArrayList<>();
                if (in <= 0) {
                    p.sendMessage("§7ServerEdit§c | §cTarget lore line must be higher than 0.");
                    return;
                } else if (lr.size() < in) {
                    p.sendMessage("§7ServerEdit§c | §cLore line §6" + in + " §cnot exists.");
                    return;
                }
                lr.set(in-1,cda.getCommandArgument(1,cda.length()));
                meta.setLore(lr);
                item.setItemMeta(meta);
                p.sendMessage("§7ServerEdit§c | §aLore replaced.");
            }, ex -> {
                p.sendMessage("§7ServerEdit§c | §cLore index is not number.");
            });

        }
    }
    @Override
    public String getModuleName() {
        return "Command|Item{ReplaceLore}";
    }
}
