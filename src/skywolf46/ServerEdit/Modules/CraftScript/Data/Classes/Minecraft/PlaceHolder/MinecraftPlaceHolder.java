package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Minecraft.PlaceHolder;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Abstract.AbstractPlaceHolder;

public abstract class MinecraftPlaceHolder extends AbstractPlaceHolder {
    @Override
    public String getClassPath() {
        return "native.minecraft";
    }
}
