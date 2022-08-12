package craftplugins.economyblocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Homes implements Listener, CommandExecutor {

    EconomyBlocks plugin;
    BankHandler bankHandler;
    Material[] materials = {Material.GRASS_BLOCK, Material.NETHERRACK, Material.END_STONE};
    double teleportPrice = 2000;

    List<PlayerHome> homes;
    int maxHomes = 3;

    public Homes(EconomyBlocks plugin, BankHandler bankHandler) {
        this.plugin = plugin;
        this.bankHandler = bankHandler;
        homes = loadData("homes.data");

        if (homes == null) {
            homes = new ArrayList<>();
        }

        Bukkit.getPluginCommand("sethome").setExecutor(this);
        Bukkit.getPluginCommand("homes").setExecutor(this);
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

//    private void convert() {
//        for (String key : homes.keySet()) {
//            int homeNumber = Integer.parseInt(String.valueOf(key.charAt(key.length() - 1)));
//            String uuid = key.substring(0, key.length() - 3);
//            String worldName = "World";
//            double[] location = homes.get(key);
//            double x = location[0];
//            double y = location[1];
//            double z = location[2];
//
//            homes.add(new PlayerHome(uuid, homeNumber, worldName, x, y, z));
//        }
//    }

    public boolean saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(homes);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PlayerHome> loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            homes = (List<PlayerHome>) in.readObject();
            in.close();
            return homes;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int getPlayerHomeCount(String uuid) {
        int count = 0;
        for (PlayerHome ph : homes) {
            if (ph.uuid.equals(uuid)) {
                count += 1;
            }
        }
        return count;
    }

    private PlayerHome getPlayerHome(String uuid, int homeNumber) {
        for (PlayerHome ph : homes) {
            if (ph.uuid.equals(uuid) && ph.homeNumber == homeNumber) {
                return ph;
            }
        }
        return null;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("Only players in game can do that");
            return false;
        }

        Player player = (Player) sender;
        String uuid = player.getUniqueId().toString();

        if (label.equalsIgnoreCase("sethome") || label.equalsIgnoreCase("sh")) {

            if (args.length > 0) {
                try {
                    int houseNum = Integer.parseInt(args[0]);

                    if (houseNum > maxHomes || houseNum <= 0) {
                        player.sendMessage(Utils.chat("Invalid house number. Must be: 1-" + maxHomes));
                        return false;
                    }

                    if (getPlayerHomeCount(uuid) >= maxHomes) {
                        PlayerHome ph = getPlayerHome(uuid, houseNum);

                        Location location = player.getLocation();
                        ph.setX(location.getX());
                        ph.setY(location.getY());
                        ph.setZ(location.getZ());
                        ph.setWorld(location.getWorld().getUID().toString());

                        player.sendMessage(Utils.chat("&dHome " + houseNum + " set!"));
                        return true;
                    }

                } catch (NumberFormatException e) {
                    player.sendMessage(Utils.chat("Invalid House Number"));
                    return false;
                }
            }

            int numHomes = getPlayerHomeCount(uuid);

            if (numHomes >= maxHomes) {
                player.sendMessage(Utils.chat("&cYou have already reached the max number of homes"));
                player.sendMessage(Utils.chat("&cTry /sethome {home number}"));
                return false;
            }

            Location location = player.getLocation();

            PlayerHome ph = new PlayerHome(uuid, getPlayerHomeCount(uuid) + 1, player.getLocation().getWorld().getUID().toString(), location.getX(), location.getY(), location.getZ());
            homes.add(ph);

            player.sendMessage(Utils.chat("&dHome set!"));
            return true;
        }
        if (getPlayerHomeCount(uuid) == 0) {
            player.sendMessage(Utils.chat("&dYou don't have any homes set. Try /sethome"));
            return false;
        }

        int emptyCount = 0;
        int homesCount = 0;

        Inventory homesGui = Bukkit.createInventory(null, 9, "Homes");

        homesGui.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        homesGui.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        homesGui.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));

        for (PlayerHome ph : homes) {

            if (ph.uuid.equals(uuid)) {
                homesCount += 1;

                double x = ph.x;
                double y = ph.y;
                double z = ph.z;
                homesGui.addItem(createGuiItem(materials[homesCount - 1], Utils.chat("&6&lHome " + homesCount), Utils.chat("&aTeleport Price: $" + teleportPrice), Utils.chat("&bWorld: " + getWorldName(UUID.fromString(ph.worldUID))), Utils.chat("&bx: " + Utils.format(x) + ", y: " + Utils.format(y) + ", z: " + Utils.format(z))));

            }
        }

        for (int i = 0; i < 9 - maxHomes - getPlayerHomeCount(uuid); i++) {
            homesGui.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));

        }

        player.openInventory(homesGui);

        return true;

    }

    private String getWorldName(UUID UID) {
        World world = Bukkit.getWorld(UID);
        String worldType = world.getName();

        if (worldType.contains("end")) {
            return "End";

        } else if (worldType.contains("nether")) {
            return "Nether";

        } else {
            return "Normal World";
        }
    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    @EventHandler
    public void onInventoryDrag(final InventoryDragEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("Homes")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equalsIgnoreCase("Homes")) {
            return;
        }

        event.setCancelled(true);

        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.GRAY_STAINED_GLASS_PANE) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        BankAccount bankAccount = bankHandler.getBankAccount(player);

        if (bankAccount.getBalance() >= teleportPrice) {
            String clickedHome = clickedItem.getItemMeta().getDisplayName();
            int homeNumber = Integer.parseInt(String.valueOf(clickedHome.charAt(clickedHome.length() - 1)));

            PlayerHome ph = getPlayerHome(player.getUniqueId().toString(), homeNumber);

            World world = Bukkit.getWorld(UUID.fromString(ph.worldUID));

            if (player.getWorld() != world) {
                player.sendMessage(Utils.chat("&dYou are traveling dimensions!"));
            }

            player.teleport(new Location(world, ph.x, ph.y, ph.z));
            bankAccount.withdraw(teleportPrice);

        } else {
            player.sendMessage(Utils.chat("&cInsufficient funds"));
        }
    }
}
