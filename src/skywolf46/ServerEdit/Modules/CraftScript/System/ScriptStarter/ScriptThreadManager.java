package skywolf46.ServerEdit.Modules.CraftScript.System.ScriptStarter;

import skywolf46.ServerEdit.Modules.CraftScript.Data.ScriptState;

import java.util.ArrayList;
import java.util.List;

public class ScriptThreadManager {
    private static List<ScriptThread> unused = new ArrayList<>();
    private static final Object lock = new Object();
    private static int numbering = 0;
    public static void startScript(ScriptState st){
        synchronized (lock){
            if(unused.size() <= 0)
                unused.add(new ScriptThread(numbering++));
            unused.remove(0)
                    .supply(st);
        }
    }

    public static void addThread(ScriptThread scriptThread) {
        synchronized (lock){
            unused.add(scriptThread);
        }
    }

    public static void stop() {
        ScriptThread.stopThread.set(true);
    }
}
