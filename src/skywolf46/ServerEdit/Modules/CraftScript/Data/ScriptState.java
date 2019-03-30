package skywolf46.ServerEdit.Modules.CraftScript.Data;

import org.apache.logging.log4j.core.script.ScriptManager;
import skywolf46.ServerEdit.Modules.CraftScript.CraftScriptRegistry;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.System.ScriptStarter.ScriptThread;
import skywolf46.ServerEdit.Modules.CraftScript.System.ScriptStarter.ScriptThreadManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScriptState {
    private List<CraftScriptClass> scripts = new ArrayList<>();
    private ExecuteState baseState = new ExecuteState();
    private AtomicBoolean stopScript = new AtomicBoolean();

    public List<String> strs = new ArrayList<>();
    public ScriptState(List<String> str) {
        strs = new ArrayList<>(str);
        for (String n : str)
            scripts.add(CraftScriptRegistry.parseString(n));
        for (int i = 0; i < scripts.size(); i++)
            scripts.get(i).validateScriptLine(baseState, i);
    }

    public void executeScript(ScriptThread scriptThread) {
        ExecuteState state = new ExecuteState(baseState.getStateObjects(),scriptThread);
        for (int i = 0; i < scripts.size();) {
            state.setUnmodified();
            scripts.get(i).processScript(state);
            if(state.isExecuteBreaked())
                return;
            if(stopScript.get())
                return;
            state.addIfNotModified();
            i = state.getCurrentLine();
        }
    }

    public void executeScript(ScriptThread scriptThread,ExecuteState state) {
        new ExecuteState(state.getStateObjects(),scriptThread).collapse(baseState,scriptThread);
        for (int i = 0; i < scripts.size();) {
            state.setUnmodified();
            scripts.get(i).processScript(state);
            if(state.isExecuteBreaked())
                return;
            if(stopScript.get())
                return;
            state.addIfNotModified();
            i = state.getCurrentLine();
        }
    }

    public void queueScript(){
        ScriptThreadManager.startScript(this);
    }

    public void queueScript(ExecuteState state){
        ScriptThreadManager.startScript(this,state);
    }

    @Override
    public String toString() {
        return "CraftScript|ScriptState{Script lines: " + scripts.size() + ",String size : " + strs.size() + "}";
    }
}
