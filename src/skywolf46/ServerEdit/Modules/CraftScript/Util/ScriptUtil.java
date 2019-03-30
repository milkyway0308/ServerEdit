package skywolf46.ServerEdit.Modules.CraftScript.Util;

import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;

import java.util.ArrayList;
import java.util.List;

public class ScriptUtil {
    private static List<Class<? extends CraftScriptClass>> converterClass = new ArrayList<>();
    public static boolean isInstanceOf(CraftScriptClass cs,Class<? extends CraftScriptClass> cl){
        return converterClass.contains(cl) || cl.isAssignableFrom(cs.getClass());
    }
}
