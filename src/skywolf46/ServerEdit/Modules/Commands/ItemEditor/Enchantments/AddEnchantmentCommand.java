package skywolf46.ServerEdit.Modules.Commands.ItemEditor.Enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.CommandAnnotation.Data.CommandData;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;
import skywolf46.ServerEdit.Util.EnchantmentReplacer;

public class AddEnchantmentCommand extends CommandItemModule {
    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        if (!CommandChecker.checkItem(arg))
            return;
        Player p = arg.get(Player.class);
        CommandData cda = arg.get(CommandData.class);
        if (cda.length() < 2)
            return;
        Enchantment ench = EnchantmentReplacer.parseEnchantment(cda.getRealCommandArgument(0));
        if(ench == null){
            p.sendMessage("§7ServerEdit§c | §cEnchantment not exists. Type §b" + cda.getCommand() + " " + cda.getRealCommandArgument(0,2) + " list §cto view enchantments.");
            return;
        }
        CommandChecker.parseInteger(cda.getCommandArgument(1),
                (argument) -> {
                    if (argument < 0)
                        p.sendMessage("§7ServerEdit§c | §cEnchantment level must have to higher than -1.");
                    else if (argument == 0) {
                        p.sendMessage("§7ServerEdit§c | §aEnchantment removed.");
                    }
                }, (ex) -> {
                    p.sendMessage("§7ServerEdit§c | §cEnchantment level is not integer.");
                });
    }

    @Override
    public String getModuleName() {
        return "Command|Item{AddEnchantment}";
    }
}
