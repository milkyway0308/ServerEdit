package skywolf46.ServerEdit.Modules.Test;

import org.apache.logging.log4j.core.script.ScriptManager;
import skywolf46.ServerEdit.Modules.CraftScript.CraftScriptRegistry;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ScriptState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.System.ScriptStarter.ScriptThreadManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        CraftScriptRegistry.init();
        long current = System.currentTimeMillis();
        System.out.println("Starting parse...");
        String[] script = new String[]{
                "print 'hello world'",
                "print 'program to work and not to feel'",
                "print 'not even altough light...'",
                "print 'hello world'",
        };
        List<String> keyword = new ArrayList<>(Arrays.asList(script));
        ScriptState sm = new ScriptState(Arrays.asList(script));


        System.out.println("Warming up...");
        for(int i = 0;i < 1000;i++)
            sm= new ScriptState(keyword);
        System.out.println("Parsing...");
        for(int i = 0;i < 100000;i++)
            sm= new ScriptState(keyword);
        System.out.println("Parsing time: " + (System.currentTimeMillis() - current) + "ms");
        System.out.println("Processing....");
//        System.out.println("Processing result = " + csc.process(new ExecuteState()));
        System.out.println("Complete.");

//        long current = System.currentTimeMillis();
//        String[] script = {
//                "set 'test' to 'hello world4'",
//                "if {test} is 'hello world'",
//                "print 'Woah, Variable test is hello world!'",
//                "endif"
//        };
//
//        script = new String[]{
//            "set 'midi' to sequencer",
//                "{midi} load 'T:\\Coward Montblanc.mid'",
//                "{midi} play"
//        };
//        ScriptState sm = new ScriptState(Arrays.asList(script));
//        sm.queueScript();

//        CraftScriptClass csc = CraftScriptRegistry.parseString("pause 10 second");
//        csc.processScript(new ExecuteState());
//        ExecuteState state = new ExecuteState();
//       for(String n : script){
//           CraftScriptRegistry.parseString(n).processScript(state);
//       }
//        System.out.println("Parsing time: " + (System.currentTimeMillis() - current) + "ms");
//        System.out.println("Processing....");
//        System.out.println("Processing result = " + csc.processScript(new ExecuteState() ));
//        System.out.println("Complete.");

//        long current = System.currentTimeMillis();
//        System.out.println("Starting parse...");
//        CraftScriptClass csc = CraftScriptRegistry.parseString("print 'Hello World, Programmed to work and not to feel' 20.0f?[cast=type]?");
//        System.out.println("Warming up...");
//        for(int i = 0;i < 100000;i++)
//            csc.processScript(new ExecuteState());
//        System.out.println("Processing script....");
//        for(int i = 0;i < 10000000;i++)
//            csc.processScript(new ExecuteState());
//        System.out.println("Processing time: " + (System.currentTimeMillis() - current) + "ms");
//        System.out.println("Complete.");
    }
}
