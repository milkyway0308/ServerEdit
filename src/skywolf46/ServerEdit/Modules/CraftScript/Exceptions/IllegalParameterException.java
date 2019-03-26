package skywolf46.ServerEdit.Modules.CraftScript.Exceptions;

public class IllegalParameterException extends IllegalStateException{
    public IllegalParameterException(){
        super("[CraftScript|Compile Error] Parameter not match");
    }
}
