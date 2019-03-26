package skywolf46.ServerEdit.Modules.Commands.ItemEditor.Attribute;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.CommandAnnotation.Data.CommandData;
import skywolf46.ServerEdit.Abstract.CommandItemModule;
import skywolf46.ServerEdit.Util.CommandChecker;
import skywolf46.ServerEdit.Util.NBTAccesor.ItemStackTagAccessor;
import skywolf46.ServerEdit.Util.NBTAccesor.NBTCompoundAccessor;

import java.util.UUID;

public class DamageAttributeCommand extends CommandItemModule {
    @Override
    public void onCommandType(CommandArgument arg) {
        if (!CommandChecker.checkPermission(arg) || !CommandChecker.checkPlayerOnly(arg))
            return;
        if (!CommandChecker.checkItem(arg))
            return;
        Player p = arg.get(Player.class);
        CommandData cda = arg.get(CommandData.class);
        if (cda.length() < 1) {
            p.sendMessage("§7ServerEdit§c | §c" + cda.getCommand() + " " + cda.getRealCommandArgument(0) + " <slot> <damage>");
        } else {
            CommandChecker.parseInteger(cda.getCommandArgument(0), (in) -> {
                ItemStack item = p.getItemInHand();
                if (in <= -1) {
                    p.sendMessage("§7ServerEdit§c | §cTarget attribute data must have to higher than 0.");
                    return;
                }
                ItemStackTagAccessor tag = new ItemStackTagAccessor(item);
                UUID uid = UUID.randomUUID();
                NBTCompoundAccessor accessor = new NBTCompoundAccessor();
                accessor.convertAndPutNBTData("AttributeName","generic.attackDamage");
                accessor.convertAndPutNBTData("Name","generic.atkatk");
                accessor.convertAndPutNBTData("Amount",in);
                accessor.convertAndPutNBTData("Operation",0);
                accessor.convertAndPutNBTData("UUIDLeast",uid.getLeastSignificantBits());
                accessor.convertAndPutNBTData("UUIDMost",uid.getMostSignificantBits());
                accessor.convertAndPutNBTData("Slot","mainhand");
                tag.addModifier(accessor);
                accessor = new NBTCompoundAccessor();
                accessor.convertAndPutNBTData("AttributeName","generic.attackDamage");
                accessor.convertAndPutNBTData("Name","generic.noiamnot");
                accessor.convertAndPutNBTData("Amount",in);
                accessor.convertAndPutNBTData("Operation",0);
                accessor.convertAndPutNBTData("UUIDLeast",uid.getLeastSignificantBits());
                accessor.convertAndPutNBTData("UUIDMost",uid.getMostSignificantBits());
                accessor.convertAndPutNBTData("Slot","feet");
                tag.addModifier(accessor);
                accessor = new NBTCompoundAccessor();
                uid = UUID.randomUUID();
                accessor.convertAndPutNBTData("AttributeName","generic.armor");
                accessor.convertAndPutNBTData("Name","generic.yesim");
                accessor.convertAndPutNBTData("Amount",41);
                accessor.convertAndPutNBTData("Operation",0);
                accessor.convertAndPutNBTData("UUIDLeast",uid.getLeastSignificantBits());
                accessor.convertAndPutNBTData("UUIDMost",uid.getMostSignificantBits());
                accessor.convertAndPutNBTData("Slot","offhand");
                tag.addModifier(accessor);
                p.setItemInHand(tag.getFixedItemStack());
                p.sendMessage("§7ServerEdit§c | §aLore replaced.");
            }, ex -> {
                ex.printStackTrace();
                p.sendMessage("§7ServerEdit§c | §cLore index is not number.");
            });

        }
    }

    @Override
    public String getModuleName() {
        return "Command|Item{DamageAttribute}";
    }
}
