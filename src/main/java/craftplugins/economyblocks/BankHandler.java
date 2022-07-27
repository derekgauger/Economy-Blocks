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

import java.util.ArrayList;
import java.util.List;

public class BankHandler implements Listener, CommandExecutor {

    EconomyBlocks plugin;
    List<BankAccount> bankAccounts = new ArrayList<>();

    public BankHandler(EconomyBlocks plugin) {
        this.plugin = plugin;

        new CarePackageShop(plugin, this);

        Bukkit.getServer().getPluginManager().registerEvents(this,plugin);
        Bukkit.getServer().getPluginCommand("send").setExecutor(this);
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        if (!p.hasPlayedBefore()) {
            bankAccounts.add(new BankAccount(plugin,p, 0));
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        try {
            double amount = Double.parseDouble(args[0]);
            String toUsername = args[1];
            BankAccount sendTo = getBankAccount(toUsername);

            if (!(sender instanceof Player)) {
                sendTo.deposit(amount);
                return true;
            }

            Player player = (Player) sender;
            BankAccount from = getBankAccount(player);

            sendTo.deposit(amount);
            from.withdraw(amount);

        } catch (NumberFormatException e) {
            sender.sendMessage(Utils.chat("&cUsage: /send {amount} {player}"));
            return false;
        }

        return true;
    }

    public BankAccount getBankAccount(String username) {

        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount.player.getName().equalsIgnoreCase(username)) {
                return bankAccount;
            }
        }

        Player player = Bukkit.getServer().getPlayer(username);

        return new BankAccount(plugin, player, 0);
    }

    public BankAccount getBankAccount(Player player) {

        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount.player == player) {
                return bankAccount;
            }
        }

        return new BankAccount(plugin, player, 0);
    }
}
