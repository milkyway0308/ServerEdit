package skywolf46.ServerEdit.Modules.ClearFlow;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import skywolf46.ServerEdit.Abstract.ItemModule;
import skywolf46.ServerEdit.Abstract.NamedModule;
import skywolf46.ServerEdit.Modules.ClearFlow.Util.EntityReplacer;
import skywolf46.ServerEdit.Util.PatternReplacer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class ClearFlowModule extends NamedModule {
    private boolean enabled = true;
    private int delay;
    private int leftDelay;
    private Set<EntityType> targets = new HashSet<>();
    private List<Pattern> targetWorlds = new ArrayList<>();

    public ClearFlowModule(String name) {
        super(name);
    }

    public ClearFlowModule(CommandSender reloader, String name, ConfigurationSection config) {
        this(name);
        long ms = System.currentTimeMillis();
        List<String> entity = config.getStringList("Clear target");
        for (String n : entity) {
            try {
                Pattern pat = PatternReplacer.replace(n);
                targets.addAll(EntityReplacer.replace(pat));
            } catch (Exception ex) {
                reloader.sendMessage("§9ClearFlow §6| §cClearFlow module \"" + name + "\" failed to add entity delimiter §n'" + n + "'§c : Pattern compile failed");
            }
        }
        entity = config.getStringList("Target world");
        for (String n : entity) {
            try {
                targetWorlds.add(PatternReplacer.replace(n));
            } catch (Exception ex) {
                reloader.sendMessage("§9ClearFlow §6| §cClearFlow module \"" + name + "\" failed to add world delimiter §n'" + n + "'§c : Pattern compile failed");
            }
        }
        reloader.sendMessage("§9ClearFlow §6| §aClearFlow module \"" + name + "\" loaded in " + (System.currentTimeMillis() - ms) + "ms with §b" + targetWorlds.size() + " delimiter and " + targets.size() + " entity types");
    }

    @Override
    public String getModuleName() {
        return "ClearFlow";
    }

    @Override
    public void disableModule() {
        enabled = false;
    }

    @Override
    public void enableModule() {
        enabled = true;
    }

    @Override
    public ItemModule createModule(CommandSender reloader, ConfigurationSection section, String moduleName) {
        return new ClearFlowModule(reloader, moduleName, section);
    }
}
