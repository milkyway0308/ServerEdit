package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension.AbstractCondition;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension.AbstractConditionHelper;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public class ToVariableConnectCondition extends CraftScriptClass {
    @Override
    public String getClassName() {
        return "ToVariableConnectCondition";
    }

    @Override
    public String getClassPath() {
        return "native.condition";
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
            if (s.equalsIgnoreCase("to"))
                return new ToVariableConnectCondition();
            return null;
        };
    }

    @Override
    public int processPriority() {
        return 0;
    }

    // Condition helper, but use with array
    @Override
    public boolean isIgnored() {
        return false;
    }
}
