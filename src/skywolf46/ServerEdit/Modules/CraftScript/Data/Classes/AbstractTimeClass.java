package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes;

import skywolf46.ServerEdit.Modules.CraftScript.Extension.PatternMatchingClass;

public abstract class AbstractTimeClass extends PatternMatchingClass {
    @Override
    public String getClassPath() {
        return "native.time";
    }

    // Check cl < this
    public boolean isBeforeThen(AbstractTimeClass cl) {
        // this = RelativeTimeClass
        if (this instanceof RelativeTimeClass) {
            // cl = this = RelativeTimeClass
            //
            if (cl instanceof RelativeTimeClass) {
//                if(cl.getYear() < getYear())
//                    return true;
                return afterThanMonth(cl);
            } else {
                // Real-Time to Relative time
                // RealTime < RelativeTime
                // Do not match year.
                // RelativeTime class not have year variable.
                return afterThanMonth(cl);
            }

        } else if (cl instanceof RelativeTimeClass) {
            // RelativeTime < RealTime
            return afterThanMonth(cl);
        } else {
            // RealTime < RealTime
            return afterThanYear(cl);
        }
    }

    public boolean isAfterThen(AbstractTimeClass cl) {
        return true;
    }

    public boolean afterThanYear(AbstractTimeClass cl) {
        if (cl.getYear() < getYear())
            return true;
        if (cl.getYear() == getYear())
            return afterThanMonth(cl);
        return false;
    }

    public boolean afterThanMonth(AbstractTimeClass cl) {
        if (cl.getMonth() < getMonth())
            return true;
        if (cl.getMonth() == getMonth())
            return afterThanDate(cl);
        return false;
    }

    public boolean afterThanDate(AbstractTimeClass cl) {
        if (cl.getDate() < getDate())
            return true;
        if (cl.getDate() == getDate())
            return afterThanHour(cl);
        return false;
    }

    public boolean afterThanHour(AbstractTimeClass cl) {
        if (cl.getHour() < getHour())
            return true;
        if (cl.getHour() == getHour())
            return afterThanMinute(cl);
        return false;
    }

    public boolean afterThanMinute(AbstractTimeClass cl) {
        if (cl.getMinute() < getMinute())
            return true;
        if (cl.getMinute() == getMinute())
            return afterThanSecond(cl);
        return false;
    }


    public boolean afterThanSecond(AbstractTimeClass cl) {
        if (cl.getSecond() < getSecond())
            return true;
        return false;
    }

    public abstract int getYear();

    public abstract int getMonth();

    public abstract int getDate();

    public abstract int getHour();

    public abstract int getMinute();

    public abstract int getSecond();

    @Override
    public int processPriority() {
        return 0;
    }
}
