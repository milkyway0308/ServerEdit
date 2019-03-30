package skywolf46.ServerEdit.Modules.CraftScript.Data.Abstract;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Annotation.Description;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Interface.CastableScriptClass;

@Description(
        "On placeholder, use static instance instead creating instance."
)
public abstract class AbstractPlaceHolder extends CastableScriptClass {

    @Override
    public String getClassPath() {
        return "native.placeholder";
    }
}
