package skywolf46.ServerEdit.Modules.CraftScript.Data;

import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CastStorage {
    private HashMap<String, BiFunction<String[],CraftScriptClass,CraftScriptClass>> caster = new HashMap<>();
    public static final BiFunction<String[],CraftScriptClass,CraftScriptClass> DEFAULT_NULL_PARSER = (path,cls) -> null;

    public BiFunction<String[],CraftScriptClass,CraftScriptClass> getCaster(String to){
        return caster.getOrDefault(to,DEFAULT_NULL_PARSER);
    }

    public void register(String to,BiFunction<String[],CraftScriptClass,CraftScriptClass> caster){
        this.caster.put(to,caster);
    }
}
