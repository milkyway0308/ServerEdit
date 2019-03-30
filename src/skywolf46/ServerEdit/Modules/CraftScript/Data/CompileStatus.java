package skywolf46.ServerEdit.Modules.CraftScript.Data;

import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;

import java.util.Arrays;
import java.util.HashMap;

public class CompileStatus {
    private HashMap<Integer, CraftScriptClass> forceApplyClass = new HashMap<>();

    private int currentIndex = 0;
    private long accessKey;

    private CraftScriptClass[] scriptClass;
    private String[] strings;
    private HashMap[] attributes;
    private HashMap<String,Object> lineAttribute = new HashMap<>();
    private String[] original;
    private boolean unlocked = false;


    @Override
    public CompileStatus clone(){
        CompileStatus cs = new CompileStatus();
        cs.currentIndex = currentIndex;
        cs.scriptClass = Arrays.copyOf(scriptClass,scriptClass.length);
        cs.strings = Arrays.copyOf(strings,strings.length);
        cs.attributes = new HashMap[attributes.length];
        for(int i = 0;i < attributes.length;i++)
            cs.attributes[i] = new HashMap(attributes[i]);
        cs.unlocked = true;
        cs.currentIndex = currentIndex;
        cs.lineAttribute = lineAttribute;
        cs.original = original;
        return cs;
    }

    private CompileStatus(){

    }

    public CompileStatus(String[] compile, long accessKey) {
        this.accessKey = accessKey;
        strings = compile;
        original = Arrays.copyOf(strings,strings.length);
        scriptClass = new CraftScriptClass[compile.length];
        attributes = new HashMap[strings.length];
        for(int i = 0;i < strings.length;i++){
            attributes[i] = new HashMap();
            String val = strings[i];
            if(val.endsWith("]")){
                if(val.contains("[")){
                    val = val.substring(0,val.length()-1);
                    int indx = val.lastIndexOf("[");
                    strings[i] = val.substring(0,indx);
                    String sbstrng = val.substring(indx+1);
                    String[] attr = sbstrng.split(",");
                    for(String n : attr){
                        indx = n.indexOf("=");
                        if(indx != -1)
                            attributes[i].put(n.substring(0,indx),n.substring(indx+1));
                        else
                            attributes[i].put(n,null);
                    }
                }
            }
        }
    }

    public CraftScriptClass addIndex(long accessKey) {
        if (accessKey != this.accessKey)
            throw new IllegalStateException("Access key not match");
        return this.scriptClass[currentIndex++];
    }

    public void forceApply(int index, CraftScriptClass csc) {
        if (this.scriptClass[index] != null)
            throw new IllegalStateException("Already defined class");
        this.scriptClass[index] = csc;
    }

    public void set(int index, CraftScriptClass csc) {
        this.scriptClass[index] = csc;
    }

    public String getString(int index){
        return strings[index];
    }

    public CraftScriptClass get(int index){
        return scriptClass[index];
    }

    public int length(){
        return scriptClass.length;
    }

    public int stringLength(){
        return strings.length;
    }

    public HashMap<String,String> attribute(int index){
        return new HashMap<>(attributes[index]);
    }

    public CraftScriptClass[] getAll(){
        return Arrays.copyOf(scriptClass,scriptClass.length);
    }
    public CraftScriptClass[] getOriginalIndex(long accessKey) {
        if (accessKey != this.accessKey)
            throw new IllegalStateException("Access key not match");
        return scriptClass;
    }

    public void updateAttributes(HashMap[] attr) {
        this.attributes = attr;
    }

    public void updateArray(CraftScriptClass[] cls2) {
        this.scriptClass = cls2;
    }

    public void updateStringArray(String[] reIndexed) {
        this.strings = reIndexed;
    }

    public <T> T getLineAttribute(String attrName){
        return (T) lineAttribute.get(attrName);
    }


    public void remove(int i) {
        if(i <= 0 || i >= length())
            return;
        int orig = length();
        CraftScriptClass[] cl = new CraftScriptClass[length()-1];
        String[] st = new String[stringLength()-1];
        HashMap[] at = new HashMap[st.length];
        int fill = 0;
        for(int x = 0;x < orig;x++){
            if(x == i)
                continue;
            cl[fill] = get(x);
            st[fill] = getString(x);
            at[fill++] = attribute(x);
        }
        this.scriptClass = cl;
        this.strings = st;
        this.attributes = at;
    }

    public String[] getOriginal(){
        return Arrays.copyOf(strings,strings.length);
    }

    public String getOriginal(int i){
        return original[i];
    }
}
