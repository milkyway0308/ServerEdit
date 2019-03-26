package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Interface;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Annotation.Description;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CastStorage;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;

import java.util.HashMap;
import java.util.function.BiFunction;

public abstract class CastableScriptClass extends CraftScriptClass {
    private static HashMap<String, CastStorage> casters = new HashMap<>();
    private static HashMap<String, BiFunction<String[], CraftScriptClass, CraftScriptClass>> defaultCaster = new HashMap<>();

    @Description({
            "Please use Full-ClassName."
    })
    public final CraftScriptClass castTo(String cl) {
        CastStorage storage = casters.get(getClassName());
        if (storage == null)
            storage = casters.get(getClassPath() + "." + getClassName());
        if (storage != null) {
            CraftScriptClass ret = storage.getCaster(cl)
                    .apply(new String[]{
                            getFullClassName(), cl
                    }, this);
            if (ret == null)
                ret = defaultCaster.getOrDefault(cl, CastStorage.DEFAULT_NULL_PARSER)
                        .apply(new String[]{
                                getFullClassName(), cl
                        }, this);
            return ret;
        }
        return defaultCaster.getOrDefault(cl, CastStorage.DEFAULT_NULL_PARSER)
                .apply(new String[]{
                        getFullClassName(), cl
                }, this);
    }

    protected void register(BiFunction<String[], CraftScriptClass, CraftScriptClass> func, String... targetClassName) {
        for (String n : targetClassName)
            casters.computeIfAbsent(getFullClassName(), (k) -> new CastStorage())
                    .register(n, func);
    }

    public static void registerGlobalCaster(String className, BiFunction<String[], CraftScriptClass, CraftScriptClass> func) {
        defaultCaster.put(className, func);
    }
}
