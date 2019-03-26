package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Invokers;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.NumberClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.TimeUnit.TimeUnitClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Exceptions.IllegalParameterException;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public class WaitScriptInvoker extends InvokerScriptClass {
    private CompileStatus toSleep;

    @Override
    protected void apply(CompileStatus cl, int index) {
        if (cl.length() == 3) {
            CraftScriptClass csc = cl.get(index + 2);
            if (!(csc instanceof TimeUnitClass)) {
                throw new IllegalParameterException();
            }
            toSleep = cl;
        } else if(cl.length() == 2){
            if(!(cl.get(index+1) instanceof NumberClass))
                throw new IllegalParameterException();
        }
    }

    @Override
    public String getClassName() {
        return "PauseInvoker";
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        try {
            Thread.sleep(((NumberClass)st.get(currentIndex+1)).getValue().intValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return VoidOverInfinityClass.STATIC;
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s, i, c) -> {
            switch (s) {
                case "wait":
                case "pause":
                    return new WaitScriptInvoker();
            }
            return null;
        };
    }
}
