package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.System;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.IntegerClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public class ThreadNumberVariable extends CraftScriptClass {
    @Override
    public String getClassName() {
        return "threadnum";
    }

    @Override
    public String getClassPath() {
        return "native.variable";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {

    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        return VoidOverInfinityClass.STATIC;
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s,i,c) -> {
            if(s.equalsIgnoreCase("threadNumber"))
                return new ThreadNumberVariable();
            return null;
        };
    }

    @Override
    public int processPriority() {
        return 0;
    }

    @Override
    public TripleFunction<Integer, CompileStatus, ExecuteState, Integer> getAfterProcessor() {
        return (i,c,e) -> {
            c.set(i,new IntegerClass(e.getScriptThread().getThreadNumber()));
            return i+1;
        };
    }
}
