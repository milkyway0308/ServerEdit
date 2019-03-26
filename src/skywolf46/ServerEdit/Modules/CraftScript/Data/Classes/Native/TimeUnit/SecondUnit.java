package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.TimeUnit;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.IntegerClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.NumberClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public class SecondUnit extends TimeUnitClass {
    private int val;

    @Override
    public String getClassName() {
        return "second";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {
        if(currentIndex <= 0 || !(cl.get(currentIndex-1) instanceof NumberClass))
            throw new IllegalStateException("SecondUnit need number parameter at front");
        val = (int) (Math.max(0,((NumberClass) cl.get(currentIndex-1)).getValue().doubleValue()) * 1000);
//        cl.remove(currentIndex-1);
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {

        return VoidOverInfinityClass.STATIC;
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s,i,c) -> {
            if(s.equals("second")){
                return new SecondUnit();
            }
            return null;
        };
    }

    @Override
    public TripleFunction<Integer, CompileStatus, ExecuteState, Integer> getAfterProcessor() {
        return (i,c,s) -> {
            c.set(i-1,new IntegerClass(val));
            c.remove(i);
            return i;
        };
    }

    @Override
    public int processPriority() {
        return 0;
    }
}
