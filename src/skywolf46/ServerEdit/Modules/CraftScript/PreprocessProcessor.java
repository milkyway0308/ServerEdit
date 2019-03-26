package skywolf46.ServerEdit.Modules.CraftScript;

import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;

public abstract class PreprocessProcessor {
    public abstract void process(CompileStatus status,long accessKey);
}
