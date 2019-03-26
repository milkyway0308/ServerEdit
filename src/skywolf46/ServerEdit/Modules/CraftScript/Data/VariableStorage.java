package skywolf46.ServerEdit.Modules.CraftScript.Data;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;

import java.util.HashMap;

public class VariableStorage {

    public static final VariableStorage GLOBAL = new VariableStorage();
    private HashMap<String, CraftScriptClass> stored = new HashMap<>();

    public void store(String s,CraftScriptClass sc){
        stored.put(s,sc);
    }

    public CraftScriptClass get(String s){
        return stored.getOrDefault(s, VoidOverInfinityClass.STATIC);
    }

    public boolean isStored(String s){
        return stored.containsKey(s);
    }

    public boolean remove(String s){
        return stored.remove(s) != null;
    }


}
