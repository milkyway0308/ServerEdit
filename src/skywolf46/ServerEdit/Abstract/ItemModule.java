package skywolf46.ServerEdit.Abstract;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public interface ItemModule {
    void disableModule();
    void enableModule();
    ItemModule createModule(CommandSender reloader, ConfigurationSection section, String moduleName);
}
