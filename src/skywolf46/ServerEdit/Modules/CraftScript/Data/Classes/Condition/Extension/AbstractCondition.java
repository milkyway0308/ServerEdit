package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension;

import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;

public abstract class AbstractCondition extends AbstractPatternCondition{
    private static HashMap<String,AbstractCondition> conditions = new HashMap<>();

    public AbstractCondition(){

    }

    public AbstractCondition(String[] conditionNames){
        for(String n : conditionNames)
            conditions.put(n,this);
    }

    @Override
    protected CraftScriptClass createInstance(Matcher mc) {
        return null;
    }

    @Override
    public List<String> getClassPattern() {
        return new ArrayList<>();
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (str,index,st) -> {
            if(conditions.containsKey(str))
                return conditions.get(str).createInstance();
            return null;
        };
    }


    public abstract AbstractCondition createInstance();

    @Override
    public String getClassPath() {
        return "native.condition";
    }
}
