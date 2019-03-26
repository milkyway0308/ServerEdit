package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension.AbstractConditionHelper;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public class ThenConditionHelper extends AbstractConditionHelper {
    @Override
    public String getClassName() {
        return "ThenCondition";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {
        // What do i thinking
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        return VoidOverInfinityClass.STATIC;
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (str,index,status) -> {
            if(str.equals("then"))
                return new ThenConditionHelper();
            return null;
        };
    }

    @Override
    public void onIgnore(CraftScriptClass[] parsed, int index) {
        // Yay I'm ignored
    }
}
