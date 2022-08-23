package craftplugins.economyblocks;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ColorsCommand implements CommandExecutor {

    EconomyBlocks plugin;

    public ColorsCommand (EconomyBlocks plugin) {
        this.plugin = plugin;

        Bukkit.getPluginCommand("colors").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(sender instanceof Player)) {
            System.out.println("Only players in game can do that");
            return false;
        }

        Player player = (Player) sender;

        player.sendMessage("&0 - " + Utils.chat("&0Black"));
        player.sendMessage("&1 - " + Utils.chat("&1Dark Blue"));
        player.sendMessage("&2 - " + Utils.chat("&2Dark Green"));
        player.sendMessage("&3 - " + Utils.chat("&3Dark Aqua"));
        player.sendMessage("&4 - " + Utils.chat("&4Dark Red"));
        player.sendMessage("&5 - " + Utils.chat("&5Purple"));
        player.sendMessage("&6 - " + Utils.chat("&6Gold"));
        player.sendMessage("&7 - " + Utils.chat("&7Gray"));
        player.sendMessage("&8 - " + Utils.chat("&8Dark Gray"));
        player.sendMessage("&9 - " + Utils.chat("&9Blue"));
        player.sendMessage("&a - " + Utils.chat("&aGreen"));
        player.sendMessage("&b - " + Utils.chat("&bAqua"));
        player.sendMessage("&c - " + Utils.chat("&cRed"));
        player.sendMessage("&d - " + Utils.chat("&dPink"));
        player.sendMessage("&e - " + Utils.chat("&eYellow"));
        player.sendMessage("&f - " + Utils.chat("&fWhite"));
        player.sendMessage("&l - " + Utils.chat("&lLarge Text"));
        player.sendMessage("&k - " + Utils.chat("&kLarge Text"));
        player.sendMessage("&m - " + Utils.chat("&mStrikeThrough"));
        player.sendMessage("&n - " + Utils.chat("&nUnderline"));
        player.sendMessage("&o - " + Utils.chat("&oItalics"));
        player.sendMessage("&r - " + Utils.chat("&rRegular"));

        return true;
    }
}
