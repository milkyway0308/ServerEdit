package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension.AbstractCondition;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.BooleanClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;

public class ForceRequireCondition extends AbstractCondition {
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

    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        CraftScriptClass toExecute = null;
        int len = 1;
        for (int i = 1; i < st.length(); i++)
            if (toExecute == null)
                toExecute = st.get(i);
            else if (toExecute.processPriority() <= st.get(i).processPriority()){
                toExecute = st.get(i);
                len = i;
            }
            if(toExecute == null)
                throw new IllegalStateException("[CraftScript|Runtime Error] \"Force require\" condition need one or more parmeter");
        CraftScriptClass cl = toExecute.process(state, st, len);
        if(cl instanceof BooleanClass && !((BooleanClass) cl).getValue()){
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
