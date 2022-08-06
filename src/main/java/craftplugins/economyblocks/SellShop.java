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

import static craftplugins.economyblocks.Utils.formatMaterialName;

public class SellShop implements Listener, CommandExecutor {

    EconomyBlocks plugin;
    public Inventory shop = Bukkit.createInventory(null,36, "Sell Shop");
    BankHandler bankHandler;

    double netherrackPrice = .10;
    double dirtPrice = .10;
    double cobbledDeepslatePrice = .50;
    double cobblestonePrice = .50;
    double andesitePrice = .50;
    double dioritePrice = .50;
    double granitePrice = .50;
    double basaltPrice = 1;
    double blackStonePrice = 1;
    double quartzPrice = 3;
    double coalPrice = 5;
    double lapisPrice = 6;
    double copperPrice = 8;
    double emeraldPrice = 30;
    double ironIngotPrice = 50;
    double goldIngotPrice = 55;
    double diamondPrice = 500;
    double netheriteScrapPrice = 8000;

    double spiderEyePrice = 2;
    double stringPrice = 5;
    double rottenFleshPrice = 5;
    double bonePrice = 5;
    double gunPowderPrice = 5;
    double blazeRodPrice = 10;
    double enderPearlPrice = 15;
    double shulkerShellPrice = 300;
    double ghastTearPrice = 500;

    double seedPrice = .25;
    double sugarCanePrice = 2;
    double wheatPrice = 3;
    double carrotPrice = 3;
    double potatoPrice = 3;
    double beetRootPrice = 3;
    double melonSlicePrice = 5;
    double netherWartPrice = 6;
    double pumpkinPrice = 30;

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

        shop.addItem(createGuiItem(Material.NETHERRACK, Utils.chat("&b" + formatMaterialName(Material.NETHERRACK.name())), Utils.chat("&aPrice Per: $" + netherrackPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.DIRT, Utils.chat("&b" + formatMaterialName(Material.DIRT.name())), Utils.chat("&aPrice Per: $" + dirtPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.COBBLED_DEEPSLATE, Utils.chat("&b" + formatMaterialName(Material.COBBLED_DEEPSLATE.name())), Utils.chat("&aPrice Per: $" + cobbledDeepslatePrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.COBBLESTONE, Utils.chat("&b" + formatMaterialName(Material.COBBLESTONE.name())), Utils.chat("&aPrice Per: $" + cobblestonePrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.ANDESITE, Utils.chat("&b" + formatMaterialName(Material.ANDESITE.name())), Utils.chat("&aPrice Per: $" + andesitePrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.DIORITE, Utils.chat("&b" + formatMaterialName(Material.DIORITE.name())), Utils.chat("&aPrice Per: $" + dioritePrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.GRANITE, Utils.chat("&b" + formatMaterialName(Material.GRANITE.name())), Utils.chat("&aPrice Per: $" + granitePrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.BASALT, Utils.chat("&b" + formatMaterialName(Material.BASALT.name())), Utils.chat("&aPrice Per: $" + basaltPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.BLACKSTONE, Utils.chat("&b" + formatMaterialName(Material.BLACKSTONE.name())), Utils.chat("&aPrice Per: $" + blackStonePrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.QUARTZ, Utils.chat("&b" + formatMaterialName(Material.QUARTZ.name())), Utils.chat("&aPrice Per: $" + quartzPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.COAL, Utils.chat("&b" + formatMaterialName(Material.COAL.name())), Utils.chat("&aPrice Per: $" + coalPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.LAPIS_LAZULI, Utils.chat("&b" + formatMaterialName(Material.LAPIS_LAZULI.name())), Utils.chat("&aPrice Per: $" + lapisPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.COPPER_INGOT, Utils.chat("&b" + formatMaterialName(Material.COPPER_INGOT.name())), Utils.chat("&aPrice Per: $" + copperPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.EMERALD, Utils.chat("&b" + formatMaterialName(Material.EMERALD.name())), Utils.chat("&aPrice Per: $" + emeraldPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.IRON_INGOT, Utils.chat("&b" + formatMaterialName(Material.IRON_INGOT.name())), Utils.chat("&aPrice Per: $" + ironIngotPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.GOLD_INGOT, Utils.chat("&b" + formatMaterialName(Material.GOLD_INGOT.name())), Utils.chat("&aPrice Per: $" + goldIngotPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.DIAMOND, Utils.chat("&b" + formatMaterialName(Material.DIAMOND.name())), Utils.chat("&aSell One: $" + diamondPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.NETHERITE_SCRAP, Utils.chat("&b" + formatMaterialName(Material.NETHERITE_SCRAP.name())), Utils.chat("&aPrice Per: $" + netheriteScrapPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.SPIDER_EYE, Utils.chat("&b" + formatMaterialName(Material.SPIDER_EYE.name())), Utils.chat("&aPrice Per: $" + spiderEyePrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.STRING, Utils.chat("&b" + formatMaterialName(Material.STRING.name())), Utils.chat("&aPrice Per: $" + stringPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.ROTTEN_FLESH, Utils.chat("&b" + formatMaterialName(Material.ROTTEN_FLESH.name())), Utils.chat("&aPrice Per: $" + rottenFleshPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.BONE, Utils.chat("&b" + formatMaterialName(Material.BONE.name())), Utils.chat("&aPrice Per: $" + bonePrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.GUNPOWDER, Utils.chat("&b" + formatMaterialName(Material.GUNPOWDER.name())), Utils.chat("&aPrice Per: $" + gunPowderPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.BLAZE_ROD, Utils.chat("&b" + formatMaterialName(Material.BLAZE_ROD.name())), Utils.chat("&aPrice Per: $" + blazeRodPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.ENDER_PEARL, Utils.chat("&b" + formatMaterialName(Material.ENDER_PEARL.name())), Utils.chat("&aPrice Per: $" + enderPearlPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.SHULKER_SHELL, Utils.chat("&b" + formatMaterialName(Material.SHULKER_SHELL.name())), Utils.chat("&aSell One: $" + shulkerShellPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.GHAST_TEAR, Utils.chat("&b" + formatMaterialName(Material.GHAST_TEAR.name())), Utils.chat("&aPrice Per: $" + ghastTearPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.WHEAT_SEEDS, Utils.chat("&b" + formatMaterialName(Material.WHEAT_SEEDS.name())), Utils.chat("&aPrice Per: $" + seedPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.SUGAR_CANE, Utils.chat("&b" + formatMaterialName(Material.SUGAR_CANE.name())), Utils.chat("&aPrice Per: $" + sugarCanePrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.WHEAT, Utils.chat("&b" + formatMaterialName(Material.WHEAT.name())), Utils.chat("&aPrice Per: $" + wheatPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.CARROT, Utils.chat("&b" + formatMaterialName(Material.CARROT.name())), Utils.chat("&aPrice Per: $" + carrotPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.POTATO, Utils.chat("&b" + formatMaterialName(Material.POTATO.name())), Utils.chat("&aPrice Per: $" + potatoPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.BEETROOT, Utils.chat("&b" + formatMaterialName(Material.BEETROOT.name())), Utils.chat("&aPrice Per: $" + beetRootPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.MELON_SLICE, Utils.chat("&b" + formatMaterialName(Material.MELON_SLICE.name())), Utils.chat("&aSell One: $" + melonSlicePrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.NETHER_WART, Utils.chat("&b" + formatMaterialName(Material.NETHER_WART.name())), Utils.chat("&aSell One: $" + netherWartPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));
        shop.addItem(createGuiItem(Material.PUMPKIN, Utils.chat("&b" + formatMaterialName(Material.PUMPKIN.name())), Utils.chat("&aPrice Per: $" + pumpkinPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

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

    private void handleTransaction(ClickType clickType, Player player, Material material, double price, BankAccount bankAccount) {
        double removedAmount = removeItems(clickType, player, material);

        if (removedAmount != 0) {
            double totalSellPrice = removedAmount * price;
            player.sendMessage(Utils.chat("&aSold " + removedAmount + " &8" + material.name().replace("_", " ").toLowerCase() + "a for &b$" + Utils.format(totalSellPrice)));
            bankAccount.deposit(totalSellPrice);
        } else {
            player.sendMessage(Utils.chat("&cYou don't have enough of this item to sell"));
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
        ClickType clickType = event.getClick();
        BankAccount bankAccount = bankHandler.getBankAccount(player);
        if (clickedItem.getType() == Material.NETHERRACK) {
            handleTransaction(clickType, player, Material.NETHERRACK, netherrackPrice, bankAccount);

        } else if (clickedItem.getType() == Material.COBBLED_DEEPSLATE) {
            handleTransaction(clickType, player, Material.COBBLED_DEEPSLATE, cobbledDeepslatePrice, bankAccount);


        } else if (clickedItem.getType() == Material.COBBLESTONE) {
            handleTransaction(clickType, player, Material.COBBLESTONE, cobblestonePrice, bankAccount);


        } else if (clickedItem.getType() == Material.IRON_INGOT) {
            handleTransaction(clickType, player, Material.IRON_INGOT, ironIngotPrice, bankAccount);


        } else if (clickedItem.getType() == Material.GOLD_INGOT) {
            handleTransaction(clickType, player, Material.GOLD_INGOT, goldIngotPrice, bankAccount);


        } else if (clickedItem.getType() == Material.DIAMOND) {
            handleTransaction(clickType, player, Material.DIAMOND, diamondPrice, bankAccount);


        } else if (clickedItem.getType() == Material.NETHERITE_SCRAP) {
            handleTransaction(clickType, player, Material.NETHERITE_SCRAP, netheriteScrapPrice, bankAccount);


        } else if (clickedItem.getType() == Material.ROTTEN_FLESH) {
            handleTransaction(clickType, player, Material.ROTTEN_FLESH, rottenFleshPrice, bankAccount);


        } else if (clickedItem.getType() == Material.SPIDER_EYE) {
            handleTransaction(clickType, player, Material.SPIDER_EYE, spiderEyePrice, bankAccount);


        } else if (clickedItem.getType() == Material.BONE) {
            handleTransaction(clickType, player, Material.BONE, bonePrice, bankAccount);


        } else if (clickedItem.getType() == Material.GUNPOWDER) {
            handleTransaction(clickType, player, Material.GUNPOWDER, gunPowderPrice, bankAccount);


        } else if (clickedItem.getType() == Material.ENDER_PEARL) {
            handleTransaction(clickType, player, Material.ENDER_PEARL, enderPearlPrice, bankAccount);


        } else if (clickedItem.getType() == Material.SHULKER_SHELL) {
            handleTransaction(clickType, player, Material.SHULKER_SHELL, shulkerShellPrice, bankAccount);


        } else if (clickedItem.getType() == Material.GHAST_TEAR) {
            handleTransaction(clickType, player, Material.GHAST_TEAR, ghastTearPrice, bankAccount);


        } else if (clickedItem.getType() == Material.WHEAT) {
            handleTransaction(clickType, player, Material.WHEAT, wheatPrice, bankAccount);


        } else if (clickedItem.getType() == Material.CARROT) {
            handleTransaction(clickType, player, Material.CARROT, carrotPrice, bankAccount);


        } else if (clickedItem.getType() == Material.POTATO) {
            handleTransaction(clickType, player, Material.POTATO, potatoPrice, bankAccount);


        } else if (clickedItem.getType() == Material.BEETROOT) {
            handleTransaction(clickType, player, Material.BEETROOT, beetRootPrice, bankAccount);


        } else if (clickedItem.getType() == Material.MELON_SLICE) {
            handleTransaction(clickType, player, Material.MELON_SLICE, melonSlicePrice, bankAccount);


        } else if (clickedItem.getType() == Material.SUGAR_CANE) {
            handleTransaction(clickType, player, Material.SUGAR_CANE, sugarCanePrice, bankAccount);


        } else if (clickedItem.getType() == Material.PUMPKIN) {
            handleTransaction(clickType, player, Material.PUMPKIN, pumpkinPrice, bankAccount);

        } else if (clickedItem.getType() == Material.DIRT) {
            handleTransaction(clickType, player, Material.DIRT, dirtPrice, bankAccount);

        } else if (clickedItem.getType() == Material.ANDESITE) {
            handleTransaction(clickType, player, Material.ANDESITE, andesitePrice, bankAccount);

        } else if (clickedItem.getType() == Material.DIORITE) {
            handleTransaction(clickType, player, Material.DIORITE, dioritePrice, bankAccount);

        } else if (clickedItem.getType() == Material.GRANITE) {
            handleTransaction(clickType, player, Material.GRANITE, granitePrice, bankAccount);

        } else if (clickedItem.getType() == Material.BASALT) {
            handleTransaction(clickType, player, Material.BASALT, basaltPrice, bankAccount);

        } else if (clickedItem.getType() == Material.BLACKSTONE) {
            handleTransaction(clickType, player, Material.BLACKSTONE, blackStonePrice, bankAccount);

        } else if (clickedItem.getType() == Material.QUARTZ) {
            handleTransaction(clickType, player, Material.QUARTZ, quartzPrice, bankAccount);

        } else if (clickedItem.getType() == Material.COAL) {
            handleTransaction(clickType, player, Material.COAL, coalPrice, bankAccount);

        } else if (clickedItem.getType() == Material.LAPIS_LAZULI) {
            handleTransaction(clickType, player, Material.LAPIS_LAZULI, lapisPrice, bankAccount);

        } else if (clickedItem.getType() == Material.COPPER_INGOT) {
            handleTransaction(clickType, player, Material.COPPER_INGOT, copperPrice, bankAccount);

        } else if (clickedItem.getType() == Material.EMERALD) {
            handleTransaction(clickType, player, Material.EMERALD, emeraldPrice, bankAccount);

        } else if (clickedItem.getType() == Material.STRING) {
            handleTransaction(clickType, player, Material.STRING, stringPrice, bankAccount);

        } else if (clickedItem.getType() == Material.BLAZE_ROD) {
            handleTransaction(clickType, player, Material.BLAZE_ROD, blazeRodPrice, bankAccount);

        } else if (clickedItem.getType() == Material.WHEAT_SEEDS) {
            handleTransaction(clickType, player, Material.WHEAT_SEEDS, seedPrice, bankAccount);

        } else if (clickedItem.getType() == Material.NETHER_WART) {
            handleTransaction(clickType, player, Material.NETHER_WART, netherWartPrice, bankAccount);

        }
        player.updateInventory();

    }

    private double removeItems(ClickType ct, Player player, Material m) {
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
