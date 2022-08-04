package craftplugins.economyblocks;

import craftplugins.economyblocks.CarePackages.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;

public class CarePackageShop implements Listener, CommandExecutor {

    EconomyBlocks plugin;
    public Inventory shop = Bukkit.createInventory(null,27, "Care Package Shop");
    BankHandler bankHandler;

    static final double tier1Cost = 2000;
    static final double tier2Cost = 4000;
    static final double tier3Cost = 6000;
    static final double tier4Cost = 10000;
    static final double tier5Cost = 16000;



    // 30%
    ItemStack tierOne = Utils.createItem(Material.WHITE_WOOL, Utils.chat("&f&lCare Package Tier 1"),1, new Enchantment[]{Enchantment.MENDING}, new int[]{1}, Utils.chat("&aCost: $" + tier1Cost));

    // 25%
    ItemStack tierTwo = Utils.createItem(Material.LIME_WOOL,Utils.chat("&a&lCare Package Tier 2"),1, new Enchantment[]{Enchantment.MENDING}, new int[]{1}, Utils.chat("&aCost: $" + tier2Cost));

    // 20%
    ItemStack tierThree = Utils.createItem(Material.LIGHT_BLUE_WOOL,Utils.chat("&b&lCare Package Tier 3"),1, new Enchantment[]{Enchantment.MENDING}, new int[]{1}, Utils.chat("&aCost: $" + tier3Cost));

    // 15%
    ItemStack tierFour = Utils.createItem(Material.PURPLE_WOOL,Utils.chat("&5&lCare Package Tier 4"), 1, new Enchantment[]{Enchantment.MENDING}, new int[]{1}, Utils.chat("&aCost: $" + tier4Cost));

    // 10%
    ItemStack tierFive = Utils.createItem(Material.ORANGE_WOOL,Utils.chat("&6&lCare Package Tier 5"), 1, new Enchantment[]{Enchantment.MENDING}, new int[]{1}, Utils.chat("&aCost: $" + tier5Cost));

    ItemStack[] items = {tierOne, tierTwo, tierThree, tierFour, tierFive};

    public CarePackageShop(EconomyBlocks plugin, BankHandler bankHandler) {
        this.plugin = plugin;
        this.bankHandler = bankHandler;

        initializeCarePackages();

        Bukkit.getServer().getPluginManager().registerEvents(this,plugin);
        Bukkit.getServer().getPluginCommand("carepackage").setExecutor(this);

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        player.sendMessage(Utils.chat("&dCalled care packages, but sometimes the Minecraft gods don't care"));
        openInventory((Player) sender);

        return true;
    }

    private void initializeCarePackages() {
        int emptyCount = 1;
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));

        // Good: 33%, Bad: 67%
        shop.addItem(tierOne);
        new TierOne(plugin, tierOne, bankHandler);

        // Good: 50%, Bad: 50%
        shop.addItem(tierTwo);
        new TierTwo(plugin, tierTwo, bankHandler);

        // Good: 67%, Bad: 33%
        shop.addItem(tierThree);
        new TierThree(plugin, tierThree, bankHandler);

        // Good: 80%, Bad: 20%
        shop.addItem(tierFour);
        new TierFour(plugin, tierFour, bankHandler);

        // Good: 90%, Bad: 10%
        shop.addItem(tierFive);
        new TierFive(plugin, tierFive, bankHandler);

        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(Arrays.asList(lore));

        if (item.getType() != Material.GRAY_STAINED_GLASS_PANE) {
            meta.addEnchant(Enchantment.MENDING,1, true);
        }

        item.setItemMeta(meta);

        return item;
    }

    public void openInventory(HumanEntity entity) {
        entity.openInventory(shop);
    }

    @EventHandler
    public void onInventoryDrag(final InventoryDragEvent e) {
        if (e.getInventory() == shop) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory() != shop) {
            return;
        }

        event.setCancelled(true);

        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.GRAY_STAINED_GLASS_PANE) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        BankAccount bankAccount = bankHandler.getBankAccount(player);

        if (clickedItem.getType() == Material.WHITE_WOOL) {
            if (bankAccount.getBalance() >= tier1Cost) {
                player.getInventory().addItem(clickedItem);
                bankAccount.withdraw(tier1Cost);
                return;
            }

        } else if (clickedItem.getType() == Material.LIME_WOOL) {
            if (bankAccount.getBalance() >= tier2Cost) {
                player.getInventory().addItem(clickedItem);
                bankAccount.withdraw(tier2Cost);
                return;
            }

        } else if (clickedItem.getType() == Material.LIGHT_BLUE_WOOL) {
            if (bankAccount.getBalance() >= tier3Cost) {
                player.getInventory().addItem(clickedItem);
                bankAccount.withdraw(tier3Cost);
                return;
            }

        } else if (clickedItem.getType() == Material.PURPLE_WOOL) {
            if (bankAccount.getBalance() >= tier4Cost) {
                player.getInventory().addItem(clickedItem);
                bankAccount.withdraw(tier4Cost);
                return;
            }

        } else if (clickedItem.getType() == Material.ORANGE_WOOL) {
            if (bankAccount.getBalance() >= tier5Cost) {
                player.getInventory().addItem(clickedItem);
                bankAccount.withdraw(tier5Cost);
                return;
            }
        }

        player.sendMessage(Utils.chat("&cInsufficient Funds"));

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Random rand = new Random();
        int randomNum = (int) (rand.nextDouble() * 2000);
        Material invalidBreaks = {Material.AZALEA, Material.REDSTONE_COMPARATOR, Material.REDSTONE_REPEATER, Material.REDSTONE_WIRE, Material.REDSTONE_TORCH, Material.TORCH, Material.TUBE_CORAL, Material.BRAIN_CORAL, Material.BUBBLE_CORAL, Material.FIRE_CORAL, Material.HORN_CORAL, Material.DEAD_TUBE_CORAL, Material.DEAD_BRAIN_CORAL, Material.DEAD_FIRE_CORAL, Material.DEAD_HORN_CORAL, Material.SCAFFOLDING, Material.SLIME_BLOCK, Material.HONEY_BLOCK, Material.TNT, Material.TRIPWIRE, Material.TRIPWIRE_HOOK, Material.GRASS, Material.FERN, Material.DANDELION, Material.POPPY, Material.BLUE_ORCHID, Material.ALLIUM, Material.AZURE_BLUET, Material.TULIP, Material.OXEYE_DAISY, Material.CORNFLOWER, Material.LILY_OF_THE_VALLEY, Material.WITHER_ROSE, Material.SUNFLOWER, Material.LILAC, Material.ROSE_BUSH, Material.PEONY, Material.SUGAR_CANE, Material.LILY_PAD, Material.SPRUCE_SAPLING, Material.BIRCH_SAPLING, Material.DARK_OAK_SAPLING, Material.ACACIA_SAPLING, Material.OAK_SAPLING, Material.JUNGLE_SAPLING, Material.BAMBOO, Material.SPORE_BLOSSOM, Material.BROWN_MUSHROOM, Material.RED_MUSHROOM, Material.DEAD_BUSH, Material.FLOWER_POT, Material.MELON_STEM, Material.PUMPKIN_STEM, Material.WARPED_FUNGUS, Material.CRIMSON_FUNGUS, Material.CRIMSON_ROOT, Material.WARPED_ROOT, Material.WEEPING_VINE, Material.TWISTING_VINE, Material.WHEAT, Material.CARROT, Material.BEATROOT, Material.CACTUS, Material.PUMPKIN, Material.MELON, MATERIAL.POTATO};

        boolean giveChance = false;

        for (Material type : invalidBreaks) {
            if (event.getBlock().getType() == type) {
                return;
            }
        }

        if (randomNum > 1980) {
            int randomIndex = Utils.getRandomNumber(0, items.length);
            Utils.addItemToInventory(items[randomIndex], event.getPlayer());
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getDisplayName() + " has received a free " + items[randomIndex].getDisplayName() + "&d!"));
        }
    }
    
    // Add entity kill event
    // Harvest event
    
}
