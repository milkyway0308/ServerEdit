package skywolf46.ServerEdit.Util.NBTAccesor;

import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ItemStackTagAccessor extends AbstractNBTAccessor {
    private Object craftedItemStack;
    private NBTCompoundAccessor accessor;
    private NBTListAccessor modifier;
    private static Method CACHED_HASTAG;
    private static Method CACHED_GETTAG;
    private static Method CACHED_SETTAG;
    private static Method CACHED_ASNMSCOPY;
    private static Method CACHED_ASBUKKITCOPY;

    static {
        try {
            Class TARGET = NBTProcessor.getNMSClass("ItemStack");
            CACHED_HASTAG = TARGET.getMethod("hasTag");
            CACHED_GETTAG = TARGET.getMethod("getTag");
            CACHED_SETTAG = TARGET.getMethod("setTag", NBTProcessor.getNMSClass("NBTTagCompound"));
            CACHED_ASNMSCOPY = NBTProcessor.getNMSClass("CraftItemStack").getMethod("asNMSCopy", ItemStack.class);
            CACHED_ASBUKKITCOPY = NBTProcessor.getNMSClass("CraftItemStack").getMethod("asBukkitCopy", NBTProcessor.getNMSClass("ItemStack"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ItemStackTagAccessor(ItemStack n) {
        try {
            this.craftedItemStack = CACHED_ASNMSCOPY.invoke(null, n);
            if ((boolean) CACHED_HASTAG.invoke(this.craftedItemStack))
                accessor = new NBTCompoundAccessor(CACHED_GETTAG.invoke(this.craftedItemStack));
            else
                accessor = new NBTCompoundAccessor();
            if (accessor.hasNBTData("AttributeModifiers"))
                modifier = new NBTListAccessor(accessor.getNBTData("AttributeModifiers"));
            else
                modifier = new NBTListAccessor();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public NBTCompoundAccessor getCompoundAccessor() {
        return accessor;
    }

    public NBTListAccessor getModifier() {
        return modifier;
    }

    public void addModifier(NBTCompoundAccessor ac) {
        modifier.iterate((k) -> {
            NBTCompoundAccessor nc = new NBTCompoundAccessor(k);
//            System.out.println(!ac.getNBTData("AttributeName").equals(nc.getNBTData("AttributeName"))
//                    || !ac.getNBTData("Name").equals(nc.getNBTData("Name")));
//            System.out.println(ac.getNBTData("AttributeName"));
//            System.out.println(nc.getNBTData("AttributeName"));
//            System.out.println(ac.getNBTData("Name"));
//            System.out.println(nc.getNBTData("Name"));
            return ac.getNBTData("AttributeName").equals(nc.getNBTData("AttributeName"))
                    || ac.getNBTData("Name").equals(nc.getNBTData("Name"));
        });
        modifier.add(ac.getNBT());
    }

    public ItemStack getFixedItemStack() {
        try {
            accessor.putNBTData("AttributeModifiers",modifier.getNBT());
            CACHED_SETTAG.invoke(craftedItemStack,accessor.getNBT());
            return (ItemStack) CACHED_ASBUKKITCOPY.invoke(null,craftedItemStack);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
