package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Interface.CastableScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;

public abstract class NativeScriptClass extends CastableScriptClass  {
    @Override
    public void applyData(CompileStatus cl, int currentIndex) {

    }

    @Override
    public NativeScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        return this;
    }

    @Override
    public int processPriority() {
        return -1000;
    }

    @Override
    public String getClassPath() {
        return "native";
    }
}
