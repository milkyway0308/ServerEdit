package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension.AbstractParsableCondition;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.BooleanClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.StringClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public class ISTypeComparator extends AbstractParsableCondition {
    @Override
    public String getClassName() {
        return "isTypeComparator";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {

    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        System.out.println(st.get(currentIndex+1).toString());
        String type = st.get(currentIndex+1).toString();
        return new BooleanClass(st.get(currentIndex-1).getClassName().equals(type) || st.get(currentIndex-1).getFullClassName().equals(type));
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s, i, c) -> {
            if(!s.equalsIgnoreCase("is"))
                return null;
            if (i == 0)
                throw new IllegalStateException("[CraftScript|Compile Error] is Type conversion condition cannot locate at 0");
            int next = c.length() - i - 1;
            if (next <= 0 || next > 1)
                throw new IllegalStateException("[CraftScript|Compile Error] is Type conversion condition need one parameter");
            c.set(i + 1, new StringClass(c.getString(i + 1)));
            return new ISTypeComparator();
        };
    }
}
