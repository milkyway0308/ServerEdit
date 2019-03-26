package skywolf46.ServerEdit.Abstract;

import java.util.HashMap;

public abstract class StringReplaceableData {
    private HashMap<String, StringReplaceableData> data = new HashMap<>();

    public abstract String asString();


    protected StringReplaceableData setData(String s,StringReplaceableData dat){
        this.data.put(s,dat);
        return this;
    }

    public StringReplaceableData getData(String s){
        return data.get(s);
    }


}
