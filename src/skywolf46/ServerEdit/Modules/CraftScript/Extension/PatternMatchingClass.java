package skywolf46.ServerEdit.Modules.CraftScript.Extension;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Interface.CastableScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PatternMatchingClass extends CastableScriptClass {

    private List<Pattern> patterns;

    public abstract List<String> getClassPattern();

    protected abstract CraftScriptClass createInstance(Matcher mc);

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (str,index,stat) -> {
            if (patterns == null) {
                patterns = new ArrayList<>();
                for (String n : getClassPattern()) {
                    if (!n.startsWith("^"))
                        n = "^" + n;
                    else if (!n.endsWith("$"))
                        n = "$" + n;
                    try {
                        patterns.add(Pattern.compile(n));
                    } catch (Exception ex) {
                        System.err.println("[CraftScript|Pattern] Cannot compile pattern \"" + n + "\" of class " + getClass().getSimpleName() + ". Ignoring error occurred pattern.");
                        ex.printStackTrace();
                    }
                }
            }
            for (Pattern pattern : patterns) {
                Matcher matcher = pattern.matcher(str);
                if (matcher.matches())
                    try {
                        return createInstance(matcher);
                    } catch (Exception ex) {
                        System.err.println("[CraftScript|Runtime Compile] Cannot process pattern \"" + pattern.pattern() + "\" of class " + getClass().getSimpleName() + " : Skipping current method compiling.");
                        ex.printStackTrace();
                        return null;
                    }
            }
            return null;
        };
    }
}
