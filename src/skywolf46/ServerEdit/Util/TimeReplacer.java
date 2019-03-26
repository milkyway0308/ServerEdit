package skywolf46.ServerEdit.Util;

public class TimeReplacer {
    public static int parseTime(String time){
        int parsed = 0;
        int lastParse = 0;
        for(int i = 0;i < time.length();i++){
            char c = time.charAt(i);
            if(c >= '0' && c <= '9')
                lastParse = (lastParse * 10) + (c - '0');
            else
                switch (c){
                    case 'h':
                        lastParse = Math.multiplyExact(lastParse,60);
                    case 'm':
                        lastParse = Math.multiplyExact(lastParse,60);
                    case 's':
                        parsed = Math.addExact(parsed,lastParse);
                        lastParse = 0;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected time delimiter [" + c + "] in {" + time + "} at char " + i);
                }
        }
        return parsed;
    }

    public static void main(String[] args) {
        System.out.println(parseTime("24h20s4m"));
    }


}
