package skywolf46.ServerEdit.Modules.CraftScript.Data.AfterProcessor;

import skywolf46.ServerEdit.Modules.CraftScript.PreprocessProcessor;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Interface.CastableScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Exceptions.CastUnableException;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;

public class CastingProcessor extends PreprocessProcessor {
    @Override
    public void process(CompileStatus status, long accessKey) {
        for(int i = 0;i < status.length();i++){
            if(status.attribute(i).containsKey("cast")){
                CraftScriptClass cls = status.get(i);
                if(!(cls instanceof CastableScriptClass))
                   throw new CastUnableException(cls.getFullClassName());

                CraftScriptClass casted = ((CastableScriptClass) cls).castTo(status.attribute(i).get("cast"));
                if(casted == null)
                    throw new CastUnableException(cls.getFullClassName(),status.attribute(i).get("cast"));
                status.set(i,casted);
            }
        }
    }
}
