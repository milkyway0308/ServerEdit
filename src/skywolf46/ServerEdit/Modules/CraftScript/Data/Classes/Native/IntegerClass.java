package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native;

import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Exceptions.UnsupportedOperationException;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public class IntegerClass extends NumberClass{
    private int val;

    public IntegerClass(int val) {
        this.val = val;
    }

    @Override
    public String getClassName() {
        return "Integer";
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (str,index,status) -> {
            try {
                return new IntegerClass(Integer.parseInt(str));
            }catch (Exception ex){
                return null;
            }
        };
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }


    @Override
    public CraftScriptClass calculate(String calc, CraftScriptClass nextTarget) {
        if(!(nextTarget instanceof NumberClass))
            throw new IllegalStateException("Cannot calculate without number class");
        NumberClass clc = (NumberClass) nextTarget;
        switch (calc) {
            case "+": {
                return new DoubleClass(val + clc.getValue().intValue());
            }
            case "-": {
                return new DoubleClass(val - clc.getValue().intValue());
            }
            case "*": {
                return new DoubleClass(val * clc.getValue().intValue());
            }
            case "/": {
                return new DoubleClass(val / clc.getValue().intValue());
            }
            case "%": {
                return new DoubleClass(val % clc.getValue().intValue());
            }
        }
        throw new UnsupportedOperationException(calc, "Integer");
    }

    @Override
    public Number getValue() {
        return val;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NumberClass && ((NumberClass) obj).getValue().doubleValue() == val;
    }
}
