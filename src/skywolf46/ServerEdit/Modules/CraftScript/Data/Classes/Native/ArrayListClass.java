package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native;

import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Data.IterateableScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

import java.util.ArrayList;
import java.util.List;

public class ArrayListClass extends IterateableScriptClass<CraftScriptClass> {
    private List<CraftScriptClass> arr = new ArrayList<>();

    @Override
    public int length() {
        return arr.size();
    }

    @Override
    public void add(CraftScriptClass craftScriptClass) {
        arr.add(craftScriptClass);
    }

    @Override
    public CraftScriptClass get(int index) {
        return arr.get(index);
    }

    @Override
    public boolean remove(int index) {
        return arr.remove(index) != null;
    }

    @Override
    public String getClassName() {
        return "ArrayList";
    }

    @Override
    public String getClassPath() {
        return "native.iterateable";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {
        // Already compiled in parser
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        return VoidOverInfinityClass.STATIC;
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s,i,c) -> {
            if(s.equals("arraylist") || s.equalsIgnoreCase("list"))
                return new ArrayListClass();
            return null;
        };
    }

    @Override
    public int processPriority() {
        return 0;
    }


}
