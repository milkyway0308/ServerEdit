package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Interface.CastableScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;

public abstract class AbstractVariable extends CastableScriptClass {

    @Override
    public String getClassPath() {
        return "native.variable";
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        // Variable cannot be processed
        return this;
    }

    @Override
    public int processPriority() {
        return -100000;
    }
}
