package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition;

import skywolf46.ServerEdit.Modules.CraftScript.CraftScriptRegistry;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension.AbstractParsableCondition;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.PreprocessProcessor;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

import java.util.Arrays;

public class DefaultORCondition extends AbstractParsableCondition {
    private CompileStatus compileStatus;
    private long access;
    public DefaultORCondition(CompileStatus cl,long access) {
        applyData(cl, 0);
        this.access = access;
    }

    @Override
    public String getClassPath() {
        return "nativeX.hidden";
    }

    @Override
    public String getClassName() {
        return "DefaultORCondition";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {
        this.compileStatus = cl;
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st41, int currentIndexer) {
        int processIndex = -1;
        CompileStatus cl = compileStatus.clone();
        for(int i = 0;i < cl.length();){
            i = cl.get(i).getAfterProcessor().apply(i,cl,state);
        }
        for(PreprocessProcessor pr : CraftScriptRegistry.getPreprocessors())
            pr.process(cl,access);
        for (int i = 0; i < cl.length(); i++)
            if (processIndex == -1)
                processIndex = i;
            else if (cl.get(processIndex).processPriority() <= cl.get(i).processPriority())
                processIndex = i;
        if (!cl.get(processIndex).getClass().equals(ForceRequireCondition.class))
            state.completeOROption();
        return cl.get(processIndex).process(state,cl, processIndex);
    }



    @Override
    public CraftScriptClass processScript(ExecuteState state) {
        return process(state,null, 0);
    }


    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (str, index, status) -> new DefaultORCondition(null,0L);
    }

    public String executeAndToString(ExecuteState state) {
        int processIndex = -1;
        CompileStatus cl = compileStatus.clone();
        for(int i = 0;i < cl.length();){
            i = cl.get(i).getAfterProcessor().apply(i,cl,state);
        }
        for(PreprocessProcessor pr : CraftScriptRegistry.getPreprocessors())
            pr.process(cl,access);
        for (int i = 0; i < cl.length(); i++)
            if (processIndex == -1)
                processIndex = i;
            else if (cl.get(processIndex).processPriority() <= cl.get(i).processPriority())
                processIndex = i;
        cl.get(processIndex).process(state,cl, processIndex);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < cl.length();i++)
            sb.append(cl.get(i).toString());
        return sb.toString();
    }

    @Override
    public void validateScriptLine(ExecuteState state, int currentLine) {
        for(int i = 0;i < compileStatus.length();i++)
            compileStatus.get(i).validateScriptLine(state,currentLine);
    }
}
