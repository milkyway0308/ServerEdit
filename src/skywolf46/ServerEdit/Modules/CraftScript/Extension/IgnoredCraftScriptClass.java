package skywolf46.ServerEdit.Modules.CraftScript.Extension;

import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;

public abstract class IgnoredCraftScriptClass extends CraftScriptClass {
    public abstract void onIgnore(CraftScriptClass[] parsed, int index);

    @Override
    public boolean isIgnored() {
        return true;
    }

    @Override
    public int processPriority() {
        return 0;
    }
}
