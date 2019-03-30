package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native;

import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public class BooleanClass extends NativeScriptClass{
    private boolean val;
    public static final BooleanClass TRUE = new BooleanClass(true);
    public static final BooleanClass FALSE = new BooleanClass(false);
    public BooleanClass(boolean val){
        this.val = val;
    }

    public static CraftScriptClass valueOf(boolean b) {
        if(b)
            return TRUE;
        return FALSE;
    }

    @Override
    public String getClassName() {
        return "Boolean";
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s,i,c) -> {
            if(s.equalsIgnoreCase("true"))
                return new BooleanClass(true);
            else if(s.equalsIgnoreCase("false"))
                return new BooleanClass(false);
            return null;
        };
    }

    public boolean getValue(){
        return val;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BooleanClass && this.val == ((BooleanClass) obj).val;
    }

    @Override
    public String toString() {
        return super.toString() + "[" + val + "]";
    }
}
