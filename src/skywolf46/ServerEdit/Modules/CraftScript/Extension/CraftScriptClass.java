package skywolf46.ServerEdit.Modules.CraftScript.Extension;

import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

import java.util.HashMap;

public abstract class CraftScriptClass {
    private HashMap<String, CraftScriptClass> cls = new HashMap<>();

    public CraftScriptClass() {

    }

    public abstract String getClassName();

    public abstract String getClassPath();

    public final String getFullClassName() {
        return getClassPath() + "." + getClassName();
    }

    public abstract void applyData(CompileStatus cl, int currentIndex);

    public abstract CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex);

    public CraftScriptClass processScript(ExecuteState state) {
        return process(state, null, 0);
    }

    public abstract TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser();

    public TripleFunction<Integer, CompileStatus, ExecuteState, Integer> getAfterProcessor() {
        return (index, compile,state) -> index + 1;
    }


    public abstract int processPriority();

    public boolean isIgnored() {
        return false;
    }

    public void onIgnore(CraftScriptClass[] parsed, int index) {
    }

    public void validateScriptLine(ExecuteState state, int currentLine){

    }


    public boolean isSerializable() {
        return false;
    }


    @Override
    public String toString() {
        return "CraftScript|" + getFullClassName();
    }
}
