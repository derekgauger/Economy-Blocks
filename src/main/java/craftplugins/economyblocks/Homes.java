package craftplugins.economyblocks;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Homes implements Listener, CommandExecutor, Serializable {

    private static transient final long serialVersionUID = -1681012206529286331L;

    transient EconomyBlocks plugin;
    transient BankHandler bankHandler;
    transient Material[] materials = {Material.GRASS_BLOCK, Material.OAK_PLANKS, Material.STONE};
    transient double teleportPrice = 2500;

    HashMap<String, double[]> homes;
    int maxHomes = 3;

    public Homes(EconomyBlocks plugin, BankHandler bankHandler) {
        this.plugin = plugin;
        this.bankHandler = bankHandler;
        homes = loadData("homes.data");

        if (homes == null) {
            homes = new HashMap<>();
        }

        Bukkit.getPluginCommand("sethome").setExecutor(this);
        Bukkit.getPluginCommand("homes").setExecutor(this);
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

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

    public HashMap<String, double[]> loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            homes = (HashMap<String, double[]>) in.readObject();
            in.close();
            return homes;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int getPlayerHomeCount(String uuid) {
        int count = 0;
        for (String key : homes.keySet()) {
            if (key.contains(uuid)) {
                count += 1;
            }
        }
        return count;
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

                    if (houseNum > maxHomes) {
                        player.sendMessage(Utils.chat("Max number of homes is " + maxHomes));
                        return false;
                    }

                    if (getPlayerHomeCount(uuid) >= maxHomes) {
                        Location location = player.getLocation();
                        double x = location.getX();
                        double y = location.getY();
                        double z = location.getZ();

                        homes.put(uuid + "--" + houseNum, new double[]{x, y, z});
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
                return false;
            }

            Location location = player.getLocation();
            double x = location.getX();
            double y = location.getY();
            double z = location.getZ();

            homes.put(uuid + "--" + (numHomes + 1), new double[]{x, y, z});
            player.sendMessage(Utils.chat("&dHome set!"));

            return true;
        }

        if (getPlayerHomeCount(uuid) == 0) {
            player.sendMessage(Utils.chat("&dYou don't have any homes set. Try /sethome"));
            return false;
        }

        int emptyCount = 0;
        int homesCount = 0;

        Inventory homesGui = Bukkit.createInventory(null,9, "Homes");

        homesGui.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        homesGui.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        homesGui.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));

        for (Map.Entry<String, double[]> entry : homes.entrySet()) {
            String key = entry.getKey();
            double[] value = entry.getValue();


            if (key.contains(uuid)) {
                homesCount += 1;

                double x = value[0];
                double y = value[1];
                double z = value[2];

                homesGui.addItem(createGuiItem(materials[homesCount - 1], Utils.chat("&6&lHome " + homesCount), Utils.chat("&aTeleport Price: $" + teleportPrice), Utils.chat("&bx: " + Utils.format(x) + ", y: " + Utils.format(y) + ", z: " + Utils.format(z))));

            }
        }

        for (int i = 0; i < maxHomes - getPlayerHomeCount(uuid); i++) {
            homesGui.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));

        }

        homesGui.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        homesGui.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        homesGui.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));

        player.openInventory(homesGui);

        return true;

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

        String home = clickedItem.getItemMeta().getDisplayName();

        int homeNumber = Integer.parseInt(String.valueOf(home.charAt(home.length() - 1)));
        System.out.println(homeNumber);

        String uuid = player.getUniqueId().toString();

        getPlayerHomeCount(uuid);

        double[] location = homes.get(uuid + "--" + homeNumber);

        double x = location[0];
        double y = location[1];
        double z = location[2];


        player.teleport(new Location(player.getWorld(), x, y, z));
        bankAccount.withdraw(teleportPrice);

    }

}
