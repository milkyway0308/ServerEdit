package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Variables;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Data.VariableStorage;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public class GetVariable extends CraftScriptClass {
    private String toGet;
    private boolean temp = false;
    public GetVariable(String g) {
        this.toGet = g;
        if(toGet.length() > 1 && toGet.startsWith("$")){
            temp = true;
            toGet = toGet.substring(1);
        }
    }

    @Override
    public String getClassName() {
        return "GetVariable";
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
        return (s, i, c) -> {
            if (s.length() > 2 && s.startsWith("{") && s.endsWith("}"))
                return new GetVariable(s.substring(1, s.length() - 1));
            return null;
        };
    }

    @Override
    public TripleFunction<Integer, CompileStatus, ExecuteState, Integer> getAfterProcessor() {
        return (i, c, e) -> {
            if(!temp)
                c.set(i,VariableStorage.GLOBAL.get(toGet));
            else
                c.set(i,e.getStateObject(toGet) == null ? VoidOverInfinityClass.STATIC : e.getStateObject(toGet));
            return i + 1;
        };
    }

    @Override
    public int processPriority() {
        return 0;
    }
}
