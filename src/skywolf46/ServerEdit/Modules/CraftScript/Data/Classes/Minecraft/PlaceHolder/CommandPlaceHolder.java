package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Minecraft.PlaceHolder;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Annotation.Description;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public class CommandPlaceHolder extends MinecraftPlaceHolder{
    public static final CommandPlaceHolder STATIC = new CommandPlaceHolder();
    @Override
    public String getClassName() {
        return "Command";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {

    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        return VoidOverInfinityClass.STATIC;
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s,i,c) ->{
            if(s.equalsIgnoreCase("command"))
                return STATIC;
            return null;
        };
    }

    @Override
    public int processPriority() {
        return 0;
    }
}
