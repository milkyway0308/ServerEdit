package skywolf46.ServerEdit.Util.MessageAssist;

public abstract class AbstractMessageSender {
    public abstract AbstractMessageSender create();
    public abstract AbstractMessageSender append(String n);
    public abstract AbstractMessageSender appendLink(String n,String link);
    public abstract AbstractMessageSender appendRecommendCommand(String n,String recommend);
    public abstract AbstractMessageSender appendExecuteCommand(String n,String cmd);
}
