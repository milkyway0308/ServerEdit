package skywolf46.ServerEdit.Modules.CraftScript;

import skywolf46.ServerEdit.Modules.CraftScript.Data.AfterProcessor.CastingProcessor;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.AtConditionHelper;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.MIDI.MIDILoadFile;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.MIDI.MIDIPlayFile;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.MIDI.MIDISequencer;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.MIDI.MIDIVolume;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Interface.CastableScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Invokers.ConsolePrintInvoker;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Invokers.DebuggingInvoker;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Invokers.SetVariableInvoker;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Invokers.WaitScriptInvoker;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Minecraft.Invoker.BroadcastInvoker;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Minecraft.Invoker.LoadScriptCommandInvoker;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Minecraft.Invoker.SendMessageInvoker;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Minecraft.PlaceHolder.CommandPlaceHolder;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.DoubleClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.FloatClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.IntegerClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.StringClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.TimeUnit.SecondUnit;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.System.ThreadNumberVariable;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.System.TypeOfVariable;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Variables.GetVariable;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.*;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.LeftEventTimeVariable;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.RealTimeClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.RelativeTimeClass;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.PatternMatchingClass;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class CraftScriptRegistry {
    private static List<CraftScriptClass> patternScripts = new ArrayList<>();
    private static List<CraftScriptClass> instantParsingScripts = new ArrayList<>();
    private static List<Class> registered = new ArrayList<>();
    private static final Object lock = new Object();
    private static boolean isInitialized = false;
    private static HashMap<Class<? extends PreprocessProcessor>, PreprocessProcessor> processor = new HashMap<>();

    public static void register(CraftScriptClass cls) {
        synchronized (lock) {
            if (registered.contains(cls.getClass())) {
                throw new IllegalStateException("Class already registered");
            }
            registered.add(cls.getClass());
            if (cls instanceof PatternMatchingClass)
                patternScripts.add(cls);
            else
                instantParsingScripts.add(cls);
        }
    }

    public static void register(PreprocessProcessor pre) {
        if (processor.containsKey(pre.getClass()))
            throw new IllegalStateException("Already registered processor");
        processor.put(pre.getClass(), pre);
    }

    private static Random r = new Random();

    public static void parseAndDebug(String toParse, Consumer<CompileStatus> debug){
        parseString(toParse,(c,l) -> {
            debug.accept(c);
            return new DefaultORCondition(c,l);
        });
    }

    public static CraftScriptClass parseString(String s){
        return parseString(s, DefaultORCondition::new);
    }

    private static CraftScriptClass parseString(String s, BiFunction<CompileStatus,Long,CraftScriptClass> finalizer) {
        String[] split = s.split(" ");
        CraftScriptClass[] cls = new CraftScriptClass[split.length];
        long access = System.currentTimeMillis() * (r.nextInt(392));
        CompileStatus st = new CompileStatus(split, access);
        for (int i = 0; i < st.stringLength(); i++) {
            try {
                if (st.get(i) == null) {
                    CraftScriptClass clc = parse(st, st.getString(i), i);
                    if (clc == null)
                        throw new Exception("Parse failed in index " + i + " / Parse string \"" + st.getString(i) + "\" failed");
                    st.set(i, clc);
                }
//                System.out.println("Parse process in step " + (i + 1) + " in " + split.length + " complete : Parsed from class " + cls[i].get().getSimpleName());
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
        cls = st.getOriginalIndex(access);
        List<Integer> ignoredIndex = new ArrayList<>();
//        int highestIndex = 0;
        for (int i = 0; i < st.length(); i++) {
            if (st.get(i).isIgnored()) {
                ignoredIndex.add(i);
                st.get(i).onIgnore(Arrays.copyOf(cls, cls.length), i);
            }
        }
        if (ignoredIndex.size() > 0) {
            CraftScriptClass[] cls2 = new CraftScriptClass[st.length() - ignoredIndex.size()];
            HashMap[] attr = new HashMap[cls2.length];
            int filled = 0;
            for (int i = 0; i < cls.length; i++)
                if (!ignoredIndex.contains(i)) {
                    cls2[filled] = cls[i];
                    attr[filled++] = st.attribute(i);
                }
            st.updateAttributes(attr);
            st.updateArray(cls2);
        }

        for (int i = 0; i < st.length(); i++)
            st.get(i).applyData(st, i);
        return finalizer.apply(st,access);
    }

    private static CraftScriptClass parse(CompileStatus st, String s, int index) {
        for (CraftScriptClass cls : instantParsingScripts) {
            CraftScriptClass parsed = cls.getClassParser().apply(s, index, st);
            if (parsed != null)
                return parsed;
        }

        // Normally, pattern script parser is slower than instant parser
        // Priority will be put at last
        for (CraftScriptClass cls : patternScripts) {
            CraftScriptClass parsed = cls.getClassParser().apply(s, index, st);
            if (parsed != null)
                return parsed;
        }
        return null;
    }




    public static void init() {
        if (isInitialized)
            throw new IllegalStateException("Already initialized");
        register(new AfterCondition(get("after")));
        register(new BeforeCondition(get("before")));
        register(new ForceRequireCondition(get("require")));
        register(new EqualsCondition(get("equal", "equals")));
        register(new RealTimeClass(Calendar.getInstance()));
        register(new RelativeTimeClass(1, 1, 1, 1, 1));
        register(new ThenConditionHelper());
        register(new LeftEventTimeVariable("NoVariableExist"));
        register(new IntegerClass(0));
        register(new FloatClass(0f));
        register(new DoubleClass(0d));
        register(new ConsolePrintInvoker());
        register(new WaitScriptInvoker());
        register(new SecondUnit());
        register(new ThreadNumberVariable());
        // Do not enable this
        // When this enable, all fallback will change to string
        register(new StringClass(""));
        register(new GetVariable(""));
        register(new IFCondition());
        register(new EndIFCondition());
        register(new MIDISequencer());
        register(new MIDILoadFile());
        register(new MIDIPlayFile());
        register(new MIDIVolume());
        register(new CastingProcessor());
        register(new SetVariableInvoker(null,null));
        register(new ISTypeComparator());
        register(new AtConditionHelper());
        register(new DebuggingInvoker(null));
        register(new ToVariableConnectCondition());
        CastableScriptClass.registerGlobalCaster("type",(fromto,sc) -> new TypeOfVariable(sc));
    }

    public static void initMinecraft(){
        init();
        register(new CommandPlaceHolder());
        register(new BroadcastInvoker());
        register(new SendMessageInvoker());
        register(new LoadScriptCommandInvoker());
    }

    private static String[] get(String... str) {
        return str;
    }

    public static List<PreprocessProcessor> getPreprocessors() {
        return new ArrayList<>(processor.values());
    }
}
