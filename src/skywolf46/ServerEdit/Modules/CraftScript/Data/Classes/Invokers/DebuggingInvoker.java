package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Invokers;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

import java.util.function.BiConsumer;

public class DebuggingInvoker extends InvokerScriptClass {
    private BiConsumer<CompileStatus, Integer> consumer;

    public DebuggingInvoker(BiConsumer<CompileStatus, Integer> consumer) {
        this.consumer = consumer;
    }

    @Override
    protected void apply(CompileStatus cl, int index) {
        this.consumer.accept(cl, index);
    }

    @Override
    public String getClassName() {
        return "debugInvoker";
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        return VoidOverInfinityClass.STATIC;
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s, i, c) -> {
            if (s.equalsIgnoreCase("DEBUG_SENTENCE"))
                return new DebuggingInvoker((st,in) -> {});
            return null;
        };
    }
}
