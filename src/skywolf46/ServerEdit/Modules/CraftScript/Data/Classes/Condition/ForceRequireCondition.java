package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension.AbstractCondition;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.BooleanClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;

public class ForceRequireCondition extends AbstractCondition {
    private CraftScriptClass toExecute;

    public ForceRequireCondition(String[] str) {
        super(str);
    }

    public ForceRequireCondition() {

    }

    @Override
    public String getClassName() {
        return "ForceRequire";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {
        if (currentIndex != 0)
            throw new IllegalStateException("\"Force Require\" condition only can used in index 0");
        for (int i = 1; i < cl.length(); i++)
            if (toExecute == null)
                toExecute = cl.get(i);
            else if (toExecute.processPriority() <= cl.get(i).processPriority())
                toExecute = cl.get(i);
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        CraftScriptClass cl = toExecute.processScript(state);
        if(cl instanceof BooleanClass && ((BooleanClass) cl).getValue()){
            state.breakExecute();
        }
        return cl;
    }

    @Override
    public int processPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public AbstractCondition createInstance() {
        return new ForceRequireCondition();
    }
}
