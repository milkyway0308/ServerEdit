package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Minecraft.Invoker;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import skywolf46.CommandAnnotation.API.MinecraftAbstractCommand;
import skywolf46.CommandAnnotation.Builder.MinecraftCommandBuilder;
import skywolf46.CommandAnnotation.Data.CommandArgument;
import skywolf46.ServerEdit.Modules.CraftScript.CraftScriptRegistry;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Abstract.AbstractPlaceHolder;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.AtConditionHelper;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.Extension.AbstractConditionHelper;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.ToVariableConnectCondition;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Invokers.InvokerScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Minecraft.PlaceHolder.CommandPlaceHolder;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Minecraft.PlaceHolder.MinecraftPlaceHolder;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Minecraft.Variable.PlayerVariable;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.StringClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.VoidOverInfinityClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ScriptState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.ScriptStorage;
import skywolf46.ServerEdit.Modules.CraftScript.Util.ScriptUtil;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleConsumer;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

import java.util.Arrays;
import java.util.HashMap;

public class LoadScriptCommandInvoker extends InvokerScriptClass {
    private static HashMap<Class<? extends AbstractPlaceHolder>, TripleConsumer<CompileStatus, Integer, String[]>> consumer = new HashMap<>();

    static {
        register(CommandPlaceHolder.class, (c, i, s) -> {
            CraftScriptClass csc = c.get(i + 1);
            if (csc instanceof StringClass) {
                MinecraftAbstractCommand.builder()
                        .command(csc.toString())
                        .add(new MinecraftAbstractCommand() {
                            @Override
                            public boolean onCommand(CommandArgument commandArgument) {
                                ScriptState state = ScriptStorage.getScript(s[0], s[1]);
                                if (state == null)
                                    commandArgument.get(CommandSender.class)
                                            .sendMessage("§6[CraftScript|Runtime Error] §cNo script with name \"" + s[1] + "\" in file \"" + s[0] + "\"");
                                else {
                                    ExecuteState ext = new ExecuteState();
                                    ext.addStateObject("executor", commandArgument.get(CommandSender.class));
                                    ext.addStateObject("player",commandArgument.get(Player.class) != null ? new PlayerVariable(commandArgument.get(Player.class)) : null);
//                                    System.out.println("Script state: ");
//                                    System.out.println(state);
                                    state.queueScript(ext);
                                }
                                return true;
                            }

                            @Override
                            public int getCommandPriority() {
                                return 0;
                            }
                        })
                .complete();
            }
        });
    }

    @Override
    protected void apply(CompileStatus cl, int index) {

    }

    @Override
    public String getClassName() {
        return "loader";
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        try {
            StringClass scriptName = (StringClass) st.get(1);
            StringClass scriptFile = (StringClass) st.get(2);
            System.out.println(scriptFile.toString().replace("\n","\\n"));
            System.out.println(scriptName.toString().replace("\n","\\n"));

            AbstractPlaceHolder holder = (AbstractPlaceHolder) st.get(4);
            if (consumer.containsKey(holder.getClass()))
                consumer.get(holder.getClass())
                        .consume(st, 4, new String[]{scriptFile.toString(), scriptName.toString()});
            else
                throw new IllegalStateException();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalStateException("[CraftScript|Runtime Error] Cannot process \"Script Registry Load\" invoker");
        }
        return VoidOverInfinityClass.STATIC;
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s, i, c) -> {
            if (!s.equalsIgnoreCase("load"))
                return null;
            if (i != 0)
                throw new IllegalStateException("[CraftScript|Compile Error] Load cannot position at index " + i);
            //pre-parse
            String[] parse = Arrays.copyOfRange(c.getOriginal(), 1, c.length());
            try {
                StringBuilder sb = new StringBuilder();
                for (String n : parse)
                    sb.append(n).append(" ");
                String result = sb.substring(0, sb.length() - 1);
                if (result.length() <= 0)
                    return null;
                System.out.println("Toparse = " + sb.toString());
                // Re-parsing technology
                CraftScriptRegistry.parseAndDebug(result, (st) -> {
                    int relative = 0;
                    CraftScriptClass csc = st.get(relative++);
                    if (!ScriptUtil.isInstanceOf(csc, StringClass.class))
                        throw new IllegalStateException();
//                    csc = st.get(relative++);
//                    System.out.println(csc);
//                    if (!(csc instanceof AtConditionHelper))
//                        throw new IllegalStateException();
                    csc = st.get(relative++);
                    if (!ScriptUtil.isInstanceOf(csc, StringClass.class))
                        throw new IllegalStateException();
                    csc = st.get(relative++);
                    if (!(csc instanceof ToVariableConnectCondition))
                        throw new IllegalStateException();
                    csc = st.get(relative);
                    if (!ScriptUtil.isInstanceOf(csc, MinecraftPlaceHolder.class))
                        throw new IllegalStateException();
                    // Validation finish
                });
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
            return new LoadScriptCommandInvoker();
        };
    }

    public static void register(Class<? extends AbstractPlaceHolder> cl, TripleConsumer<CompileStatus, Integer, String[]> consumer) {
        LoadScriptCommandInvoker.consumer.put(cl, consumer);
    }
}
