package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;

public class RealTimeClass extends AbstractTimeClass {
    private Calendar c;

    public RealTimeClass(Calendar c) {
        this.c = c;
    }

    @Override
    public int getYear() {
        return c.get(Calendar.YEAR);
    }

    @Override
    public int getMonth() {
        return c.get(Calendar.MONTH) + 1;
    }

    @Override
    public int getDate() {
        return c.get(Calendar.DATE);
    }

    @Override
    public int getHour() {
        return c.get(Calendar.HOUR_OF_DAY);
    }

    @Override
    public int getMinute() {
        return c.get(Calendar.MINUTE);
    }

    @Override
    public int getSecond() {
        return c.get(Calendar.SECOND);
    }

    @Override
    public String getClassName() {
        return "RealTime";
    }

    // you shall not pass
    @Override
    public void applyData(CompileStatus cl, int currentIndex) {

    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        return VoidOverInfinityClass.STATIC;
    }

    @Override
    protected CraftScriptClass createInstance(Matcher mc) {
        Calendar c = Calendar.getInstance();
        String[] toParse = mc.group(0).split(":");
        c.set(Calendar.YEAR, Math.max(1999, Integer.parseInt(toParse[0])));
        c.set(Calendar.MONTH, Math.max(1, Math.min(Integer.parseInt(toParse[1]), 12)) - 1);

        c.set(Calendar.DATE, Integer.parseInt(toParse[2]));

        c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(toParse[3]));

        c.set(Calendar.MINUTE, Integer.parseInt(toParse[4]));

        c.set(Calendar.SECOND, Integer.parseInt(toParse[5]));
        return new RealTimeClass(c);
    }

    @Override
    public List<String> getClassPattern() {
        return Arrays.asList(
                "^(?:\\d+:){5}(?:\\d+)$"
        );
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof RealTimeClass)
            return ((RealTimeClass) obj).c.equals(c);
        else if(obj instanceof RelativeTimeClass){
            RelativeTimeClass rtc = (RelativeTimeClass) obj;
            int[] timeArray = new int[]{
                    rtc.getMonth(),
                    rtc.getDate(),
                    rtc.getHour(),
                    rtc.getMinute(),
                    rtc.getSecond()
            };
            int[] calendarArray = new int[]{
                    getMonth(),
                    getDate(),
                    getHour(),
                    getMinute(),
                    getSecond()
            };
            for(int i = 0;i < timeArray.length;i++){
                if(timeArray[i] < 0)
                    continue;
                if(timeArray[i] != calendarArray[i]){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
