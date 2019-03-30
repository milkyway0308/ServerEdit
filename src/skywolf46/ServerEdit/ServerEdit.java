package skywolf46.ServerEdit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import skywolf46.CommandAnnotation.API.MinecraftAbstractCommand;
import skywolf46.ServerEdit.Modules.Commands.BookEditor.BookHelpCommand;
import skywolf46.ServerEdit.Modules.Commands.BookEditor.ChangeBookOwnerCommand;
import skywolf46.ServerEdit.Modules.Commands.BookEditor.DismantleBookCommand;
import skywolf46.ServerEdit.Modules.Commands.BookEditor.EditPageContentCommand;
import skywolf46.ServerEdit.Modules.Commands.ItemEditor.Attribute.DamageAttributeCommand;
import skywolf46.ServerEdit.Modules.Commands.ItemEditor.Display.SetDisplayCommand;
import skywolf46.ServerEdit.Modules.Commands.ItemEditor.HelpCommand;
import skywolf46.ServerEdit.Modules.Commands.ItemEditor.ItemInfo.ChangeItemCommand;
import skywolf46.ServerEdit.Modules.Commands.ItemEditor.ItemInfo.ClearItemCommand;
import skywolf46.ServerEdit.Modules.Commands.ItemEditor.ItemInfo.Flags.*;
import skywolf46.ServerEdit.Modules.Commands.ItemEditor.ItemInfo.ItemHelpCommand;
import skywolf46.ServerEdit.Modules.Commands.ItemEditor.ItemInfo.SetUnbreakableCommand;
import skywolf46.ServerEdit.Modules.Commands.ItemEditor.Lores.*;
import skywolf46.ServerEdit.Modules.CraftScript.Commands.ReloadScriptCommand;
import skywolf46.ServerEdit.Modules.CraftScript.CraftScriptRegistry;
import skywolf46.ServerEdit.Modules.CraftScript.ScriptStorage;

public class ServerEdit extends JavaPlugin {
    private static ServerEdit inst;

    public static ServerEdit getInst() {
        return inst;
    }

    @Override
    public void onEnable() {
        inst = this;
        MinecraftAbstractCommand.builder()
                .command("/craftscript","/cscript")
                .add(new ReloadScriptCommand())
                .complete();
        MinecraftAbstractCommand.builder()
                .command("/sei", "/serveredititem", "/seitem")
                .add(new HelpCommand())
                .child("lore", new LoreHelpCommand(), true)
                .command("lr")
                .child("add", new AddLoreCommand())
                .child("clear", new ClearLoreCommand())
                .child("remove", new RemoveLoreCommand())
                .child("replace", new ReplaceLoreCommand(), true)
                .command("rp")
                .parent()
                .parent()
                .child("name", new SetDisplayCommand(), true)
                .command("display")
                .parent()
                .child("item", new ItemHelpCommand(), true)
                .command("it")
                .child("changeitem", new ChangeItemCommand(), true)
                .command("change", "ic")
                .parent()
                .child("clear", new ClearItemCommand(), true)
                .command("cl")
                .parent()
                .child("unbreakable", new SetUnbreakableCommand(), true)
                .command("unbreak", "ub")
                .parent()
                .child("flag", new FlagHelpCommand(), true)
                .command("fl")
                .child("flags", new FlagListCommand(), true)
                .command("flaglist", "list")
                .parent()
                .child("add", new AddFlagCommand())
                .child("remove", new RemoveFlagCommand())
                .child("clear", new ClearFlagCommand())
                .parent()
                .parent()
                .child("test", new DamageAttributeCommand())
                .complete();
        MinecraftAbstractCommand.builder()
                .command("/seb", "/servereditbook", "/sebook")
                .add(new BookHelpCommand())
                .child("owner", new ChangeBookOwnerCommand(), true)
                .command("changeowner", "co")
                .parent()
                .child("edit", new EditPageContentCommand(), true)
                .command("editpage", "ep")
                .parent()
                .child("dismantle", new DismantleBookCommand(), true)
                .command("dm")
                .parent()
                .complete();

        CraftScriptRegistry.initMinecraft();
        ScriptStorage.reload(Bukkit.getConsoleSender());
    }
}
