package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native;

import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Exceptions.QuoteUnclosedException;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

import java.util.Arrays;
import java.util.HashMap;

public class StringClass extends NativeScriptClass {
    private String current;

    public StringClass(String current) {
        this.current = current;
    }

    @Override
    public String getClassName() {
        return "String";
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (str, index, status) -> {
            if (str.length() > 0) {
                char opener = '\"';
                char start = str.charAt(0);
                if (opener == start || (opener = '\'') == start) {
                    if (str.length() > 1 && str.charAt(str.length() - 1) == opener)
                        return new StringClass(str.substring(1, str.length() - 1));
                    for (int i = index + 1; i < status.stringLength(); i++) {
                        String s = status.getString(i);
                        if (s.length() > 0 && s.charAt(s.length() - 1) == opener) {
                            StringBuilder item = new StringBuilder(str.substring(1));

                            for (int c = index + 1; c < i; c++)
                                item.append(' ').append(status.getString(c));
                            item.append(' ').append(s, 0, s.length() - 1);
                            String[] reIndexed = new String[status.stringLength() - (i - index)];
                            CraftScriptClass[] reIndexedClass = new CraftScriptClass[reIndexed.length];
                            HashMap[] reIndexedAttribute = new HashMap[reIndexed.length];
                            for (int x = 0; x < reIndexedClass.length; x++) {
                                reIndexedClass[x] = status.get(x);
                            }
                            int attr = 0;
                            for (int a = 0; a < index; a++){
                                reIndexedAttribute[attr++] = status.attribute(a);
                            }
                            for (int a = i; a < status.stringLength(); a++){
                                reIndexedAttribute[attr++] = status.attribute(a);
                            }
                            status.updateAttributes(reIndexedAttribute);
                            status.updateArray(reIndexedClass);

                            int filled = 0;
                            for (int x = 0; x < status.stringLength(); x++) {
                                if (x == index) {
                                    reIndexed[filled++] = ('\"' + item.toString() + '\"');

                                } else if (x < index || x > i) {
                                    reIndexed[filled++] = status.getString(x);
                                } else {
//
                                }
                            }
                            status.updateStringArray(reIndexed);
                            return new StringClass(item.toString());
                        }
                    }
                    throw new QuoteUnclosedException();
                }
            }
            return null;
        };
    }

    @Override
    public String toString() {
        return String.valueOf(current);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringClass && ((StringClass) obj).current.equals(current);
    }
}
