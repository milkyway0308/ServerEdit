package skywolf46.ServerEdit.Util.NBTAccesor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;

import static skywolf46.ServerEdit.Util.NBTAccesor.NBTProcessor.getNMSClass;
public class NBTListAccessor extends AbstractNBTAccessor {
    private static Class<?> CACHED_CONSTRUCTURE;
    private static Field CACHED_LIST_FIELD;
    private List<Object> tagList;

    static {
        try {
            CACHED_CONSTRUCTURE = getNMSClass("NBTTagList");
            CACHED_LIST_FIELD = CACHED_CONSTRUCTURE.getDeclaredField("list");
            CACHED_LIST_FIELD.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public NBTListAccessor(){
        try {
            super.setNBT(CACHED_CONSTRUCTURE.newInstance());
            tagList = (List<Object>) CACHED_LIST_FIELD.get(getNBT());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public NBTListAccessor(Object nbttaglist){
        super.setNBT(nbttaglist);
        try {
            tagList = (List<Object>) CACHED_LIST_FIELD.get(getNBT());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    public void add(Object o){
        tagList.add(o);
    }

    public void iterate(Function<Object,Boolean> funct){
        for(int i = 0;i < tagList.size();i++)
            if(funct.apply(tagList.get(i)))
                tagList.remove(i--);
    }


}
