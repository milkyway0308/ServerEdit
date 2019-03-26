package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension.AbstractCondition;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.BooleanClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.AbstractTimeClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;

import java.util.Arrays;

public class EqualsCondition extends AbstractCondition {
    private CraftScriptClass first;
    private CraftScriptClass second;

    public EqualsCondition(String[] str) {
        super(str);
    }

    public EqualsCondition() {

    }

    @Override
    public String getClassName() {
        return "equals";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {
        if (currentIndex <= 0)
            throw new IllegalStateException("CraftScript condition parsing failed : Equals condition need first parameter");
        if (cl.length() <= currentIndex + 1)
            throw new IllegalStateException("CraftScript condition parsing failed : Equals condition need next parameter");
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        return new BooleanClass(st.get(currentIndex-1).equals(st.get(currentIndex+1)));
    }

    @Override
    public AbstractCondition createInstance() {
        return new EqualsCondition();
    }


}