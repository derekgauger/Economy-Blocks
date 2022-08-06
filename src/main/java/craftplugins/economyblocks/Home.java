package craftplugins.economyblocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Home implements CommandExecutor, Serializable {

    private static transient final long serialVersionUID = -1681012206529286331L;

    transient EconomyBlocks plugin;
    HashMap<String, double[]> homes;

    public Home(EconomyBlocks plugin) {
        this.plugin = plugin;

        homes = loadData("homes.data");

        if (homes == null) {
            homes = new HashMap<>();
        }

        Bukkit.getPluginCommand("sethome").setExecutor(this);
        Bukkit.getPluginCommand("home").setExecutor(this);
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

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] strings) {

        if (!(sender instanceof Player)) {
            System.out.println("Only players in game can do that");
            return false;
        }

        Player player = (Player) sender;
        String uuid = player.getUniqueId().toString();

        if (label.equalsIgnoreCase("sethome") || label.equalsIgnoreCase("sh")) {
            Location location = player.getLocation();
            double x = location.getX();
            double y = location.getY();
            double z = location.getZ();


            homes.put(uuid, new double[]{x, y, z});
            player.sendMessage(Utils.chat("&dHome set!"));

        } else if (label.equalsIgnoreCase("home") || label.equalsIgnoreCase("h")) {

            double[] location = homes.get(uuid);

            if (location == null) {
                player.sendMessage(Utils.chat("&cNo home is currently set. Try /sethome"));
                return false;
            }

            double x = location[0];
            double y = location[1];
            double z = location[2];
            player.sendMessage(Utils.chat("&dHome - x: " + Utils.format(x) + ", y: " + Utils.format(y) + ", z: " + Utils.format(z)));

        }

        return true;


    }
}
