package skywolf46.ServerEdit.Modules.CraftScript;

import org.bukkit.command.CommandSender;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ScriptState;
import skywolf46.ServerEdit.Modules.CraftScript.System.ScriptStarter.ScriptThreadManager;
import skywolf46.ServerEdit.ServerEdit;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ScriptStorage {
    private static HashMap<String, HashMap<String, ScriptState>> scripts = new HashMap<>();

    public static int load(File f, CommandSender se) {
        AtomicInteger val = new AtomicInteger(0);
        String fileName = f.getName();
//        int indx = fileName.indexOf(".");
//        if (indx != -1)
//            fileName = fileName.substring(0, indx);
        scripts.put(fileName, ScriptReader.readScript(f, (file, s, e) -> {
            se.sendMessage("§c" + e.getMessage());
            val.addAndGet(1);
        }));
        if (scripts.get(fileName) != null && scripts.get(fileName).get("construct") != null)
            scripts.get(fileName).get("construct")
                    .queueScript();
        return val.get();
    }

    public static int reload(CommandSender cs) {
        cs.sendMessage("§6CraftScript | §7Stopping scripts...");
        ScriptThreadManager.stopCurrent();
        cs.sendMessage("§6CraftScript | §7Reloading...");
        long ms = System.currentTimeMillis();
        for (HashMap<String, ScriptState> st : scripts.values())
            if (st != null && st.get("destruct") != null)
                st.get("destruct").queueScript();
        scripts.clear();
        File target = new File("plugins/ServerEdit/CraftScript/Scripts");
        if (target.exists()) {
            if (target.isDirectory()) {
                int err = 0;
                for (File f : target.listFiles())
                    if (f.getName().endsWith(".cscript"))
                        err += load(f, cs);
                cs.sendMessage("§6CraftScript | §aScript reloaded with " + err + " error in " + (System.currentTimeMillis() - ms) + "ms");
                return err;
            } else
                target.delete();
        }
        target.getParentFile().mkdirs();
        ServerEdit.getInst().saveResource("CraftScript/Scripts/Example.cscript", true);
        int err = 0;
        for (File f : target.listFiles())
            if (f.getName().endsWith(".cscript"))
                err += load(f, cs);
        cs.sendMessage("§6CraftScript | §aScript reloaded with " + err + " error in " + ms + "ms");
        return err;
    }

    public static ScriptState getScript(String scriptFile, String scriptName) {
        HashMap<String, ScriptState> state = scripts.get(scriptFile);
        if (state == null)
            return null;
        return state.get(scriptName);
    }
}
