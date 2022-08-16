package craftplugins.economyblocks;

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
import org.jetbrains.annotations.NotNull;

public class Shops implements CommandExecutor, Listener {

    EconomyBlocks plugin;
    BankHandler bankHandler;
    CommunityHandler communityHandler;
    BuyShop bs;
    SellShop ss;
    FillBlockShop fbs;

    Inventory shop = Bukkit.createInventory(null, 9, "Shop");

    public Shops(EconomyBlocks plugin, BankHandler bankHandler, CommunityHandler communityHandler) {

        this.plugin = plugin;
        this.bankHandler = bankHandler;
        this.communityHandler = communityHandler;

        bs = new BuyShop(plugin, bankHandler, communityHandler);
        ss = new SellShop(plugin, bankHandler, communityHandler);
        fbs = new FillBlockShop(plugin, bankHandler);


        Bukkit.getPluginCommand("shop").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this, plugin);

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("Only players in game can do that");
            return false;
        }

        Player player = (Player) sender;

        openInventory(player);
        return true;
    }

    private void initializeShop(Player player) {
        shop.clear();
        int emptyCount = 1;

        String uuid = player.getUniqueId().toString();

        Community community = communityHandler.getPlayerCommunity(uuid);

        shop.addItem(Utils.createItem(Material.GRAY_STAINED_GLASS_PANE, Utils.chat("&4&lEmpty " + emptyCount++), 1, null, null));
        shop.addItem(Utils.createItem(Material.GRAY_STAINED_GLASS_PANE, Utils.chat("&4&lEmpty " + emptyCount++), 1, null, null));

        shop.addItem(Utils.createItem(Material.MUSIC_DISC_PIGSTEP, Utils.chat("&b&lBuy Shop"), 1, new Enchantment[] {Enchantment.MENDING}, new int[] {1}, Utils.chat("&aOpens the buy shop menu")));
        shop.addItem(Utils.createItem(Material.GOLD_INGOT, Utils.chat("&6&lSell Shop"), 1, new Enchantment[] {Enchantment.MENDING}, new int[] {1}, Utils.chat("&aOpens the sell shop menu")));

        if (community != null) {
            shop.addItem(Utils.createItem(Material.GRASS_BLOCK, Utils.chat("&e&lCommunity Build Shop"), 1, new Enchantment[] {Enchantment.MENDING}, new int[] {1}, Utils.chat("&aOpens the community build shop menu")));
            shop.addItem(Utils.createItem(Material.BOOK, Utils.chat("&9&lCommunity Book Shop"), 1, new Enchantment[] {Enchantment.MENDING}, new int[] {1}, Utils.chat("&aOpens the community book shop menu")));
        } else {
            shop.addItem(Utils.createItem(Material.BARRIER, Utils.chat("&4&lCommunity Build Shop"), 1, new Enchantment[]{Enchantment.MENDING}, new int[]{1}, Utils.chat("&cJoin a community to unlock this shop")));
            shop.addItem(Utils.createItem(Material.BARRIER, Utils.chat("&4&lCommunity Book Shop"), 1, new Enchantment[]{Enchantment.MENDING}, new int[]{1}, Utils.chat("&cJoin a community to unlock this shop")));
        }

        shop.addItem(Utils.createItem(Material.STONE, Utils.chat("&d&lFill Block Shop"), 1, new Enchantment[] {Enchantment.MENDING}, new int[] {1}, Utils.chat("&aOpens the fill block shop menu")));

        shop.addItem(Utils.createItem(Material.GRAY_STAINED_GLASS_PANE, Utils.chat("&4&lEmpty " + emptyCount++), 1, null, null));
        shop.addItem(Utils.createItem(Material.GRAY_STAINED_GLASS_PANE, Utils.chat("&4&lEmpty " + emptyCount++), 1, null, null));

    }

    public void openInventory(HumanEntity entity) {
        initializeShop((Player) entity);
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
        String uuid = player.getUniqueId().toString();

        Community community = communityHandler.getPlayerCommunity(uuid);

        if (clickedItem.getType() == Material.BARRIER) {
            player.sendMessage(Utils.chat("You need to join a community to unlock that shop"));
            return;
        }

        if (clickedItem.getType() == Material.MUSIC_DISC_PIGSTEP) {
            bs.openInventory(player);

        } else if (clickedItem.getType() == Material.GOLD_INGOT) {
            ss.openInventory(player);

        } else if (clickedItem.getType() == Material.GRASS_BLOCK) {
            if (community == null) {
                return;
            } else {
                communityHandler.openBuildInventory(player);
            }

        } else if (clickedItem.getType() == Material.BOOK) {
            if (community == null) {
                return;
            } else {
                communityHandler.openBookInventory(player);
            }

        } else if (clickedItem.getType() == Material.STONE) {
            fbs.openInventory(player);

        }

    }
}
