package craftplugins.economyblocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Teleport implements Listener, CommandExecutor {

    EconomyBlocks plugin;
    public Inventory tps;
    BankHandler bankHandler;
    double teleportPrice = 500;
    List<String> removedPlayers = new ArrayList<>();

    public Teleport(EconomyBlocks plugin, BankHandler bankHandler) {
        this.plugin = plugin;
        this.bankHandler = bankHandler;
        tps = Bukkit.createInventory(null, roundToNearestNinth(Bukkit.getServer().getOnlinePlayers().size()) , "Teleport Shop");

        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        Bukkit.getServer().getPluginCommand("go").setExecutor(this);
        Bukkit.getServer().getPluginCommand("godisable").setExecutor(this);
        Bukkit.getServer().getPluginCommand("goenable").setExecutor(this);
    }

    public static int roundToNearestNinth(int num) {
        int remainder = 9 - (num % 9);
        return num + remainder;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("Only players in game can do that!");
            return false;
        }

        Player player = (Player) sender;

        if (args.length >= 1) {
            String username = args[0];
            if (!removedPlayers.contains(username)) {
                Player teleportPerson = Bukkit.getPlayer(username);

                if (teleportPerson == null) {
                    player.sendMessage(Utils.chat("&c'" + args[0] + "' is offline and cannot be teleported to"));
                    return false;
                }
                BankAccount ba = bankHandler.getBankAccount(player);

                if (ba.getBalance() < teleportPrice) {
                    player.sendMessage(Utils.chat("&cInsufficient funds. Teleport price: $" + Utils.format(teleportPrice)));
                    return false;
                }

                player.teleport(teleportPerson);
                ba.withdraw(teleportPrice);

                return true;
            } else {
                player.sendMessage(Utils.chat("&cThat player has /go disabled"));
                return false;
            }
        }


        if (label.equalsIgnoreCase("godisable")) {
            if (!removedPlayers.contains(player.getUniqueId().toString())) {
                removedPlayers.add(player.getUniqueId().toString());
                player.sendMessage(Utils.chat("&dYou will no longer appear in the teleporting menu"));
            } else {
                player.sendMessage(Utils.chat("&dTeleporting to you use already disabled"));
            }

        } else if (label.equalsIgnoreCase("goenable")){
            if (removedPlayers.contains(player.getUniqueId().toString())) {
                removedPlayers.remove(player.getUniqueId().toString());
                player.sendMessage(Utils.chat("&dYou will no appear in the teleporting menu"));
            } else {
                player.sendMessage(Utils.chat("&dTeleporting to you use already enabled"));
            }

        } else {
            openInventory(player);
        }
        return true;
    }

    private void initializeTeleportGUI() {

        tps = Bukkit.createInventory(null, roundToNearestNinth(Bukkit.getServer().getOnlinePlayers().size()) , "Teleport Shop");

        for (Player player : Bukkit.getOnlinePlayers()) {

            if (removedPlayers.contains(player.getUniqueId().toString())) {
                continue;
            }

            Location location = player.getLocation();
            int x = (int) location.getX();
            int y = (int) location.getY();
            int z = (int) location.getZ();
            tps.addItem(createGuiItem(player, Utils.chat("&6&l" + player.getName()), Utils.chat("&aPrice to Teleport: $" + teleportPrice), Utils.chat("&bClick to the last location of " + player.getName()), Utils.chat("&b" + Utils.getWorldName(player.getWorld().getUID())), Utils.chat("&b" + x + ", " + y + ", " + z)));
        }
    }

    protected ItemStack createGuiItem(Player player, final String name, final String... lore) {
        final ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(player.getName());

        meta.setDisplayName(name);

        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    public void openInventory(HumanEntity entity) {
        initializeTeleportGUI();
        entity.openInventory(tps);
    }

    @EventHandler
    public void onInventoryDrag(final InventoryDragEvent e) {
        if (e.getInventory() == tps) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory() != tps) {
            return;
        }

        event.setCancelled(true);

        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.GRAY_STAINED_GLASS_PANE) {
            return;
        }

        Player clicker = (Player) event.getWhoClicked();
        BankAccount bankAccount = bankHandler.getBankAccount(clicker);

        if (bankAccount.balance < teleportPrice) {
            clicker.sendMessage(Utils.chat("&cInsufficient Funds"));
            return;
        }

        ItemMeta meta = clickedItem.getItemMeta();

        String toUsername = meta.getDisplayName().substring(4);
        Player toPlayer = Bukkit.getServer().getPlayer(toUsername);

        if (toPlayer == clicker) {
            clicker.sendMessage(Utils.chat("&dDon't try to teleport to yourself. That is just foolish!"));
            return;
        }

        if (toPlayer != null) {
            clicker.teleport(toPlayer.getLocation());
            bankAccount.withdraw(teleportPrice);
            clicker.sendMessage(Utils.chat("&dTeleported to " + toPlayer.getDisplayName()));
        } else {
            clicker.sendMessage(Utils.chat("&cThat player is no longer online!"));
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        removedPlayers.remove(player.getUniqueId().toString());

    }

}
