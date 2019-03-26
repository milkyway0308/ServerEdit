package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension.AbstractCondition;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.BooleanClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.AbstractTimeClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;

public class AfterCondition extends AbstractCondition {
    private AbstractTimeClass first;
    private AbstractTimeClass second;

    public AfterCondition(String[] strs) {
        super(strs);
    }

    public AfterCondition() {

    }

    @Override
    public String getClassName() {
        return "AfterCondition";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {
        if (currentIndex <= 0)
            throw new IllegalStateException("CraftScript condition parsing failed : Before condition need \"AbstractTimeClass\" parameter ahead");
        if (cl.length() <= currentIndex + 1)
            throw new IllegalStateException("CraftScript condition parsing failed : Before condition need next parameter");
        if (!AbstractTimeClass.class.isAssignableFrom(cl.get(currentIndex + 1).getClass()))
            throw new IllegalStateException("CraftScript condition parsing failed : Before condition need \"AbstractTimeClass\" extension parameter");
        if (!AbstractTimeClass.class.isAssignableFrom(cl.get(currentIndex - 1).getClass()))
            throw new IllegalStateException("CraftScript condition parsing failed : Before condition need \"AbstractTimeClass\" extension parameter");
        this.first = (AbstractTimeClass) cl.get(currentIndex - 1);
        this.second = (AbstractTimeClass) cl.get(currentIndex + 1);
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        return new BooleanClass(second.isAfterThen(first));
    }

    @Override
    public AbstractCondition createInstance() {
        return new AfterCondition();
    }
}
