package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native;

import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Exceptions.UnsupportedOperationException;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public class DoubleClass extends NumberClass {
    private double val;

    private static Class DOUBLE = DoubleClass.class;
    private static Class FLOAT = FloatClass.class;
    private static Class INTEGER = Integer.class;
    private static Class STRING = String.class;

    public DoubleClass(double val) {
        this.val = val;
    }

    @Override
    public String getClassName() {
        return "Double";
    }


    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (str,index, status) -> {
            try {
                return new DoubleClass(Double.parseDouble(str));
            } catch (Exception e) {
                return null;
            }
        };
    }

    @Override
    public CraftScriptClass calculate(String calc, CraftScriptClass nextTarget) {
        if(!(nextTarget instanceof NumberClass))
            throw new IllegalStateException("Cannot calculate without number class");
        NumberClass clc = (NumberClass) nextTarget;
        switch (calc) {
            case "+": {
                return new DoubleClass(val + clc.getValue().doubleValue());
            }
            case "-": {
                return new DoubleClass(val - clc.getValue().doubleValue());
            }
            case "*": {
                return new DoubleClass(val * clc.getValue().doubleValue());
            }
            case "/": {
                return new DoubleClass(val / clc.getValue().doubleValue());
            }
            case "%": {
                return new DoubleClass(val % clc.getValue().doubleValue());
            }
        }
        throw new UnsupportedOperationException(calc, "Double");
    }

    @Override
    public Number getValue() {
        return val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NumberClass && ((NumberClass) obj).getValue().doubleValue() == val;
    }
}
