package skywolf46.ServerEdit.Data.Events;

import skywolf46.ServerEdit.Abstract.StringReplaceableData;

import java.util.HashMap;

public abstract class ServerEditEvent extends StringReplaceableData{
    public abstract String getEventName();
}
