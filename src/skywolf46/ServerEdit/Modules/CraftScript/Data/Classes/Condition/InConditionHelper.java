package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension.AbstractConditionHelper;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public class InConditionHelper extends AbstractConditionHelper {
    @Override
    public String getClassName() {
        return "InConditionHelper";
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s, i, c) -> {
            if (s.equalsIgnoreCase("in"))
                return new InConditionHelper();
            return null;
        };
    }

    // Condition helper, but use with array
    @Override
    public boolean isIgnored() {
        return false;
    }
}
