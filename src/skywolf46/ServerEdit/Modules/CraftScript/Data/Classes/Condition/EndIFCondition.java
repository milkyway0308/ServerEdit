package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension.AbstractParsableCondition;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EndIFCondition extends AbstractParsableCondition {
    @Override
    public String getClassName() {
        return "EndIF";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {

    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        return VoidOverInfinityClass.STATIC;
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s,i,c) -> {
            if(s.equalsIgnoreCase("endif"))
                return new EndIFCondition();
            return null;
        };
    }

    @Override
    public void validateScriptLine(ExecuteState state, int currentLine) {
        if(state.getStateObject("EndIf") == null)
            state.addStateObject("EndIf",new AtomicInteger(0));
        AtomicInteger endIf = state.getStateObject("EndIf");
        AtomicInteger startIf = state.getStateObject("If");
        if(startIf == null)
            state.addStateObject("If",startIf = new AtomicInteger(0));
        if(endIf.addAndGet(1) != startIf.get())
            throw new IllegalStateException("[CraftScript|Compile Error] If condition not closed");
        List<Integer> endIfs = state.getStateObject("EndIfs");
        if(endIfs == null)
            state.addStateObject("EndIfs",(endIfs = new ArrayList<>()));
        endIfs.add(currentLine);
    }
}
