package skywolf46.ServerEdit.Modules.CraftScript.Exceptions;

public class CastUnableException extends IllegalStateException{

    public CastUnableException(String from){
        super("[CraftScript|Compile Error] Class " + from + " is uncastable");
    }
    public CastUnableException(String from,String to){
        super("[CraftScript|Compile Error] Cannot cast " + from + " to " + to);
    }
}
