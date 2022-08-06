package craftplugins.economyblocks.Events.GoodEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GiveEffects implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println("Give Effects");

        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 60 * 20, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20 * 60 * 20, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 60 * 20, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 60 * 20, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 20 * 60 * 20, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 60 * 20, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 60 * 20, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 60 * 20, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 20 * 60 * 20, 1));

        player.sendMessage(Utils.chat("&dYou have been given potion effects"));
    }
}
