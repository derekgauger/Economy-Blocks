package craftplugins.economyblocks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class FillBlockShop implements Listener, CommandExecutor {

    EconomyBlocks plugin;
    BankHandler bankHandler;
    public Inventory shop = Bukkit.createInventory(null, 9, "Fill Block Shop");

    double fillBlockPrice = 1000;


    public FillBlockShop(EconomyBlocks plugin, BankHandler bankHandler) {
        this.plugin = plugin;
        this.bankHandler = bankHandler;

        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        Bukkit.getServer().getPluginCommand("fbs").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("Only players in game can do that");
            return false;
        }

        initializeFillBlockShop();
        Player player = (Player) sender;

        openInventory(player);

        return true;
    }

    private void initializeFillBlockShop() {
        shop.clear();
        int emptyCount = 1;
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));

        shop.addItem(createGuiItem(Material.DIRT, Utils.chat("&6Dirt Fill Block"), Utils.chat("&bFills 6x6x6 above placed block with dirt"), Utils.chat("&aPrice Per: $" + fillBlockPrice)));
        shop.addItem(createGuiItem(Material.STONE, Utils.chat("&6Stone Fill Block"), Utils.chat("&bFills 6x6x6 above placed block with stone"), Utils.chat("&aPrice Per: $" + fillBlockPrice)));
        shop.addItem(createGuiItem(Material.NETHERRACK, Utils.chat("&6Netherrack Fill Block"), Utils.chat("&bFills 6x6x6 above placed block with netherrack"), Utils.chat("&aPrice Per: $" + fillBlockPrice)));

        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        shop.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));

    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(Arrays.asList(lore));

        if (item.getType() == Material.DIRT || item.getType() == Material.STONE || item.getType() == Material.NETHERRACK) {
            meta.addEnchant(Enchantment.MENDING, 1, true);
        }

        item.setItemMeta(meta);

        return item;
    }

    public void openInventory(HumanEntity entity) {
        initializeFillBlockShop();
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

        if (!event.getInventory().contains(clickedItem)) {
            return;
        }

        if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.GRAY_STAINED_GLASS_PANE) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        BankAccount bankAccount = bankHandler.getBankAccount(player);

        if (!clickedItem.getItemMeta().getDisplayName().contains("Fill Block")) {
            return;
        }

        if (clickedItem.getType() == Material.DIRT) {
            if (bankAccount.getBalance() >= fillBlockPrice) {
                player.getInventory().addItem(clickedItem);
                bankAccount.withdraw(fillBlockPrice);
                return;
            }

        } else if (clickedItem.getType() == Material.STONE) {
            if (bankAccount.getBalance() >= fillBlockPrice) {
                player.getInventory().addItem(clickedItem);
                bankAccount.withdraw(fillBlockPrice);
                return;
            }
        } else if (clickedItem.getType() == Material.NETHERRACK) {
            if (bankAccount.getBalance() >= fillBlockPrice) {
                player.getInventory().addItem(clickedItem);
                bankAccount.withdraw(fillBlockPrice);
                return;
            }
        }

        player.sendMessage(Utils.chat("&cInsufficient Funds"));
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent event) {

        String name = event.getItemInHand().getItemMeta().getDisplayName();

        if (!name.contains("Fill Block")) {
            return;
        }

        int range = 6;

        for (int x = -range/2; x < range/2; x++) {
            for (int z = -range/2; z < range/2; z++) {
                for (int y = 0; y < range; y++) {
                    Block block = event.getBlock().getLocation().add(x, y, z).getBlock();
                    if (block.getType() == Material.AIR) {
                        block.setType(event.getBlock().getType());
                    }
                }
            }
        }
    }
}
