package skywolf46.ServerEdit.Modules.ClearFlow.Util;

import org.bukkit.entity.EntityType;
import skywolf46.ServerEdit.Util.PatternReplacer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class EntityReplacer {
    private static HashMap<String, List<EntityType>> types = new HashMap<>();

    static {
        add(EntityType.DROPPED_ITEM, "item", "dropped item", "itemstack");
        add(EntityType.EXPERIENCE_ORB, "exp", "dropped exp");
        add(EntityType.AREA_EFFECT_CLOUD, "effect cloud", "area effect cloud");
        add(EntityType.ELDER_GUARDIAN, "elder guardian", "boss", "hostile");
        add(EntityType.WITHER_SKELETON, "wither skeleton", "hostile");
        add(EntityType.STRAY, "stray");
        add(EntityType.EGG, "egg");
        add(EntityType.LEASH_HITCH, "leash", "leash knot", "leash hitch");
        add(EntityType.PAINTING, "painting");
        add(EntityType.ARROW, "arrow");
        add(EntityType.SNOWBALL, "snowball");
        add(EntityType.FIREBALL, "fireball");
        add(EntityType.SMALL_FIREBALL, "small fireball");
        add(EntityType.ENDER_PEARL, "ender pearl");
        add(EntityType.ENDER_SIGNAL, "ender signal", "eye of ender signal", "eye of ender", "ender eye");
        add(EntityType.SPLASH_POTION, "potion", "potion flask", "splash potion");
        add(EntityType.THROWN_EXP_BOTTLE, "xp bottle", "exp bottle", "thrown exp bottle", "thrown xp bottle");
        add(EntityType.ITEM_FRAME, "item frame", "frame");
        add(EntityType.WITHER_SKULL, "wither skull");
        add(EntityType.PRIMED_TNT, "tnt", "primed tnt");
        add(EntityType.FIREWORK, "fireworks", "firework", "rocket", "firework rocket");
        add(EntityType.HUSK, "husk", "hostile");
        add(EntityType.SPECTRAL_ARROW, "spectral arrow", "arrow");
        add(EntityType.SHULKER_BULLET, "shulker bullet");
        add(EntityType.DRAGON_FIREBALL, "dragon fireball");
        add(EntityType.ZOMBIE_VILLAGER, "zombie villager", "hostile");
        add(EntityType.ZOMBIE_HORSE, "zombie horse", "animal");
        add(EntityType.ARMOR_STAND, "armor stand");
        add(EntityType.DONKEY, "donkey", "animal");
        add(EntityType.MULE, "mule", "hostile");
        add(EntityType.EVOKER_FANGS, "evoker fangs", "hostile");
        add(EntityType.EVOKER, "evoker", "hostile");
        add(EntityType.VEX, "vex", "hostile");
        add(EntityType.VINDICATOR, "vindicator", "vindication illager", "hostile");
        add(EntityType.ILLUSIONER, "illusion", "illusion illager", "hostile");
        add(EntityType.MINECART_COMMAND, "minecart", "commandblock minecart", "command cart", "minecart command", "command minecart", "minecart commandblock");
        add(EntityType.BOAT, "boat", "nice boat");
        add(EntityType.MINECART, "default minecart", "minecart");
        add(EntityType.MINECART_CHEST, "minecart chest", "chest minecart", "minecart");
        add(EntityType.MINECART_TNT, "tnt minecart", "minecart tnt", "minecart");
        add(EntityType.MINECART_HOPPER, "minecart hopper", "hopper minecart", "minecart");
        add(EntityType.MINECART_MOB_SPAWNER, "spawner minecart", "minecart spawner", "minecart mob spawner", "mob spawner minecart", "minecart");
        add(EntityType.CREEPER, "creeper", "hostile");
        add(EntityType.SKELETON, "skeleton", "hostile");
        add(EntityType.SPIDER, "spider", "hostile");
        add(EntityType.GIANT, "giant", "hostile");
        add(EntityType.ZOMBIE, "zombie", "hostile");
        add(EntityType.SLIME, "slime", "hostile");
        add(EntityType.GHAST, "ghast", "hostile");
        add(EntityType.PIG_ZOMBIE, "zombie pigman", "pig zombie", "zombie pig", "hostile");
        add(EntityType.ENDERMAN, "enderman", "hostile");
        add(EntityType.CAVE_SPIDER, "cave spider", "hostile");
        add(EntityType.SILVERFISH, "silverfish", "hostile");
        add(EntityType.BLAZE, "blaze", "hostile");
        add(EntityType.MAGMA_CUBE, "magma cube", "hostile");
        add(EntityType.ENDER_DRAGON, "ender dragon", "boss", "hostile");
        add(EntityType.WITHER, "wither", "boss", "hostile");
        add(EntityType.BAT, "bat", "animal");
        add(EntityType.WITCH, "witch", "hostile");
        add(EntityType.ENDERMITE, "endermite", "hostile");
        add(EntityType.GUARDIAN, "guardian", "hostile");
        add(EntityType.SHULKER, "shulker", "hostile");
        add(EntityType.PIG, "pig", "animal");
        add(EntityType.SHEEP, "sheep", "animal");
        add(EntityType.COW, "cow", "animal");
        add(EntityType.CHICKEN, "chicken", "animal");
        add(EntityType.SQUID, "squid", "animal");
        add(EntityType.WOLF, "wolf", "animal");
        add(EntityType.MUSHROOM_COW, "mushroom cow", "animal");
        add(EntityType.SNOWMAN, "snowman");
        add(EntityType.OCELOT, "ocelot", "animal");
        add(EntityType.IRON_GOLEM, "iron golem");
        add(EntityType.HORSE, "horse", "animal");
        add(EntityType.RABBIT, "rabbit", "animal");
        add(EntityType.POLAR_BEAR, "polar bear", "animal");
        add(EntityType.LLAMA, "llama", "animal");
        add(EntityType.LLAMA_SPIT, "llama spit");
        add(EntityType.PARROT, "parrot", "animal");
        add(EntityType.VILLAGER, "villager");
        add(EntityType.ENDER_CRYSTAL, "ender crystal");
        add(EntityType.LINGERING_POTION, "lingering potion", "potion");
        add(EntityType.FISHING_HOOK, "fishing hook");
        add(EntityType.TIPPED_ARROW, "tipped arrow", "arrow");
    }

    private static void add(EntityType type, String... names) {
        for (String name : names) {
            types.computeIfAbsent(name, k -> new ArrayList<>())
                    .add(type);
            types.computeIfAbsent(name.replace(" ", ""), k -> new ArrayList<>())
                    .add(type);
        }
        if (!type.isAlive()) {
            types.computeIfAbsent("tile entity", k -> new ArrayList<>())
                    .add(type);
            types.computeIfAbsent("tileentity", k -> new ArrayList<>())
                    .add(type);
        } else {
            types.computeIfAbsent("living entity", k -> new ArrayList<>())
                    .add(type);
            types.computeIfAbsent("livingentity", k -> new ArrayList<>())
                    .add(type);
        }
    }

    public static List<EntityType> replace(String name) {
        return types.get(name.replace("_", " "));
    }

    public static List<EntityType> replace(Pattern p) {
//        System.out.println("Starting replace [Pattern: " + p.pattern() + "]");
        List<EntityType> ec = new ArrayList<>();
        for (Map.Entry<String, List<EntityType>> et : types.entrySet()){
//            System.out.println("Matching pattern at " + et.getKey());
            if (p.matcher(et.getKey()).find()){
//                System.out.println("Matching success, adding " + et.getValue().size() + "entityTypes");
                ec.addAll(et.getValue());
            }
//                System.out.println("Matching fail");
        }
        return ec;
    }

    public static void main(String[] args) {
        System.out.println(replace(PatternReplacer.replace("CO*".toLowerCase())));
    }
}
