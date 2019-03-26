package skywolf46.ServerEdit.Modules.CraftScript.Data;

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
    public ScriptState(List<String> str) {
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

    public void queueScript(){
        ScriptThreadManager.startScript(this);
    }
}
