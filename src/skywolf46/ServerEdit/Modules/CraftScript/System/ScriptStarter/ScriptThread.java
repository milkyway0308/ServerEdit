package skywolf46.ServerEdit.Modules.CraftScript.System.ScriptStarter;

import skywolf46.ServerEdit.Modules.CraftScript.Data.ScriptState;

import java.util.concurrent.atomic.AtomicBoolean;

public class ScriptThread extends Thread{
    private ScriptState script;
    public static AtomicBoolean stopThread = new AtomicBoolean();
    private int number;
    public ScriptThread(int i){
        this.number = i;
        start();
    }

    @Override
    public void run() {
        while (!stopThread.get()){
            if(script != null){

                script.executeScript(this);
                script = null;
                ScriptThreadManager.addThread(this);
            }
        }
    }

    public void supply(ScriptState st) {
        this.script = st;
    }

    public int getThreadNumber(){
        return number;
    }
}
