package skywolf46.ServerEdit.Abstract;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import skywolf46.CommandAnnotation.API.MinecraftAbstractCommand;
import skywolf46.CommandAnnotation.Data.CommandArgument;

public abstract class CommandItemModule extends MinecraftAbstractCommand implements ItemModule{
    private boolean isEnabled = true;

    public abstract void onCommandType(CommandArgument arg);

    @Override
    public boolean onCommand(CommandArgument commandArgument) {
        if(isEnabled)
            onCommandType(commandArgument);
        return false;
    }

    @Override
    public int getCommandPriority() {
        return 0;
    }

    @Override
    public void disableModule() {
        isEnabled = false;
    }

    @Override
    public void enableModule() {
        isEnabled = true;
    }

    public abstract String getModuleName();

    @Override
    public ItemModule createModule(CommandSender reloader, ConfigurationSection section, String moduleName) {
        return null;
    }
}
