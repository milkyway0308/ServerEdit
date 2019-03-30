package skywolf46.ServerEdit.Modules.CraftScript;

import skywolf46.ServerEdit.Modules.CraftScript.Data.ScriptState;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleConsumer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

public class ScriptReader {
    public static HashMap<String, ScriptState> readScript(File f, TripleConsumer<File, String, Exception> exceptCatcher) {
        try {
            BufferedReader bif = new BufferedReader(new FileReader(f));
            HashMap<String, ScriptState> text = new HashMap<>();
            List<String> list = new ArrayList<>();
            String scriptName = "construct";
            String str;
            while ((str = bif.readLine()) != null) {
                int substring = 0;
                for(int i = 0;i < str.length();i++){
                    if(str.charAt(i) == '\t' || str.charAt(i) == ' ')
                        substring++;
                    else
                        break;
                }
                str = str.substring(substring,str.length());
                if (str.startsWith("script:")) {
                    try {
                        text.put(scriptName, new ScriptState(list));
                    } catch (Exception ex) {
                        exceptCatcher.consume(f, scriptName, ex);
                    }
                    list.clear();
                    scriptName = str.substring(str.indexOf(':') + 1);
                } else {
                    list.add(str);
                }
            }
            try {
                text.put(scriptName, new ScriptState(list));
            } catch (Exception ex) {
                exceptCatcher.consume(f, scriptName, ex);
            }
            return text;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
