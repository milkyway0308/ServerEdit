package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native;

import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Exceptions.UnsupportedOperationException;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public class FloatClass extends NumberClass{
    private float val;

    public FloatClass(float val) {
        this.val = val;
    }

    @Override
    public String getClassName() {
        return "Float";
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (str,index, status) -> {
            if (str.length() < 2)
                return null;
            char last = str.charAt(str.length() - 1);
            if (last == 'f' || last == 'F')
                try {
                    return new FloatClass(Float.parseFloat(str.substring(0, str.length() - 1)));
                } catch (Exception ex) {
                }
            return null;
        };
    }


    @Override
    public CraftScriptClass calculate(String calc, CraftScriptClass nextTarget) {
        if(!(nextTarget instanceof NumberClass))
            throw new IllegalStateException("Cannot calculate without number class");
        NumberClass clc = (NumberClass) nextTarget;
        switch (calc) {
            case "+": {
                return new DoubleClass(val + clc.getValue().floatValue());
            }
            case "-": {
                return new DoubleClass(val - clc.getValue().floatValue());
            }
            case "*": {
                return new DoubleClass(val * clc.getValue().floatValue());
            }
            case "/": {
                return new DoubleClass(val / clc.getValue().floatValue());
            }
            case "%": {
                return new DoubleClass(val % clc.getValue().floatValue());
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
