package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Invokers;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public class ConsolePrintInvoker extends InvokerScriptClass {
    @Override
    public String getClassName() {
        return "ConsolePrintInvoker";
    }

    @Override
    protected void apply(CompileStatus cl,int index) {
        if(cl.length() == 1)
            throw new IllegalStateException("[CraftScript|Compile Error] ConsolePrint invoker need 1 or more argument");
    }

    @Override
    public VoidOverInfinityClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        CraftScriptClass[] copy = st.getAll();
        StringBuilder sb = new StringBuilder();
        for(int i = 1;i < copy.length;i++)
            sb.append(copy[i].toString());
        System.out.println(sb.toString());
        return VoidOverInfinityClass.STATIC;
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s,index,c) -> {
            switch (s){
                case "console":
                case "print":
                    return new ConsolePrintInvoker();
            }
            return null;
        };
    }

}
