package skywolf46.ServerEdit.Abstract;

public abstract class NamedModule implements ItemModule{
    private String name;
    private boolean isEnabled;

    public NamedModule(String name){
        this.name = name;
    }

    @Override
    public void disableModule() {
        isEnabled = false;
    }

    @Override
    public void enableModule() {
        isEnabled = true;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public abstract String getModuleName();
}
