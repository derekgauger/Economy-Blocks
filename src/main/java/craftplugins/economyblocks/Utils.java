package craftplugins.economyblocks;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Utils implements Listener {

    EconomyBlocks plugin;
    ItemStack smiteStick = Utils.createItem(Material.STICK, Utils.chat("&bLightning Stick"), 1,null, null);

    CommunityHandler communityHandler;
    Nicknames nicknames;

    public Utils(EconomyBlocks plugin, CommunityHandler communityHandler, Nicknames nicknames) {
        this.plugin = plugin;
        this.communityHandler = communityHandler;
        this.nicknames = nicknames;

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

        if (event.getEntity() instanceof Cow) {
            if (event.getEntity().getCustomName() != null) {
                if (event.getEntity().getCustomName().contains("Community Cow")) {
                    ((Cow) event.getEntity()).setHealth(2047.9);
                }
            }
        }

        if (event.getEntity().getCustomName() !=  null && !event.getEntity().getCustomName().equalsIgnoreCase("")) {
            if (!event.getEntity().getCustomName().contains("❤")) {
                return;
            }
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

    @EventHandler
    public void onDeathEvent(PlayerDeathEvent event) {
        Player player = event.getPlayer();

        Location location = player.getLocation();
        int x = (int) location.getX();
        int y = (int) location.getY();
        int z = (int) location.getZ();
        player.sendMessage("Death Location - x: " + x + ", y: " + y + ", z: " + z);

    }

    public static ItemStack createItem(final Material material, final String name, int amount, Enchantment[] enchants, int[] levels, final String... lore) {
        final ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();

        if (!name.equalsIgnoreCase("")) {
            meta.setDisplayName(name);
        }
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);
        if (enchants != null) {
            for (int i = 0; i < enchants.length; i++) {
                if (material == Material.ENCHANTED_BOOK) {
                    EnchantmentStorageMeta esm = (EnchantmentStorageMeta) item.getItemMeta();
                    esm.addStoredEnchant(enchants[i], levels[i],true);
                    item.setItemMeta(esm);
                } else {
                    ItemMeta eMeta = item.getItemMeta();
                    eMeta.addEnchant(enchants[i], levels[i], true);
                    item.setItemMeta(eMeta);
                }
            }
        }

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

    public static void picker(GiveItemInfo[] options, Player player) {

        List<GiveItemInfo> hundredList = new ArrayList<>();

        for (GiveItemInfo option : options) {
            for (int i = 0; i < option.getChance(); i++) {
                hundredList.add(option);
            }
        }

        if (hundredList.size() != 100) {
            System.out.println("Urgently fix the item %s in one of the files");
            return;
        }

        Random rand = new Random();

        int randomIndex = rand.nextInt(hundredList.size());

        GiveItemInfo randomItem = hundredList.get(randomIndex);

        for (ItemStack item : randomItem.getItems()) {
            Utils.addItemToInventory(item, player);
        }

        if (randomItem.getChance() <= 5) {
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getDisplayName() + " has been given " + randomItem.getMessage()));

        } else {
            player.sendMessage(Utils.chat("&dYou have been given " + randomItem.getMessage()));
        }

    }

    public static String getWorldName(UUID UID) {
        World world = Bukkit.getWorld(UID);
        String worldType = world.getName();

        if (worldType.contains("end")) {
            return "End";

        } else if (worldType.contains("nether")) {
            return "Nether";

        } else {
            return "Overworld";
        }
    }

    @EventHandler
    public void onAnvilPrepare(PrepareAnvilEvent event) {
        Player player = (Player) event.getViewers().get(0);
        AnvilInventory inventory = event.getInventory();
        ItemStack FIRST_ITEM = inventory.getItem(0);
        ItemStack SECOND_ITEM = inventory.getItem(1);

        if ((FIRST_ITEM == null) || (SECOND_ITEM == null)) return;

        if (SECOND_ITEM.getType() == Material.ENCHANTED_BOOK) {
            if (FIRST_ITEM.getType() != Material.ENCHANTED_BOOK) {

                ItemStack result = FIRST_ITEM.clone();
                ItemMeta bookMeta = SECOND_ITEM.getItemMeta();
                assert bookMeta != null;
                for (Enchantment enchantment : ((EnchantmentStorageMeta) bookMeta).getStoredEnchants().keySet()) {
                    if (canEnchant(FIRST_ITEM, enchantment)) {
                        int bookLevel = ((EnchantmentStorageMeta) bookMeta).getStoredEnchantLevel(enchantment);
                        int itemLevel = FIRST_ITEM.getEnchantmentLevel(enchantment);
                        if (itemLevel < bookLevel) {
                            result.addUnsafeEnchantment(enchantment, bookLevel);
                        } else if (itemLevel == bookLevel) {
                            if (itemLevel >= enchantment.getMaxLevel()) {
                                continue;
                            }
                            result.addUnsafeEnchantment(enchantment, bookLevel + 1);
                        }
                    }
                }
                event.setResult(result);
            }
        }
    }

    public boolean canEnchant(ItemStack itemStack, Enchantment enchantment) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        AtomicBoolean canEnchant = new AtomicBoolean(enchantment.canEnchantItem(itemStack));

        if (itemMeta != null) {
            itemMeta.getEnchants().keySet().forEach(ench -> {
                if (ench != enchantment && ench.conflictsWith(enchantment)) {
                    canEnchant.set(false);
                }
            });
        }
        return canEnchant.get();
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        Community community = communityHandler.getPlayerCommunity(player.getUniqueId().toString());
        String prefix = "";
        if (community != null) {
            prefix = communityHandler.getCommunityName(community) + "&f";
        }

        String name = nicknames.getPlayerName(player.getUniqueId().toString());

        if (name == null) {
            if (player.isOp()) {
                name = "&e" + player.getName();
            } else {
                name = "&b" + player.getName();
            }
        }

        event.setFormat(Utils.chat(prefix + "&f " + name + "&f : " + event.getMessage()));
    }

}
