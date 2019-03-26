package skywolf46.ServerEdit.Modules.CraftScript.Exceptions;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Invokers.InvokerScriptClass;

public class InvokerLocateException extends IllegalStateException{
    public InvokerLocateException(){
        super("[CraftScript|Compile Error] Invoker class only can located in index 0");
    }
}
