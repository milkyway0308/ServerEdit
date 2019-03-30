package skywolf46.ServerEdit.Modules.CraftScript.System.ScriptStarter;

import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ScriptState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScriptThreadManager {
    private static ScriptThreadManager currentManager = new ScriptThreadManager();
    private List<ScriptThread> unused = new ArrayList<>();
    private final Object lock = new Object();
    private int numbering = 0;
    private AtomicBoolean stop = new AtomicBoolean(false);

    public static void startScript(ScriptState st) {
        synchronized (currentManager.lock) {
            if (currentManager.unused.size() <= 0)
                currentManager.unused.add(new ScriptThread(currentManager, currentManager.numbering++));
            currentManager.unused.remove(0)
                    .supply(st);
        }
    }

    public static void startScript(ScriptState scriptState, ExecuteState state) {
        synchronized (currentManager.lock) {
            if (currentManager.unused.size() <= 0)
                currentManager.unused.add(new ScriptThread(currentManager, currentManager.numbering++));
            currentManager.unused.remove(0)
                    .supply(scriptState,state);
        }
    }

    public void addThread(ScriptThread scriptThread) {
        synchronized (lock) {
            currentManager.unused.add(scriptThread);
        }
    }

    public static void stopCurrent() {
        currentManager.stop.set(true);
        currentManager = new ScriptThreadManager();
    }

    public boolean isStopped() {
        return stop.get();
    }
}
