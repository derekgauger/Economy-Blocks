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
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class CarePackageShop implements Listener, CommandExecutor {

    EconomyBlocks plugin;
    public Inventory shop = Bukkit.createInventory(null,27, "Care Package Shop");
    BankHandler bankHandler;

    static final double tier1Cost = 2000;
    static final double tier2Cost = 4000;
    static final double tier3Cost = 6000;
    static final double tier4Cost = 10000;
    static final double tier5Cost = 16000;

    public CarePackageShop(EconomyBlocks plugin, BankHandler bankHandler) {
        this.plugin = plugin;
        this.bankHandler = bankHandler;

        initializeCarePackages();

        Bukkit.getServer().getPluginManager().registerEvents(this,plugin);
        Bukkit.getServer().getPluginCommand("shop").setExecutor(this);

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
        ItemStack tierOne = createGuiItem(Material.WHITE_WOOL,Utils.chat("&f&lCare Package Tier 1"), Utils.chat("&aCost: $" + tier1Cost));
        shop.addItem(tierOne);
        new TierOne(plugin,tierOne);

        // Good: 50%, Bad: 50%
        ItemStack tierTwo = createGuiItem(Material.LIME_WOOL,Utils.chat("&a&lCare Package Tier 2"), Utils.chat("&aCost: $" + tier2Cost));
        shop.addItem(tierTwo);
        new TierTwo(plugin, tierTwo);

        // Good: 67%, Bad: 33%
        ItemStack tierThree = createGuiItem(Material.LIGHT_BLUE_WOOL,Utils.chat("&b&lCare Package Tier 3"), Utils.chat("&aCost: $" + tier3Cost));
        shop.addItem(tierThree);
        new TierThree(plugin, tierThree);

        // Good: 80%, Bad: 20%
        ItemStack tierFour = createGuiItem(Material.PURPLE_WOOL,Utils.chat("&5&lCare Package Tier 4"), Utils.chat("&aCost: $" + tier4Cost));
        shop.addItem(tierFour);
        new TierFour(plugin, tierFour);

        // Good: 90%, Bad: 10%
        ItemStack tierFive = createGuiItem(Material.ORANGE_WOOL,Utils.chat("&6&lCare Package Tier 5"), Utils.chat("&aCost: $" + tier5Cost));
        shop.addItem(tierFive);
        new TierFive(plugin, tierFive);

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
}
