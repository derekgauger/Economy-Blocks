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
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class MineralShop implements Listener, CommandExecutor {

    EconomyBlocks plugin;
    public Inventory shop = Bukkit.createInventory(null,27, "Mineral Shop");
    BankHandler bankHandler;

    double cobblestonePrice = .50;
    double ironIngotPrice = 100;
    double goldIngotPrice = 125;
    double diamondPrice = 500;
    double netheriteScrapPrice = 16000;

    public MineralShop(EconomyBlocks plugin, BankHandler bankHandler) {
        this.plugin = plugin;
        this.bankHandler = bankHandler;
        initializeShop();

        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        Bukkit.getServer().getPluginCommand("shop").setExecutor(this);
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

        shop.addItem(createGuiItem(Material.COBBLESTONE, Utils.chat("&8&lCobblestone"), Utils.chat("&aPrice Per: $" + cobblestonePrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.IRON_INGOT, Utils.chat("&7&lIron Ingot"), Utils.chat("&aPrice Per: $" + ironIngotPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.GOLD_INGOT, Utils.chat("&6&lGold Ingot"), Utils.chat("&aPrice Per: $" + goldIngotPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.DIAMOND, Utils.chat("&b&lDiamond"), Utils.chat("&aSell One: $" + diamondPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

        shop.addItem(createGuiItem(Material.NETHERITE_SCRAP, Utils.chat("&5&lNetherite Scrap"), Utils.chat("&aPrice Per: $" + netheriteScrapPrice), Utils.chat("Left Click: Sell One"), Utils.chat("Right Click: Sell All")));

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
        ClickType clickType = event.getClick();
        BankAccount bankAccount = bankHandler.getBankAccount(player);

        if (clickedItem.getType() == Material.COBBLESTONE) {
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
