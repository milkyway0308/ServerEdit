package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.IgnoredCraftScriptClass;

public abstract class AbstractConditionHelper extends IgnoredCraftScriptClass {

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {
        // Hi, I am Condition Helper!
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        // ServerEdit CraftScript will not parse ConditionHelper
        return VoidOverInfinityClass.STATIC;
    }

    @Override
    public String getClassPath() {
        return "native.condition.ignored";
    }
}
