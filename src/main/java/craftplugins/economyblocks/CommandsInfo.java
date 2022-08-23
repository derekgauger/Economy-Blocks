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
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            player.sendMessage(Utils.chat("&dWelcome to EconomyBlocks! Do /info for information"));
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("Only players in game can do that");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {

            player.sendMessage(Utils.chat("&d==================================="));
            player.sendMessage(Utils.chat("&d - &a/info community &d: Community commands"));
            player.sendMessage(Utils.chat("&d - &a/info homes &d: Homes commands"));
            player.sendMessage(Utils.chat("&d - &a/info go &d: Teleporting commands"));
            player.sendMessage(Utils.chat("&d - &a/info shops &d: Shop menu commands"));
            player.sendMessage(Utils.chat("&d - &a/info eco &d: Economy commands"));
            player.sendMessage(Utils.chat("&d - &a/info cp &d: Care package information"));
            player.sendMessage(Utils.chat("&d==================================="));

            return true;
        }

        if (args.length == 1) {
            String cmd = args[0];

            if (cmd.equalsIgnoreCase("community")) {
                player.sendMessage(Utils.chat("&d-----------------------------------"));
                player.sendMessage(Utils.chat("&aCommunity Maintenance Commands"));
                player.sendMessage(Utils.chat("&d-----------------------------------"));
                player.sendMessage(Utils.chat("&d - &a/c create {name} &d: Creates a community"));
                player.sendMessage(Utils.chat("&d - &a/c delete &d: Deletes a community"));
                player.sendMessage(Utils.chat("&d - &a/c invite {username} &d: Invites a player"));
                player.sendMessage(Utils.chat("&d - &a/c kick {username} &d: Removes a player"));
                player.sendMessage(Utils.chat("&d - &a/c upgrade &d: Upgrades the community"));
                player.sendMessage(Utils.chat("&d - &a/c sethub &d: Sets community hub"));
                player.sendMessage(Utils.chat("&d - &a/c promote {username} &d: Promotes a member"));
                player.sendMessage(Utils.chat("&d - &a/c demote {username} &d: Demotes a member"));
                player.sendMessage(Utils.chat("&d - &a/c primary {color code} &d: Changes the primary color"));
                player.sendMessage(Utils.chat("&d - &a/c secondary {color code} &d: Changes the secondary color"));
                player.sendMessage(Utils.chat("&d-----------------------------------"));
                player.sendMessage(Utils.chat("&aCommunity Mmeber Commands"));
                player.sendMessage(Utils.chat("&d-----------------------------------"));
                player.sendMessage(Utils.chat("&d - &a/hub &d: Teleports to community hub"));
                player.sendMessage(Utils.chat("&d - &a/hub display &d: Displays community hub info"));
                player.sendMessage(Utils.chat("&d - &a/c leave &d: Leaves community"));
                player.sendMessage(Utils.chat("&d - &a/c info &d: Gets community information"));
                player.sendMessage(Utils.chat("&d - &a/c buildshop &d: Opens build shop menu"));
                player.sendMessage(Utils.chat("&d - &a/c bookshop &d: Opens book shop menu"));
                player.sendMessage(Utils.chat("&d - &a/c fund {amount} &d: Contributes to the community"));
                player.sendMessage(Utils.chat("&d - &a/c progress &d: Displays community progress"));
                player.sendMessage(Utils.chat("&d-----------------------------------"));
                player.sendMessage(Utils.chat("&aCommunity All Users Commands"));
                player.sendMessage(Utils.chat("&d-----------------------------------"));
                player.sendMessage(Utils.chat("&d - &a/c join {community name} &d: Joins a community"));
                player.sendMessage(Utils.chat("&d - &a/c list &d: Lists all active communities"));
                player.sendMessage(Utils.chat("&d - &a/c list invites &d: Lists current community invites"));
                player.sendMessage(Utils.chat("&d - &a/c perks &d: List all com. tier perks"));

            } else if (cmd.equalsIgnoreCase("homes")) {
                player.sendMessage(Utils.chat("&d-----------------------------------"));
                player.sendMessage(Utils.chat("&aHomes Commands"));
                player.sendMessage(Utils.chat("&d-----------------------------------"));
                player.sendMessage(Utils.chat("&d - &a/sethome &d: Adds a home"));
                player.sendMessage(Utils.chat("&d - &a/sethome {home number} &d: Sets a specific home loc."));
                player.sendMessage(Utils.chat("&d - &a/homes &d: Opens homes menu"));

            } else if (cmd.equalsIgnoreCase("go")) {
                player.sendMessage(Utils.chat("&d-----------------------------------"));
                player.sendMessage(Utils.chat("&aTeleporting Commands"));
                player.sendMessage(Utils.chat("&d-----------------------------------"));
                player.sendMessage(Utils.chat("&d - &a/go &d: Opens teleport menu"));
                player.sendMessage(Utils.chat("&d - &a/go {username} &d: Teleports to player"));
                player.sendMessage(Utils.chat("&d - &a/godisable &d: Disables teleporting"));
                player.sendMessage(Utils.chat("&d - &a/goenable &d: Enables teleporting"));

            } else if (cmd.equalsIgnoreCase("shops")) {
                player.sendMessage(Utils.chat("&d-----------------------------------"));
                player.sendMessage(Utils.chat("&aShop Menu Commands"));
                player.sendMessage(Utils.chat("&d-----------------------------------"));
                player.sendMessage(Utils.chat("&d - &a/shops &d: Opens all shops menus"));
                player.sendMessage(Utils.chat("&d - &a/buy &d: Opens buy menu"));
                player.sendMessage(Utils.chat("&d - &a/sell &d: Opens sell menu"));
                player.sendMessage(Utils.chat("&d - &a/c buildshop &d: Opens community build menu"));
                player.sendMessage(Utils.chat("&d - &a/c bookshop &d: Opens community book menu"));
                player.sendMessage(Utils.chat("&d - &a/fbs &d: Opens fill block shop"));

            } else if (cmd.equalsIgnoreCase("eco")) {
                player.sendMessage(Utils.chat("&d-----------------------------------"));
                player.sendMessage(Utils.chat("&aEconomy Commands"));
                player.sendMessage(Utils.chat("&d-----------------------------------"));
                player.sendMessage(Utils.chat("&d - &a/bal &d: Gets your bank account balance"));
                player.sendMessage(Utils.chat("&d - &a/bal {username} &d: Gets another player's balance"));
                player.sendMessage(Utils.chat("&d - &a/send {username} {amount} &d: Sends money to a player"));
                player.sendMessage(Utils.chat("&d - &a/setbal {username} {amount} &d: Allows console to set player bals"));

            } else if (cmd.equalsIgnoreCase("cp")) {
                player.sendMessage(Utils.chat("&d-----------------------------------"));
                player.sendMessage(Utils.chat("&aCare Package Information"));
                player.sendMessage(Utils.chat("&d-----------------------------------"));
                player.sendMessage(Utils.chat("&aTier 1 &d: 50% chance of a good event - Executes 1 good event"));
                player.sendMessage(Utils.chat("&aTier 2 &d: 67% chance of a good event - Executes 1 good event"));
                player.sendMessage(Utils.chat("&aTier 3 &d: 80% chance of a good event - Executes 2 good events"));
                player.sendMessage(Utils.chat("&aTier 4 &d: 95% chance of a good event - Executes 2 good events"));
                player.sendMessage(Utils.chat("&aTier 5 &d: 100% chance of a good event - Executes 3 good events"));
            } else if (cmd.equalsIgnoreCase("names")) {
                player.sendMessage(Utils.chat("&d-----------------------------------"));
                player.sendMessage(Utils.chat("&aNames and Colors"));
                player.sendMessage(Utils.chat("&d-----------------------------------"));
                player.sendMessage(Utils.chat("&d - &a/colors &d: Lists all the possible color codes for chatting"));
                player.sendMessage(Utils.chat("&d - &a/nick {nickname} &d: Makes a nickname for you"));
            } else {
                player.sendMessage(Utils.chat("&cInvalid command check /info for usages"));
                return false;
            }

        }

        return true;
    }
}
