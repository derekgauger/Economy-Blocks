package craftplugins.economyblocks;

import org.bukkit.Bukkit;
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
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Nicknames implements CommandExecutor {

    EconomyBlocks plugin;
    HashMap<String, String> names;

    public Nicknames(EconomyBlocks plugin) {

        this.plugin = plugin;

        names = loadData("nicknames.data");

        if (names == null) {
            names = new HashMap<>();
        }

        Bukkit.getPluginCommand("nick").setExecutor(this);
    }

    public String getPlayerName(String uuid) {
        return names.get(uuid);
    }

    public boolean saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(names);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public HashMap<String, String> loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            names = (HashMap<String, String>) in.readObject();
            in.close();
            return names;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {


        if (!(sender instanceof Player)) {
            System.out.println("Only players in game can do that");
            return false;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(Utils.chat("&cUsage: /nick {nickname}"));
            return false;
        }

        String nickname = args[0];

        if (nickname.length() > 32) {
            player.sendMessage(Utils.chat("&cNickname cannot be longer than 32 characters"));
            return false;
        }

        if (nickname.contains("&k")) {
            player.sendMessage(Utils.chat("&cNickname cannot contain the ") + "&k" + Utils.chat(" code"));
            return false;
        }

        names.put(player.getUniqueId().toString(), nickname);
        player.sendMessage(Utils.chat("&dYou name in chat will now be: &f" + nickname));

        return true;
    }
}
