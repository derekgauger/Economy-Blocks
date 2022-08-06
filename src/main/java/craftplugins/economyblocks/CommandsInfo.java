package craftplugins.economyblocks;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class CommandsInfo implements Listener, CommandExecutor {

    EconomyBlocks plugin;

    public CommandsInfo(EconomyBlocks plugin) {
        this.plugin = plugin;

        Bukkit.getServer().getPluginCommand("info").setExecutor(this);

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            player.sendMessage(Utils.chat("&dWelcome to EconomyBlocks! Do /info for information"));
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] strings) {

        if (label.equalsIgnoreCase("info") || label.equalsIgnoreCase("commands")) {
            sender.sendMessage(Utils.chat("&d===================================="));
            sender.sendMessage(Utils.chat("&dEconomyBlocks is a 1.19.1+ Vanilla Minecraft plugin for adding a little bit more spice"));
            sender.sendMessage(Utils.chat("&dCare Packages spawn random events both good and bad"));
            sender.sendMessage(Utils.chat("&d===================================="));
            sender.sendMessage(Utils.chat("&dCommands:"));
            sender.sendMessage(Utils.chat("&d- &a/sell &d: Opens a shop for selling items"));
            sender.sendMessage(Utils.chat("&d- &a/buy &d: Opens a shop for buying items"));
            sender.sendMessage(Utils.chat("&d- &a/sethome &d: Sets your home location"));
            sender.sendMessage(Utils.chat("&d- &a/home &d: Displays your home location"));
            sender.sendMessage(Utils.chat("&d- &a/go &d: Opens a teleporting menu"));
            sender.sendMessage(Utils.chat("&d- &a/godisable &d: Removes you from the teleporting menu"));
            sender.sendMessage(Utils.chat("&d- &a/goenable &d: Adds you to the teleporting menu"));
            sender.sendMessage(Utils.chat("&d- &a/send {username} {amount} &d: Transfers money to a player"));
            sender.sendMessage(Utils.chat("&d- &a/bal &d: Displays your current account balance"));
            sender.sendMessage(Utils.chat("&d- &a/bal {username} &d: Displays other player's account balance"));
            sender.sendMessage(Utils.chat("&d- &a/info &d: Displays information about the plugin"));
            sender.sendMessage(Utils.chat("&d===================================="));
            sender.sendMessage(Utils.chat("&dCare Package Tiers"));
            sender.sendMessage(Utils.chat("&d - Tier 1 : 50% chance of a good event"));
            sender.sendMessage(Utils.chat("&d - Tier 2 : 67% chance of a good event"));
            sender.sendMessage(Utils.chat("&d - Tier 3 : 80% chance of a good event"));
            sender.sendMessage(Utils.chat("&d - Tier 4 : 95% chance of a good event"));
            sender.sendMessage(Utils.chat("&d - Tier 5 : 100% chance of a good event"));
            sender.sendMessage(Utils.chat("&d===================================="));

        }

        return true;
    }
}
