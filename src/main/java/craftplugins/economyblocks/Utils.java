package craftplugins.economyblocks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.*;

public class Utils implements Listener {

    EconomyBlocks plugin;
    ItemStack smiteStick = Utils.createItem(Material.STICK, Utils.chat("&bLightning Stick"), 1,null, null);


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

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        ItemStack item = event.getPlayer().getItemInHand();
        if (item.isSimilar(smiteStick)) {
            Block tb = event.getPlayer().getTargetBlock(120);

            if (tb != null) {
                tb.getWorld().spawnEntity(tb.getLocation(), EntityType.LIGHTNING);
            } else {
                Block teb = event.getPlayer().getTargetEntity(120).getLocation().getBlock();
                teb.getWorld().spawnEntity(teb.getLocation(), EntityType.LIGHTNING);
            }

        }
    }

    public static ItemStack createItem(final Material material, final String name, int amount, Enchantment[] enchants, int[] levels, final String... lore) {
        final ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(Arrays.asList(lore));

        if (enchants != null) {
            for (int i = 0; i < enchants.length; i++) {
                meta.addEnchant(enchants[i], levels[i], true);
            }
        }
        item.setItemMeta(meta);

        return item;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String format(double value) {
        DecimalFormat df = new DecimalFormat("###,###,###.##");
        return String.valueOf(df.format(value));
    }

    public static String formatMaterialName(String value) {
        value = value.replace("_", " ");
        String[] split = value.split(" ");
        List<String> name = new ArrayList<>();
        for (String s : split) {
            s = s.toLowerCase();
            s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1);
            name.add(s);
        }
        StringBuilder output = new StringBuilder();
        for (String s : name) {
            output.append(s).append(" ");
        }
        return output.substring(0, output.length() - 1);
    }
    
    public static void addItemToInventory(ItemStack item, Player player) {
        HashMap<Integer, ItemStack> addedItem = player.getInventory().addItem(item);
        if (!addedItem.isEmpty()) {
            player.getWorld().dropItemNaturally(player.getLocation(), item);
            player.sendMessage(Utils.chat("&dItem has been dropped at your feet"));
        }
    }
    
}
