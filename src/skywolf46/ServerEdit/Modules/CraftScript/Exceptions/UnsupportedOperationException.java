package skywolf46.ServerEdit.Modules.CraftScript.Exceptions;

public class UnsupportedOperationException extends IllegalStateException{
    public UnsupportedOperationException(String operator, String className){
        super("Operator " + operator + " is not supported on class " + className);
    }
}
