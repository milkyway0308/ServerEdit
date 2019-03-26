package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension;

import skywolf46.ServerEdit.Modules.CraftScript.Extension.PatternMatchingClass;

public abstract class AbstractPatternCondition extends PatternMatchingClass {

    @Override
    public int processPriority() {
        return 1000;
    }
}
