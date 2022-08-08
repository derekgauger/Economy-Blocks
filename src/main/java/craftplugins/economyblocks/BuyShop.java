package craftplugins.economyblocks;

import craftplugins.economyblocks.CarePackages.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BuyShop implements Listener, CommandExecutor {

    EconomyBlocks plugin;
    public Inventory shop = Bukkit.createInventory(null,27, "Buy Shop");
    BankHandler bankHandler;

    static final double tier1Cost = 2000;
    static final double tier2Cost = 4000;
    static final double tier3Cost = 6000;
    static final double tier4Cost = 10000;
    static final double tier5Cost = 16000;

    static final double fireResPrice = 5000;
    static final double nightVisionPrice = 2000;
    static final double musicDiscPrice = 25000;
    static final double saddlePrice = 10000;
    static final double nameTagPrice = 20000;
    static final double axolotlPrice = 5000;
    static final double pandaPrice = 5000;
    static final double redstonePrice = 15 * 64;
    static final double foxPrice = 7500;
    static final double parrotPrice = 7500;

    List<Player> uneligablePlayers = new ArrayList<>();


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

    public BuyShop(EconomyBlocks plugin, BankHandler bankHandler) {
        this.plugin = plugin;
        this.bankHandler = bankHandler;

        initializeCarePackages();

        Bukkit.getServer().getPluginManager().registerEvents(this,plugin);
        Bukkit.getServer().getPluginCommand("buy").setExecutor(this);

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
        Potion fireRes = new Potion(PotionType.FIRE_RESISTANCE);
        ItemStack fireResPot = fireRes.toItemStack(1);
        ItemMeta fireResPotMeta = fireResPot.getItemMeta();
        fireResPotMeta.setDisplayName(Utils.chat("&4&lFire Resistance"));
        fireResPotMeta.setLore(Arrays.asList(Utils.chat("&aPrice Per: $" + fireResPrice)));
        fireResPot.setItemMeta(fireResPotMeta);

        Potion nightVision = new Potion(PotionType.NIGHT_VISION);
        ItemStack nightVisionPot = nightVision.toItemStack(1);
        ItemMeta nightVisionPotMeta = nightVisionPot.getItemMeta();
        nightVisionPotMeta.setDisplayName(Utils.chat("&5&lNight Vision"));
        nightVisionPotMeta.setLore(Arrays.asList(Utils.chat("&aPrice Per: $" + nightVisionPrice)));
        nightVisionPot.setItemMeta(nightVisionPotMeta);

        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));

        shop.addItem(tierOne);
        new TierOne(plugin, tierOne, bankHandler);

        shop.addItem(tierTwo);
        new TierTwo(plugin, tierTwo, bankHandler);

        shop.addItem(tierThree);
        new TierThree(plugin, tierThree, bankHandler);

        shop.addItem(tierFour);
        new TierFour(plugin, tierFour, bankHandler);

        shop.addItem(tierFive);
        new TierFive(plugin, tierFive, bankHandler);

        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));

        shop.addItem(fireResPot);
        shop.addItem(nightVisionPot);
        shop.addItem(createGuiItem(Material.MUSIC_DISC_PIGSTEP, Utils.chat("&d&lRandom Music Disc"),Utils.chat("&aPrice Per: $" + musicDiscPrice)));
        shop.addItem(createGuiItem(Material.SADDLE, Utils.chat("&6&lSaddle"),Utils.chat("&aPrice Per: $" + saddlePrice)));
        shop.addItem(createGuiItem(Material.NAME_TAG, Utils.chat("&f&lName Tag"),Utils.chat("&aPrice Per: $" + nameTagPrice)));

        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));

        shop.addItem(createGuiItem(Material.AXOLOTL_SPAWN_EGG, Utils.chat("&f&lAxolatl Spawn Egg"),Utils.chat("&aPrice Per: $" + axolotlPrice)));
        shop.addItem(createGuiItem(Material.PANDA_SPAWN_EGG, Utils.chat("&b&lPanda Spawn Egg"),Utils.chat("&aPrice Per: $" + pandaPrice)));
        shop.addItem(Utils.createItem(Material.REDSTONE, Utils.chat("&c&lRedstone Dust"), 64, null, null, Utils.chat("&aPrice Per Stack: $" + redstonePrice)));
        shop.addItem(createGuiItem(Material.FOX_SPAWN_EGG, Utils.chat("&e&lFox Spawn Egg"),Utils.chat("&aPrice Per Stack: $" + foxPrice)));
        shop.addItem(createGuiItem(Material.PARROT_SPAWN_EGG, Utils.chat("&a&lParrot Spawn Egg"),Utils.chat("&aPrice Per: $" + parrotPrice)));

        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));

    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(Arrays.asList(lore));

        if (item.getType().name().toLowerCase().contains("wool")) {
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
        } else if (clickedItem.getItemMeta().getDisplayName().toLowerCase().contains("fire")) {
            if (bankAccount.getBalance() >= fireResPrice) {
                player.getInventory().addItem(clickedItem);
                bankAccount.withdraw(fireResPrice);
                return;
            }
        } else if (clickedItem.getItemMeta().getDisplayName().toLowerCase().contains("night")) {
            if (bankAccount.getBalance() >= nightVisionPrice) {
                player.getInventory().addItem(clickedItem);
                bankAccount.withdraw(nightVisionPrice);
                return;
            }
        } else if (clickedItem.getType() == Material.MUSIC_DISC_PIGSTEP) {
            if (bankAccount.getBalance() >= musicDiscPrice) {
                Material[] discs = {Material.MUSIC_DISC_PIGSTEP, Material.MUSIC_DISC_5, Material.MUSIC_DISC_11, Material.MUSIC_DISC_13, Material.MUSIC_DISC_BLOCKS, Material.MUSIC_DISC_CAT, Material.MUSIC_DISC_CHIRP, Material.MUSIC_DISC_FAR, Material.MUSIC_DISC_MALL, Material.MUSIC_DISC_MELLOHI, Material.MUSIC_DISC_STAL, Material.MUSIC_DISC_STRAD,Material.MUSIC_DISC_WAIT,Material.MUSIC_DISC_WARD, Material.MUSIC_DISC_OTHERSIDE};
                int randomIndex = Utils.getRandomNumber(0, discs.length);

                player.getInventory().addItem(new ItemStack(discs[randomIndex]));
                bankAccount.withdraw(musicDiscPrice);
                return;
            }
        } else if (clickedItem.getType() == Material.SADDLE) {
            if (bankAccount.getBalance() >= saddlePrice) {
                player.getInventory().addItem(new ItemStack(Material.SADDLE));
                bankAccount.withdraw(saddlePrice);
                return;
            }
        } else if (clickedItem.getType() == Material.NAME_TAG) {
            if (bankAccount.getBalance() >= nameTagPrice) {
                player.getInventory().addItem(new ItemStack(Material.NAME_TAG));
                bankAccount.withdraw(nameTagPrice);
                return;
            }
        } else if (clickedItem.getType() == Material.AXOLOTL_SPAWN_EGG) {
            if (bankAccount.getBalance() >= axolotlPrice) {
                player.getInventory().addItem(new ItemStack(Material.AXOLOTL_SPAWN_EGG));
                bankAccount.withdraw(axolotlPrice);
                return;
            }
        } else if (clickedItem.getType() == Material.PANDA_SPAWN_EGG) {
            if (bankAccount.getBalance() >= pandaPrice) {
                player.getInventory().addItem(new ItemStack(Material.PANDA_SPAWN_EGG));
                bankAccount.withdraw(pandaPrice);
                return;
            }
        } else if (clickedItem.getType() == Material.REDSTONE) {
            if (bankAccount.getBalance() >= redstonePrice) {
                player.getInventory().addItem(new ItemStack(Material.REDSTONE, 64));
                bankAccount.withdraw(redstonePrice);
                return;
            }
        } else if (clickedItem.getType() == Material.FOX_SPAWN_EGG) {
            if (bankAccount.getBalance() >= foxPrice) {
                player.getInventory().addItem(new ItemStack(Material.FOX_SPAWN_EGG));
                bankAccount.withdraw(foxPrice);
                return;
            }
        } else if (clickedItem.getType() == Material.PARROT_SPAWN_EGG) {
            if (bankAccount.getBalance() >= parrotPrice) {
                player.getInventory().addItem(new ItemStack(Material.PARROT_SPAWN_EGG));
                bankAccount.withdraw(parrotPrice);
                return;
            }
        }

        player.sendMessage(Utils.chat("&cInsufficient Funds"));

    }

    private void giveRandomCarePackage(Player player) {

        if (uneligablePlayers.contains(player)) {
            return;
        }

        Random rand = new Random();
        double randomNum = rand.nextDouble() * 100;

        if (randomNum > 90) {
            Utils.addItemToInventory(tierFive, player);
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getDisplayName() + " has received a free " + tierFive.getItemMeta().getDisplayName() + "&d!"));

        } else if (randomNum > 75) {
            Utils.addItemToInventory(tierFour, player);
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getDisplayName() + " has received a free " + tierFour.getItemMeta().getDisplayName() + "&d!"));

        } else if (randomNum > 55) {
            Utils.addItemToInventory(tierThree, player);
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getDisplayName() + " has received a free " + tierThree.getItemMeta().getDisplayName() + "&d!"));

        } else if (randomNum > 35) {
            Utils.addItemToInventory(tierTwo, player);
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getDisplayName() + " has received a free " + tierTwo.getItemMeta().getDisplayName() + "&d!"));

        } else {
            Utils.addItemToInventory(tierOne, player);
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getDisplayName() + " has received a free " + tierOne.getItemMeta().getDisplayName() + "&d!"));

        }
        uneligablePlayers.add(player);
        runTimeoutTimer(player);

    }

    private void runTimeoutTimer(Player player) {
        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                if (count++ > 30) {
                    uneligablePlayers.remove(player);
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 20 * 1);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Random rand = new Random();
        int randomNum = (int) (rand.nextDouble() * 2000);
        Material[] invalidBreaks = {Material.TALL_GRASS, Material.TALL_SEAGRASS, Material.KELP, Material.AZALEA, Material.COMPARATOR, Material.REPEATER, Material.REDSTONE_WIRE, Material.REDSTONE_TORCH, Material.TORCH, Material.TUBE_CORAL, Material.BRAIN_CORAL, Material.BUBBLE_CORAL, Material.FIRE_CORAL, Material.HORN_CORAL, Material.DEAD_TUBE_CORAL, Material.DEAD_BRAIN_CORAL, Material.DEAD_FIRE_CORAL, Material.DEAD_HORN_CORAL, Material.SCAFFOLDING, Material.SLIME_BLOCK, Material.HONEY_BLOCK, Material.TNT, Material.TRIPWIRE, Material.TRIPWIRE_HOOK, Material.GRASS, Material.FERN, Material.DANDELION, Material.POPPY, Material.BLUE_ORCHID, Material.ALLIUM, Material.AZURE_BLUET, Material.ORANGE_TULIP, Material.PINK_TULIP, Material.RED_TULIP, Material.WHITE_TULIP, Material.OXEYE_DAISY, Material.CORNFLOWER, Material.LILY_OF_THE_VALLEY, Material.WITHER_ROSE, Material.SUNFLOWER, Material.LILAC, Material.ROSE_BUSH, Material.PEONY, Material.SUGAR_CANE, Material.LILY_PAD, Material.SPRUCE_SAPLING, Material.BIRCH_SAPLING, Material.DARK_OAK_SAPLING, Material.ACACIA_SAPLING, Material.OAK_SAPLING, Material.JUNGLE_SAPLING, Material.BAMBOO, Material.SPORE_BLOSSOM, Material.BROWN_MUSHROOM, Material.RED_MUSHROOM, Material.DEAD_BUSH, Material.FLOWER_POT, Material.MELON_STEM, Material.PUMPKIN_STEM, Material.WARPED_FUNGUS, Material.CRIMSON_FUNGUS, Material.CRIMSON_ROOTS, Material.WARPED_ROOTS, Material.WEEPING_VINES, Material.TWISTING_VINES, Material.WHEAT, Material.CARROTS, Material.BEETROOTS, Material.POTATOES};

        for (Material type : invalidBreaks) {
            if (event.getBlock().getType() == type) {
                return;
            }
        }

        Player player = event.getPlayer();

        if (randomNum > 1980) {

            giveRandomCarePackage(player);
        }
    }
    
    @EventHandler
    public void onMobKill(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if (!(entity instanceof Mob)) {
            return;
        }

        Player killer = ((Mob) entity).getKiller();

        if (!(killer instanceof Player)) {
            return;
        }
        
        Random rand = new Random();
        int randomNum = (int) (rand.nextDouble() * 2000);

        if (randomNum > 1980) {

            giveRandomCarePackage(killer);
        }
    }
    
    @EventHandler
    public void onHarvest(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material[] validCrops = {Material.WHEAT, Material.CARROTS, Material.BEETROOTS, Material.POTATOES};

        boolean giveChance = false;
        for (Material m : validCrops) {
            if (m == block.getType()) {
                giveChance = true;
                Ageable a = (Ageable) block.getBlockData();
                if (m != Material.BEETROOTS) {
                    if (a.getAge() != 7) {
                        return;
                    }
                } else {
                    if (a.getAge() != 3) {
                        return;
                    }
                }
            }
        }
        
        if (!giveChance) {
            return;
        }
        
        Random rand = new Random();
        int randomNum = (int) (rand.nextDouble() * 2000);

        Player player = event.getPlayer();

        if (randomNum > 1980) {

            giveRandomCarePackage(player);
        }
    }
}
