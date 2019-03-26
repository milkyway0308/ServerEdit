package skywolf46.ServerEdit.Util.NBTAccesor;

import net.minecraft.server.v1_12_R1.NBTTagCompound;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class NBTCompoundAccessor extends AbstractNBTAccessor {
    private static Class CACHED_CONSTRUCTURE = NBTProcessor.getNMSClass("NBTTagCompound");
    private static Field CACHED_MAP_FIELD;

    static {
        try {
            CACHED_MAP_FIELD = CACHED_CONSTRUCTURE.getDeclaredField("map");
            CACHED_MAP_FIELD.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, Object> map;

    public NBTCompoundAccessor() {
        try {
            super.setNBT(CACHED_CONSTRUCTURE.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            map = (HashMap<String, Object>) CACHED_MAP_FIELD.get(getNBT());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public NBTCompoundAccessor(Object o) {
        super.setNBT(o);
        try {
            map = (HashMap<String, Object>) CACHED_MAP_FIELD.get(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void putNBTData(String n, Object o) {
        map.put(n, o);
    }

    public void convertAndPutNBTData(String n, int o) {
        map.put(n, NBTProcessor.parseToNBT(o));
    }

    public void convertAndPutNBTData(String n, String s) {
        map.put(n, NBTProcessor.parseToNBT(s));
    }

    public void convertAndPutNBTData(String n, double d) {
        map.put(n, NBTProcessor.parseToNBT(d));
    }

    public void convertAndPutNBTData(String n, long l) {
        map.put(n, NBTProcessor.parseToNBT(l));
    }

    public Object getNBTData(String n) {
        return map.get(n);
    }

    public boolean hasNBTData(String n) {
        return map.containsKey(n);
    }

    public void iterate(Function<Map.Entry<String, Object>, Boolean> funct) {
        List<Map.Entry<String, Object>> list = new ArrayList<>(map.entrySet());
        for (Map.Entry<String, Object> a : list)
            if (funct.apply(a))
                map.remove(a.getKey());
    }
}
