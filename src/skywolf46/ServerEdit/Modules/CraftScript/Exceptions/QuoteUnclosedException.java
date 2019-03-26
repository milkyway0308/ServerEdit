package skywolf46.ServerEdit.Modules.CraftScript.Exceptions;

public class QuoteUnclosedException extends IllegalStateException{
    public QuoteUnclosedException(){
        super("[CraftScript|Compile Error] String quote unclosed");
    }
}
