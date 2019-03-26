package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native;

import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public class VoidOverInfinityClass extends CraftScriptClass {
    public static final VoidOverInfinityClass STATIC = new VoidOverInfinityClass();

    @Override
    public String getClassName() {
        return "voidOverInfinity";
    }

    @Override
    public String getClassPath() {
        return "native";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {

    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        return STATIC;
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s,i,c) -> {
            if(s.equalsIgnoreCase("null") || s.equalsIgnoreCase("nullptr") || s.equalsIgnoreCase("nullpointer"))
                return STATIC;
            return null;
        };
    }

    @Override
    public int processPriority() {
        return 0;
    }

    @Override
    public String toString() {
        return "null";
    }
}
