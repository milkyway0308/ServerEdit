package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.regex.Matcher;

public class LeftEventTimeVariable extends RelativeTimeClass {
    private static HashMap<String, Calendar> expire = new HashMap<>();
    private static HashMap<String, Consumer<String>> expireRunner = new HashMap<>();

    private String name;

    public LeftEventTimeVariable(String name) {
        super(0, 0, 0, 0, 0);
        this.name = name;
    }

    @Override
    public String getClassName() {
        return "LeftEventTime";
    }

    @Override
    public int getYear() {
        return -2;
    }

    @Override
    public int getMonth() {
        return -2;
    }

    @Override
    public int getDate() {
        Calendar c = expire.get(name);
        if (c == null)
            return 0;
        long sec = ChronoUnit.SECONDS.between(Calendar.getInstance().toInstant(), c.toInstant());
        long toDay = TimeUnit.SECONDS.toDays(sec);
        sec -= TimeUnit.DAYS.toSeconds(toDay);
        long toHour = TimeUnit.SECONDS.toHours(sec);
        sec -= TimeUnit.HOURS.toSeconds(toHour);
        long toMin = sec / 60;
        sec -= toMin * 60;
        return (int) toDay;
    }

    @Override
    public int getHour() {
        Calendar c = expire.get(name);
        if (c == null)
            return 0;
        long sec = ChronoUnit.SECONDS.between(Calendar.getInstance().toInstant(), c.toInstant());
        long toDay = TimeUnit.SECONDS.toDays(sec);
        sec -= TimeUnit.DAYS.toSeconds(toDay);
        long toHour = TimeUnit.SECONDS.toHours(sec);
        sec -= TimeUnit.HOURS.toSeconds(toHour);
        long toMin = sec / 60;
        sec -= toMin * 60;
        return (int) toHour;
    }

    @Override
    public int getMinute() {
        Calendar c = expire.get(name);
        if (c == null)
            return 0;
        long sec = ChronoUnit.SECONDS.between(Calendar.getInstance().toInstant(), c.toInstant());
        long toDay = TimeUnit.SECONDS.toDays(sec);
        sec -= TimeUnit.DAYS.toSeconds(toDay);
        long toHour = TimeUnit.SECONDS.toHours(sec);
        sec -= TimeUnit.HOURS.toSeconds(toHour);
        long toMin = sec / 60;
        sec -= toMin * 60;
        return (int) toMin;
    }

    @Override
    public int getSecond() {
        Calendar c = expire.get(name);
        if (c == null)
            return 0;
        long sec = ChronoUnit.SECONDS.between(Calendar.getInstance().toInstant(), c.toInstant());
        long toDay = TimeUnit.SECONDS.toDays(sec);
        sec -= TimeUnit.DAYS.toSeconds(toDay);
        long toHour = TimeUnit.SECONDS.toHours(sec);
        sec -= TimeUnit.HOURS.toSeconds(toHour);
        long toMin = sec / 60;
        sec -= toMin * 60;
        return (int) sec;
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {

    }

    public static void addExpire(String user, Calendar expire, Consumer<String> expireRunner) {
        if (expire.before(Calendar.getInstance())) {
            expireRunner.accept(user);
            return;
        }
        LeftEventTimeVariable.expire.put(user, expire);
        LeftEventTimeVariable.expireRunner.put(user, expireRunner);
    }

    public static void tick() {
        Iterator<Map.Entry<String, Calendar>> iterate = expire.entrySet().iterator();
        while (iterate.hasNext()) {
            Map.Entry<String, Calendar> next = iterate.next();
            if (Calendar.getInstance().after(next.getValue())) {
                iterate.remove();
                expireRunner.get(next.getKey()).accept(next.getKey());
            } else {

                //todo loop options
            }
        }
    }

    public static void startTestThread(){
        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        tick();
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        return VoidOverInfinityClass.STATIC;
    }

    @Override
    public CraftScriptClass createInstance(Matcher mc) {

        return null;
    }

    @Override
    public List<String> getClassPattern() {
        return Arrays.asList("LeftEventTime\\|(.*?)");
    }

    @Override
    public int processPriority() {
        return 0;
    }
}
