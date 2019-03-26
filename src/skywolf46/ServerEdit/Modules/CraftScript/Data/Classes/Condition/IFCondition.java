package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension.AbstractCondition;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension.AbstractParsableCondition;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.BooleanClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class IFCondition extends AbstractParsableCondition {

    @Override
    public String getClassName() {
        return "IFCondition";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {
        if (currentIndex != 0)
            throw new IllegalStateException("If must have to position at index 0");
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        Iterator<Integer> ifIndexer = state.getStateObject("EndIfIndexer");
        if (ifIndexer == null)
            state.addStateObject("EndIfIndexer", ifIndexer = ((List<Integer>) state.getStateObject("EndIfs")).iterator());
        int max = -1;
        for (int i = 1; i < st.length(); i++)
            if (max == -1)
                max = i;
            else if (st.get(max).processPriority() < st.get(i).processPriority())
                max = i;
        CraftScriptClass cl = st.get(max).process(state, st, max);
        if(!(cl instanceof BooleanClass))
            throw new IllegalStateException("If condition need Boolean return value");
        if (((BooleanClass) cl).getValue()) {
            ifIndexer.next();
            return cl;
        } else
            state.jumpTo(ifIndexer.next());
        return cl;
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s, i, b) -> {
            if (s.equalsIgnoreCase("if"))
                return new IFCondition();
            return null;
        };
    }

    @Override
    public int processPriority() {
        return 100000;
    }

    @Override
    public void validateScriptLine(ExecuteState state, int currentLine) {
        AtomicInteger startIf = state.getStateObject("If");
        if(startIf == null)
            state.addStateObject("If",startIf = new AtomicInteger(0));
        startIf.addAndGet(1);
    }
}
