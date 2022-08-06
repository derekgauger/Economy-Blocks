package craftplugins.economyblocks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class SellShop implements Listener, CommandExecutor {

    EconomyBlocks plugin;
    public Inventory shop = Bukkit.createInventory(null,27, "Sell Shop");
    BankHandler bankHandler;
    
    double netherrackPrice = .10;
    double cobbledDeepslatePrice = .50;
    double cobblestonePrice = .50;
    double ironIngotPrice = 75;
    double goldIngotPrice = 100;
    double diamondPrice = 500;
    double netheriteScrapPrice = 8000;
    
    double spiderEyePrice = 5;
    double rottenFleshPrice = 5;
    double bonePrice = 5;
    double gunPowderPrice = 5;
    double enderPearlPrice = 15;
    double shulkerShellPrice = 300;
    double ghastTearPrice = 500;

    double sugarCanePrice = 2;
    double wheatPrice = 3;
    double carrotPrice = 3;
    double potatoPrice = 3;
    double beetRootPrice = 3;
    double melonSlicePrice = 4;
    double pumpkinPrice = 40;

    public SellShop(EconomyBlocks plugin, BankHandler bankHandler) {
        this.plugin = plugin;
        this.bankHandler = bankHandler;
        initializeShop();

        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        Bukkit.getServer().getPluginCommand("sell").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(sender instanceof Player)) {
            System.out.println("Only players in game can do that");
            return false;
        }

        Player player = (Player) sender;

        openInventory(player);

        return false;
    }

    private void initializeShop() {
        int emptyCount = 1;
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        
        shop.addItem(createGuiItem(Material.NETHERRACK, Utils.chat("&c&lNetherrack"), Utils.chat("&aPrice Per: $" + netherrackPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.COBBLED_DEEPSLATE, Utils.chat("&8&lCobbled Deepslate"), Utils.chat("&aPrice Per: $" + cobbledDeepslatePrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.COBBLESTONE, Utils.chat("&7&lCobblestone"), Utils.chat("&aPrice Per: $" + cobblestonePrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.IRON_INGOT, Utils.chat("&f&lIron Ingot"), Utils.chat("&aPrice Per: $" + ironIngotPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.GOLD_INGOT, Utils.chat("&6&lGold Ingot"), Utils.chat("&aPrice Per: $" + goldIngotPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.DIAMOND, Utils.chat("&b&lDiamond"), Utils.chat("&aSell One: $" + diamondPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.NETHERITE_SCRAP, Utils.chat("&5&lNetherite Scrap"), Utils.chat("&aPrice Per: $" + netheriteScrapPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        
        shop.addItem(createGuiItem(Material.ROTTEN_FLESH, Utils.chat("&c&lRotten Flesh"), Utils.chat("&aPrice Per: $" + rottenFleshPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.SPIDER_EYE, Utils.chat("&4&lSpider Eye"), Utils.chat("&aPrice Per: $" + spiderEyePrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.BONE, Utils.chat("&f&lBone"), Utils.chat("&aPrice Per: $" + bonePrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.GUNPOWDER, Utils.chat("&7&lGun Powder"), Utils.chat("&aPrice Per: $" + gunPowderPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.ENDER_PEARL, Utils.chat("&1&lEnder Pearl"), Utils.chat("&aPrice Per: $" + enderPearlPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.SHULKER_SHELL, Utils.chat("&5&lShulker Shell"), Utils.chat("&aSell One: $" + shulkerShellPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.GHAST_TEAR, Utils.chat("&b&lGhast Tear"), Utils.chat("&aPrice Per: $" + ghastTearPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));

        shop.addItem(createGuiItem(Material.SUGAR_CANE, Utils.chat("&1&lSugar Cane"), Utils.chat("&aPrice Per: $" + sugarCanePrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.WHEAT, Utils.chat("&e&lWheat"), Utils.chat("&aPrice Per: $" + wheatPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.CARROT, Utils.chat("&6&lCarrot"), Utils.chat("&aPrice Per: $" + carrotPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.POTATO, Utils.chat("&9&lPotato"), Utils.chat("&aPrice Per: $" + potatoPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.BEETROOT, Utils.chat("&c&lBeetroot"), Utils.chat("&aPrice Per: $" + beetRootPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.MELON_SLICE, Utils.chat("&a&lMelon Slice"), Utils.chat("&aSell One: $" + melonSlicePrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.PUMPKIN, Utils.chat("&6&lPumpkin"), Utils.chat("&aPrice Per: $" + pumpkinPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(Arrays.asList(lore));

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

    private void handleTransaction(ClickType clickType, Player player, double removeAmount, Material material, double price) {

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
        ClickType clickType = event.getClick();
        BankAccount bankAccount = bankHandler.getBankAccount(player);
        if (clickedItem.getType() == Material.NETHERRACK) {
            double removedAmount = removeItems(clickType, player, Material.NETHERRACK, netherrackPrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * netherrackPrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &8" + Material.NETHERRACK.name().replace("_", " ").toLowerCase() + "a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.COBBLED_DEEPSLATE) {
            double removedAmount = removeItems(clickType, player, Material.COBBLED_DEEPSLATE, cobbledDeepslatePrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * cobbledDeepslatePrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &8" + Material.COBBLED_DEEPSLATE.name().replace("_", " ").toLowerCase() + "a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.COBBLESTONE) {
            double removedAmount = removeItems(clickType, player, Material.COBBLESTONE, cobblestonePrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * cobblestonePrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &8" + Material.COBBLESTONE.name().replace("_", " ").toLowerCase() + "a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.IRON_INGOT) {
            double removedAmount = removeItems(clickType, player, Material.IRON_INGOT, ironIngotPrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * ironIngotPrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &7" + Material.IRON_INGOT.name().replace("_", " ").toLowerCase() + "a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.GOLD_INGOT) {
            double removedAmount = removeItems(clickType, player, Material.GOLD_INGOT, goldIngotPrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * goldIngotPrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &6" + Material.GOLD_INGOT.name().replace("_", " ").toLowerCase() + "a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.DIAMOND) {
            double removedAmount = removeItems(clickType, player, Material.DIAMOND, diamondPrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * diamondPrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &b" + Material.DIAMOND.name().replace("_", " ").toLowerCase() + "a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.NETHERITE_SCRAP) {
            double removedAmount = removeItems(clickType, player, Material.NETHERITE_SCRAP, netheriteScrapPrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * netheriteScrapPrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &5" + Material.NETHERITE_SCRAP.name().replace("_", " ").toLowerCase() + "&a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.ROTTEN_FLESH) {
            double removedAmount = removeItems(clickType, player, Material.ROTTEN_FLESH, rottenFleshPrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * rottenFleshPrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &5" + Material.ROTTEN_FLESH.name().replace("_", " ").toLowerCase() + "&a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.SPIDER_EYE) {
            double removedAmount = removeItems(clickType, player, Material.SPIDER_EYE, spiderEyePrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * spiderEyePrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &5" + Material.SPIDER_EYE.name().replace("_", " ").toLowerCase() + "&a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.BONE) {
            double removedAmount = removeItems(clickType, player, Material.BONE, bonePrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * bonePrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &5" + Material.BONE.name().replace("_", " ").toLowerCase() + "&a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.GUNPOWDER) {
            double removedAmount = removeItems(clickType, player, Material.GUNPOWDER, gunPowderPrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * gunPowderPrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &5" + Material.GUNPOWDER.name().replace("_", " ").toLowerCase() + "&a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.ENDER_PEARL) {
            double removedAmount = removeItems(clickType, player, Material.ENDER_PEARL, enderPearlPrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * enderPearlPrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &5" + Material.ENDER_PEARL.name().replace("_", " ").toLowerCase() + "&a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.SHULKER_SHELL) {
            double removedAmount = removeItems(clickType, player, Material.SHULKER_SHELL, shulkerShellPrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * shulkerShellPrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &5" + Material.SHULKER_SHELL.name().replace("_", " ").toLowerCase() + "&a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.GHAST_TEAR) {
            double removedAmount = removeItems(clickType, player, Material.GHAST_TEAR, ghastTearPrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * ghastTearPrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &5" + Material.GHAST_TEAR.name().replace("_", " ").toLowerCase() + "&a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.WHEAT) {
            double removedAmount = removeItems(clickType, player, Material.WHEAT, wheatPrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * wheatPrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &5" + Material.WHEAT.name().replace("_", " ").toLowerCase() + "&a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.CARROT) {
            double removedAmount = removeItems(clickType, player, Material.CARROT, carrotPrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * carrotPrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &5" + Material.CARROT.name().replace("_", " ").toLowerCase() + "&a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.POTATO) {
            double removedAmount = removeItems(clickType, player, Material.POTATO, potatoPrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * potatoPrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &5" + Material.POTATO.name().replace("_", " ").toLowerCase() + "&a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.BEETROOT) {
            double removedAmount = removeItems(clickType, player, Material.BEETROOT, beetRootPrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * beetRootPrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &5" + Material.BEETROOT.name().replace("_", " ").toLowerCase() + "&a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.MELON_SLICE) {
            double removedAmount = removeItems(clickType, player, Material.MELON_SLICE, melonSlicePrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * melonSlicePrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &5" + Material.MELON_SLICE.name().replace("_", " ").toLowerCase() + "&a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.SUGAR_CANE) {
            double removedAmount = removeItems(clickType, player, Material.SUGAR_CANE, sugarCanePrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * sugarCanePrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &5" + Material.SUGAR_CANE.name().replace("_", " ").toLowerCase() + "&a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.PUMPKIN) {
            double removedAmount = removeItems(clickType, player, Material.PUMPKIN, pumpkinPrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * pumpkinPrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &5" + Material.PUMPKIN.name().replace("_", " ").toLowerCase() + "&a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        } else if (clickedItem.getType() == Material.NETHERITE_SCRAP) {
            double removedAmount = removeItems(clickType, player, Material.NETHERITE_SCRAP, netheriteScrapPrice);

            if (removedAmount != 0) {
                double totalSellPrice = removedAmount * netheriteScrapPrice;
                player.sendMessage(Utils.chat("&aSold " + removedAmount + " &5" + Material.NETHERITE_SCRAP.name().replace("_", " ").toLowerCase() + "&a for &b$" + Utils.format(totalSellPrice)));
                bankAccount.deposit(totalSellPrice);
            } else {
                player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
            }

        }
        player.updateInventory();

    }

    private double removeItems(ClickType ct, Player player, Material m, double sellPrice) {
        double removedAmount = 0;
        if (ct.isLeftClick()) {
            removedAmount = removeOne(player.getInventory(), m);

        } else if (ct.isRightClick()) {
            removedAmount = removeAll(player.getInventory(), m);

        }

        return removedAmount;
    }

    private int removeAll(PlayerInventory inv, Material m) {
        int totalItems = 0;
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack slot = inv.getItem(i);

            if (slot == null) {
                continue;
            }

            if (m == slot.getType()) {
                totalItems += slot.getAmount();
                inv.clear(i);
            }
        }
        return totalItems;
    }

    private int removeOne(PlayerInventory inv, Material m) {
        for (int i = 0; i < inv.getSize(); i++) {

            ItemStack slot = inv.getItem(i);

            if (slot == null) {
                continue;
            }

            if (m == slot.getType()) {
                slot.setAmount(slot.getAmount() - 1);
                return 1;
            }
        }
        return 0;
    }
}
