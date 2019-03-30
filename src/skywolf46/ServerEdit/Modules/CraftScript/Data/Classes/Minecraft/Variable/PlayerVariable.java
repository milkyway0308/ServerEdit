package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Minecraft.Variable;

import org.bukkit.entity.Player;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Minecraft.Abstract.MinecraftVariable;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

public class PlayerVariable extends MinecraftVariable {
    private Player p;

    public PlayerVariable(Player p) {
        this.p = p;
    }

    @Override
    public String getClassName() {
        return "Player";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {

    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s, i, c) -> null;
    }

    public Player getPlayer(){
        return p;
    }
}
