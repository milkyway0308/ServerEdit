package skywolf46.ServerEdit.Modules.Commands.ItemEditor.ItemInfo;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.CommandAnnotation.Data.CommandData;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;

public class ChangeItemCommand extends CommandItemModule {
    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        if (!CommandChecker.checkItem(arg))
            return;
        Player p = arg.get(Player.class);
        CommandData cda = arg.get(CommandData.class);
        if (cda.length() < 1) {
            p.sendMessage("§7ServerEdit§c | §c" + cda.getCommand() + " " + cda.getRealCommandArgument(0, 2) + " <Material|ID>");
        } else {
            CommandChecker.parseInteger(cda.getCommandArgument(0), (inte) -> {
                Material mat = Material.getMaterial(inte);
                if (inte == 0 || mat == null) {
                    p.sendMessage("§7ServerEdit§c | §aMaterial ID §b" + inte + "§c is not exists.");
                } else if (!mat.isItem()) {
                    p.sendMessage("§7ServerEdit§c | §cMaterial §b" + mat.name() + "(ID " + inte + ") §c is not item.");
                } else {
                    p.getItemInHand().setType(mat);
                    p.sendMessage("§7ServerEdit§c | §aItem material has been changed to §b" + mat.name() + "(ID " + inte + ") §a.");

                }
            }, ex -> {
                Material mat = Material.getMaterial(cda.getCommandArgument(0));
                if (mat == null || mat == Material.AIR) {
                    p.sendMessage("§7ServerEdit§c | §aMaterial §b" + cda.getCommandArgument(0) + "§c is not exists.");
                } else if (!mat.isItem()) {
                    p.sendMessage("§7ServerEdit§c | §cMaterial §b" + mat.name() + "(ID " + mat.getId() + ") §c is not item.");
                } else {
                    p.getItemInHand().setType(mat);
                    p.sendMessage("§7ServerEdit§c | §aItem material has been changed to §b" + mat.name() + "(ID " + mat.getId() + ") §a.");
                }
            });


        }
    }

    @Override
    public String getModuleName() {
        return "Command|Item{ChangeItem}";
    }
}
