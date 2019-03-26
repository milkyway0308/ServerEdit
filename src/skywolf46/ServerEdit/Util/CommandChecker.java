package skywolf46.ServerEdit.Util;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import skywolf46.CommandAnnotation.Data.CommandArgument;

import java.util.function.Consumer;

public class CommandChecker {
    public static boolean checkPlayerOnly(CommandArgument arg) {
        CommandSender cs = arg.get(CommandSender.class);
        Player p = arg.get(Player.class);
        if (p == null) {
            cs.sendMessage("§7ServerEdit§c | §cPlayer only command!");
            return false;
        }
        return true;
    }

    public static boolean checkPermission(CommandArgument arg) {
        CommandSender cs = arg.get(CommandSender.class);
        if (!cs.hasPermission("serveredit.admin") && !cs.isOp()) {
            cs.sendMessage("§7ServerEdit§c | §cPermission denied!");
            return false;
        }
        return true;
    }

    public static boolean checkItem(CommandArgument arg) {
        Player p = arg.get(Player.class);
        ItemStack hand = p.getItemInHand();
        if (hand == null || hand.getType() == Material.AIR) {
            p.sendMessage("§7ServerEdit§c | §cMust have to hold item.");
            return false;
        }
        return true;
    }

    public static void parseInteger(String command, Consumer<Integer> funct, Consumer<Exception> except) {
        try {
            funct.accept(Integer.parseInt(command));
        } catch (Exception ex) {
            except.accept(ex);
        }
    }

    public static void parseBoolean(String command, Consumer<Boolean> funct, Consumer<Exception> except) {
        try {
            if (command.equalsIgnoreCase("true"))
                funct.accept(true);
            else if(command.equalsIgnoreCase("false"))
                funct.accept(false);
            else
                throw new Exception();
        } catch (Exception ex) {
            except.accept(ex);
        }
    }
}
