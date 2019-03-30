package skywolf46.ServerEdit.Modules.CraftScript.Data;

import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.System.ScriptStarter.ScriptThread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExecuteState {
    private boolean executeBreak = false;
    private boolean isOROptionCompleted = false;
    private List<CraftScriptClass> lines = new ArrayList<>();
    private HashMap<String, Object> map = new HashMap<>();
    private int currentIndex = 0;
    private ScriptThread manage;
    private boolean modified = false;

    public ExecuteState(HashMap<String, Object> map, ScriptThread st) {
        this.map.putAll(map);
        this.manage = st;
    }

    public ExecuteState() {

    }

    public void breakExecute() {
        System.out.println("Break execution detected");
        executeBreak = true;
    }

    public boolean canExecute() {
        return !executeBreak && isOROptionCompleted;
    }

    public boolean isExecuteBreaked() {
        return executeBreak;
    }

    public void completeOROption() {
        isOROptionCompleted = true;
    }

    public void addStateObject(String s, Object o) {
        map.put(s, o);
    }

    public <T> T getStateObject(String s) {
        return (T) map.get(s);
    }

    public void nextLine() {
        currentIndex++;
    }

    public CraftScriptClass get(int line) {
        return this.lines.get(line);
    }

    public void jumpTo(int index) {
        modified = true;
        if (index <= 0)
            index = 0;
        currentIndex = index;
    }

    public HashMap<String, Object> getStateObjects() {
        return new HashMap<>(map);
    }

    public int getCurrentLine() {
        return currentIndex;
    }

    public ScriptThread getScriptThread() {
        return manage;
    }

    public void setUnmodified() {
        this.modified = false;
    }

    public void addIfNotModified() {
        if (!modified)
            currentIndex++;
    }

    public void collapse(ExecuteState state,ScriptThread th) {
        map.putAll(state.map);
        this.manage = th;
    }
}
