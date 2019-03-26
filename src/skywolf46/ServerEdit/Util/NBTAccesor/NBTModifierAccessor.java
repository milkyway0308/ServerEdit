package skywolf46.ServerEdit.Util.NBTAccesor;

import com.google.common.primitives.Primitives;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static skywolf46.ServerEdit.Util.NBTAccesor.NBTProcessor.getNMSClass;
public class NBTModifierAccessor {


    private Object compound;

    public NBTModifierAccessor(){
        try {
            compound = getNMSClass("NBTTagCompound").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void addValue(String k,String val){
        try {
            getNMSClass("NBTTagCompound").getMethod("setString",String.class,String.class)
                    .invoke(compound,k,val);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void addValue(String k,int val){
        try {

            getNMSClass("NBTTagCompound").getMethod("setInt",String.class,Integer.TYPE)
                    .invoke(compound,k,val);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void addValue(String k,double val){
        try {
            getNMSClass("NBTTagCompound").getMethod("setDouble",String.class,Double.TYPE)
                    .invoke(compound,k,val);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public ItemStack applyTo(ItemStack n){
        try {
            Object o = getNMSClass("CraftItemStack").getMethod("asNMSCopy",ItemStack.class)
                    .invoke(null,n);
            Object compound;
            if((boolean)o.getClass().getMethod("hasTag").invoke(o)){
                compound = o.getClass().getMethod("getTag").invoke(o);
            }else{
                compound = getNMSClass("NBTTagCompound").newInstance();
            }
            Object l = getNMSClass("NBTTagList").newInstance();
            l.getClass().getMethod("add",getNMSClass("NBTBase"))
                    .invoke(l,this.compound);
            compound.getClass().getMethod("set",String.class,getNMSClass("NBTBase"))
                    .invoke(compound,"AttributeModifiers",l);
            o.getClass().getMethod("setTag",getNMSClass("NBTTagCompound"))
                    .invoke(o,compound);

            return (ItemStack) getNMSClass("CraftItemStack").getMethod("asBukkitCopy",getNMSClass("ItemStack"))
                    .invoke(null,o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return new ItemStack(Material.AIR);
    }
}
