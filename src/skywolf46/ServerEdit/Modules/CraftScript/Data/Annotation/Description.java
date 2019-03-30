package skywolf46.ServerEdit.Modules.CraftScript.Data.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
public @interface Description {
    String[] value();
}
