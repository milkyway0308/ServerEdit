package skywolf46.ServerEdit.Util;

import net.minecraft.server.v1_12_R1.EntityPlayer;
import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;

public class EnchantmentReplacer {
    private static HashMap<String,Enchantment> enchs = new HashMap<>();
    static {
        input(Enchantment.PROTECTION_ENVIRONMENTAL,"protection");
        input(Enchantment.PROTECTION_FIRE,"fire-protection");
        input(Enchantment.PROTECTION_FALL,"feather-falling");
        input(Enchantment.PROTECTION_EXPLOSIONS,"explosion-protection");
        input(Enchantment.PROTECTION_PROJECTILE,"projectile-protection");
        input(Enchantment.THORNS,"thorns");
        input(Enchantment.DEPTH_STRIDER,"depth-strider");
        input(Enchantment.FROST_WALKER,"frost-walker");
        input(Enchantment.BINDING_CURSE,"binding-curse");
        input(Enchantment.DAMAGE_ALL,"sharpness");
        input(Enchantment.DAMAGE_UNDEAD,"");
        input(Enchantment.DAMAGE_ARTHROPODS,"");
        input(Enchantment.KNOCKBACK,"knockback");
        input(Enchantment.FIRE_ASPECT,"fire-aspect");
        input(Enchantment.LOOT_BONUS_BLOCKS,"looting");
        input(Enchantment.DIG_SPEED,"efficiency");
        input(Enchantment.SWEEPING_EDGE,"sweeping-edge");
        input(Enchantment.ARROW_DAMAGE,"arrow-damage");
        input(Enchantment.ARROW_KNOCKBACK,"arrow-knockback");
        input(Enchantment.ARROW_FIRE,"arrow-fire");
        input(Enchantment.ARROW_INFINITE,"arrow-infinite");
        input(Enchantment.LUCK,"luck");
        input(Enchantment.LURE,"lure");
        input(Enchantment.MENDING,"mending");
        input(Enchantment.VANISHING_CURSE,"vanishing-curse");
    }
    public static Enchantment parseEnchantment(String cmd){
        return enchs.get(cmd);
    }

    public static void input(Enchantment ench,String...cmd){
        for(String n : cmd)
            enchs.put(n,ench);
    }
}
