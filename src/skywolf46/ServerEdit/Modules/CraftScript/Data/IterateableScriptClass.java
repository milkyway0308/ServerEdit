package skywolf46.ServerEdit.Modules.CraftScript.Data;

import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;

import java.util.Iterator;

public abstract class IterateableScriptClass<T extends CraftScriptClass> extends CraftScriptClass {

    public abstract int length();

    public abstract void add(T t);

    public abstract CraftScriptClass get(int index);

    public abstract boolean remove(int index);

    public Iterator<CraftScriptClass> getIterator() {
        return new Iterator<CraftScriptClass>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex <= length() - 1;
            }

            @Override
            public CraftScriptClass next() {
                return get(currentIndex++);
            }
        };
    }

    public boolean isTypeOf(Class<? extends CraftScriptClass> cls) {
        Iterator<CraftScriptClass> csc = getIterator();
        while (csc.hasNext())
            if (!cls.isAssignableFrom(csc.next().getClass()))
                return false;
        return true;
    }

    public boolean isTypeOf(String cls) {
        Iterator<CraftScriptClass> csc = getIterator();
        while (csc.hasNext()){
            CraftScriptClass next = csc.next();
            if(next.getFullClassName().equalsIgnoreCase(cls) || next.getClassName().equalsIgnoreCase(cls))
                continue;
            return false;
        }
        return true;
    }
}
