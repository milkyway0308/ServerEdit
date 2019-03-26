package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.System;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Interface.CastableScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public class TypeOfVariable extends CastableScriptClass {

    private CraftScriptClass csc;
    public TypeOfVariable(CraftScriptClass cl){
        this.csc = cl;
    }

    @Override
    public String getClassName() {
        return "typeOf";
    }

    @Override
    public String getClassPath() {
        return "nativeX.hidden";
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
        return null;
    }

    @Override
    public int processPriority() {
        return 0;
    }

    @Override
    public String toString() {
        return csc.getFullClassName();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CraftScriptClass && csc.getFullClassName().equals(((CraftScriptClass) obj).getFullClassName());
    }
}
