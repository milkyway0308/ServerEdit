package skywolf46.ServerEdit.Util.NBTAccesor;

import org.bukkit.Bukkit;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class NBTProcessor {
    private static HashMap<String, Class> nmsCaptured = new HashMap<>();
    private static String nmsVersion;

    private static HashMap<String, Constructor> nmsConstructureCaptured = new HashMap<>();

    static {
        nmsVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            nmsCaptured.put("CraftItemStack",Class.forName("org.bukkit.craftbukkit." + nmsVersion + ".inventory.CraftItemStack"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Class<?> getNMSClass(String name) {
        if (nmsCaptured.containsKey(name)) {
            return nmsCaptured.get(name);
        }

        return nmsCaptured.computeIfAbsent(name, (k) -> {
            try {
                return Class.forName("net.minecraft.server." + nmsVersion + "." + k);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    public static Constructor getNMSConstructure(String name, Class... param) {
        if (nmsConstructureCaptured.containsKey(name))
            return nmsConstructureCaptured.get(name);
        return nmsConstructureCaptured.computeIfAbsent(name, k -> {
            try {
                return getNMSClass(name).getConstructor(param);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    public static Object parseToNBT(int i) {
        try {
            return getNMSConstructure("NBTTagInt", Integer.TYPE).newInstance(i);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object parseToNBT(String s) {
        try {
            return getNMSConstructure("NBTTagString", String.class).newInstance(s);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object parseToNBT(double d) {
        try {
            return getNMSConstructure("NBTTagDouble", Double.TYPE).newInstance(d);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object parseToNBT(long l) {
        try {
            return getNMSConstructure("NBTTagLong", Long.TYPE).newInstance(l);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object createCompound(){
        try {
            return getNMSClass("NBTTagCompound").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object createList(){
        try {
            return getNMSClass("NBTTagList").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
