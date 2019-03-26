package skywolf46.ServerEdit.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternReplacer {
    public static Pattern replace(String pattern){
        pattern = "^" + pattern + "$";
        pattern = pattern.replace("*",".*");
        System.out.println(Pattern.compile(pattern).pattern());
        return Pattern.compile(pattern);
    }

    public static void main(String[] args) {
        Pattern p = replace("te*");
        Matcher mc = p.matcher("te");
        System.out.println(mc.find());
        System.out.println(mc.group(1));
    }
}
