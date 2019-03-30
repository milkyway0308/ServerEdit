package skywolf46.ServerEdit.Modules.CraftScript.System.ScriptStarter;

import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ScriptState;

import java.util.concurrent.atomic.AtomicBoolean;

public class ScriptThread extends Thread {
    private ScriptState script;
    private ExecuteState state;
    private ScriptThreadManager stm;
    private int number;

    public ScriptThread(ScriptThreadManager currentManager, int i) {
        this.number = i;
        stm = currentManager;
        start();
    }

    @Override
    public void run() {
        while (!stm.isStopped()) {
            if (script != null) {
                if (state == null)
                    script.executeScript(this);
                else
                    script.executeScript(this,state);
                script = null;
                state = null;
                stm.addThread(this);
            }
        }
    }

    public void supply(ScriptState st) {
        this.script = st;
    }

    public void supply(ScriptState st, ExecuteState state) {
        this.script = st;
        this.state = state;
    }

    public int getThreadNumber() {
        return number;
    }
}
