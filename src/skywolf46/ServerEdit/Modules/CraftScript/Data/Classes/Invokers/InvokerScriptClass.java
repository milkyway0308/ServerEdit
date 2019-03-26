package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Invokers;

import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Exceptions.InvokerLocateException;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;

public abstract class InvokerScriptClass extends CraftScriptClass {
    @Override
    public String getClassPath() {
        return "native.invoker";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {
        if(currentIndex != 0)
            throw new InvokerLocateException();
        apply(cl,currentIndex);
    }

    protected abstract void apply(CompileStatus cl,int index);

    @Override
    public int processPriority() {
        return 100000;
    }
}
