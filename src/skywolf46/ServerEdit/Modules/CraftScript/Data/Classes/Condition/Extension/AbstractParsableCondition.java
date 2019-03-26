package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension;

import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public abstract class AbstractParsableCondition extends AbstractCondition{
    @Override
    public abstract TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser();

    @Override
    public AbstractCondition createInstance() {
        return null;
    }
}
