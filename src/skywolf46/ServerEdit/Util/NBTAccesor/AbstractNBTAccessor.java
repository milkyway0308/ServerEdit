package skywolf46.ServerEdit.Util.NBTAccesor;

public abstract class AbstractNBTAccessor {
    private Object inst;

    public Object getNBT(){
        return inst;
    }

    protected void setNBT(Object nbt){
        this.inst = nbt;
    }
}
