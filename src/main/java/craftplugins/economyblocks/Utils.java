package craftplugins.economyblocks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.List;

public class Utils implements Listener {

    EconomyBlocks plugin;

    public Utils(EconomyBlocks plugin) {
        this.plugin = plugin;

        Bukkit.getServer().getPluginManager().registerEvents(this,plugin);
    }

    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    @EventHandler
    public void onEntityHit(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player || !(event.getEntity() instanceof LivingEntity)) {
            return;
        }

        Entity entity = event.getEntity();

        double health = ((LivingEntity) entity).getHealth() - event.getFinalDamage();
        health = Math.round(health * 10.0) / 10.0;
        if (health < 0.0) {
            health = 0.0;
        }
        entity.setCustomNameVisible(true);
        entity.setCustomName(Utils.chat((String.format("%.1f", health) + " &c❤ &f" + entity.getType())));
    }

    public static List<Block> getBlocks(Block start, int radius) {
        List<Block> blocks = new ArrayList<>();
        double startX = start.getLocation().getX();
        double startY = start.getLocation().getY();
        double startZ = start.getLocation().getZ();

        for (double x = startX - radius; x <= startX + radius; x++) {
            for (double y = startY - radius; y <= startY + radius; y++) {
                for (double z = startZ - radius; z <= startZ + radius; z++) {
                    blocks.add(new Location(start.getWorld(),x,y,z).getBlock());
                }
            }
        }

        return blocks;
    }
}
