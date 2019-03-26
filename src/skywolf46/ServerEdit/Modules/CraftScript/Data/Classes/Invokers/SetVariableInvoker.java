package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Invokers;

import skywolf46.ServerEdit.Modules.CraftScript.CraftScriptRegistry;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.DefaultORCondition;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Data.VariableStorage;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public class SetVariableInvoker extends InvokerScriptClass{
    private int variableEnd = 0;
    private CraftScriptClass name;
    private CraftScriptClass var;
    public SetVariableInvoker(CraftScriptClass cs,CraftScriptClass ne){
        this.name = cs;
        this.var = ne;
    }
    @Override
    protected void apply(CompileStatus cl, int index) {
    }

    @Override
    public String getClassName() {
        return "setVariable";
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        System.out.println("Executed!");
        String var = ((DefaultORCondition)name).executeAndToString(state);
        VariableStorage.GLOBAL.store(var,this.var.processScript(state));
        return VoidOverInfinityClass.STATIC;
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s,i,c) -> {
            if(!s.equalsIgnoreCase("set"))
                return null;
            if(i != 0)
                throw new IllegalStateException("");
            int varEnd = -1;
            if(s.equalsIgnoreCase("set")){
                for(int x = 1;x < c.length();x++)
                    if(c.getString(x).equalsIgnoreCase("to")){
                        varEnd = x;
                        break;
                    }
                if(varEnd == -1 || varEnd == i || varEnd >= c.length()-1)
                    throw new IllegalStateException();
                StringBuilder reparse = new StringBuilder();
                for(int x = 1;x < varEnd;x++)
                    reparse.append(c.getOriginal(x)).append(" ");
                CraftScriptClass variableName = CraftScriptRegistry.parseString(reparse.substring(0,reparse.length()-1));
                reparse = new StringBuilder();
                for(int x = varEnd+1;x < c.length();x++)
                    reparse.append(c.getOriginal(x)).append(" ");
                CraftScriptClass variable = CraftScriptRegistry.parseString(reparse.substring(0,reparse.length()-1));
                int toRemove = c.length();
                for(int x = 1;x < toRemove;x++){
                    c.remove(1);
                }

                return new SetVariableInvoker(variableName,variable);
            }
            return null;
        };
    }
}
