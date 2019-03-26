package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

public class RelativeTimeClass extends AbstractTimeClass {
    // 0 = Default Time
    // 0< = Relative Time
    // 1 = Month/Date/Hour/Minute/Second
    // 2 = Date/Hour/Minute/Second
    // 3 = Hour/Minute:Date
    private int[] timeParam = new int[]{
            -1, -1,
            -1, -1, -1
    };

    private static int[] dates = new int[]{
            31, 29 /* On non-leap year,28*/, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public RelativeTimeClass(int month, int date, int hour, int minute, int second) {
        this.timeParam = new int[]{
                month, date, hour, minute, second
        };
        this.init();
    }

    RelativeTimeClass(int[] array) {
        this.timeParam = array;
    }


    private void init() {
        timeParam[0]--;
        for (int i = 0; i < timeParam.length; i++) {
            if (timeParam[i] != -1 && timeParam[i] < 0)
                timeParam[i] = 0;
        }

        while (timeParam[4] > 60) {
            if (timeParam[3] != -1)
                timeParam[3]++;
            timeParam[4] -= 60;
        }

        while (timeParam[3] > 60) {
            if (timeParam[2] != -1)
                timeParam[2]++;
            timeParam[3] -= 60;
        }

        while (timeParam[2] > 24) {
            if (timeParam[1] != -1)
                timeParam[1]++;
            timeParam[2] -= 24;
        }
        int max_date = Math.min(12, dates[0]);
        while (timeParam[1] > max_date) {
            if (timeParam[0] != -1)
                timeParam[0]++;
            timeParam[1] -= max_date;
        }
        if (timeParam[0] != -1) {
            while (timeParam[0] > 12)
                timeParam[0] -= 12;
            timeParam[0] = Math.max(1, timeParam[0]);
        }
    }

    @Override
    public String getClassName() {
        return "RelativeTime";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {

    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        return VoidOverInfinityClass.STATIC;
    }


    @Override
    public CraftScriptClass createInstance(Matcher mc) {
        String[] split = mc.group(0).split(":");
//        System.out.println(Arrays.asList(split));
        int[] timeArray = new int[]{-1, -1, -1, -1, -1};
        int copy = 0;
        for (int i = 5 - split.length; i < 5; i++) {
//            System.out.println(split.length- (timeArray.length - i-1)-1);
            int index = split.length - (timeArray.length - i - 1) - 1;
            if (split[index].equals("∞")) {
                timeArray[i] = -2;
                continue;
            }
            timeArray[i] = Integer.parseInt(split[index]);
//            System.out.println(timeArray[i]);
        }
//        System.out.println(Arrays.toString(timeArray));
        return new RelativeTimeClass(timeArray);
    }

    @Override
    public List<String> getClassPattern() {
        return Arrays.asList(
                "(?:(\\d+:)|(∞:)){1,4}(?:(\\d+)|∞)"
        );
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (str,curindex,status) -> {
            List<Integer> indexes = new ArrayList<>();
            int index = -1;
            while (true) {
                index = str.indexOf(':', index + 1);
                if (index == -1)
                    break;
                indexes.add(index);
            }
            int[] arr = new int[]{-1, -1, -1, -1, -1};
            if (indexes.size() >= 1 && indexes.size() <= 4) {
                int start = -1;
                try {
                    for (int i = 0; i < indexes.size(); i++) {
                        int a = indexes.get(i);
                        String substring = str.substring(start + 1, a);
//                        System.out.println("Cutted " + substring);
                        int indx = arr.length - (indexes.size()+1)+i;
                        if (substring.equals("∞"))
                            arr[indx] = -2;
                        else {
                            int parsed = Integer.parseInt(substring);
                            if (parsed < 0)
                                return null;
                            arr[indx] = parsed;
//                            System.out.println("Parsed " + parsed);
                        }
                        start = a;
                    }
                    String substring = str.substring(start + 1);
//                    System.out.println("Cutted " + substring);
                    if (substring.equals("∞"))
                        arr[arr.length-1] = -2;
                    else {
                        int parsed = Integer.parseInt(substring);
                        if (parsed < 0)
                            return null;
                        arr[arr.length-1] = parsed;
//                        System.out.println("Parsed " + parsed);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
                return new RelativeTimeClass(arr);
            }
            return null;
        };
    }

    @Override
    public int getYear() {
        return -1;
    }

    @Override
    public int getMonth() {
        return timeParam[0];
    }

    @Override
    public int getDate() {
        return timeParam[1];
    }

    @Override
    public int getHour() {
        return timeParam[2];
    }

    @Override
    public int getMinute() {
        return timeParam[3];
    }

    @Override
    public int getSecond() {
        return timeParam[4];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RealTimeClass)
            return obj.equals(this);
        else if (obj instanceof RelativeTimeClass) {
            for (int i = 0; i < timeParam.length; i++)
                if (((RelativeTimeClass) obj).timeParam[i] != timeParam[i])
                    return false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return Arrays.toString(timeParam);
    }
}
