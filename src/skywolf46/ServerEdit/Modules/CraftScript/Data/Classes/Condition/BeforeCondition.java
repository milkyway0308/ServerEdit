package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension.AbstractCondition;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.BooleanClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.AbstractTimeClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;

public class BeforeCondition extends AbstractCondition {
    private AbstractTimeClass first;
    private AbstractTimeClass second;


    public BeforeCondition(String[] cond) {
        super(cond);
    }

    public BeforeCondition() {

    }

    @Override
    public String getClassName() {
        return "BeforeCondition";
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
        return new BooleanClass(second.isBeforeThen(first));
    }

    @Override
    public AbstractCondition createInstance() {
        return new BeforeCondition();
    }

}
