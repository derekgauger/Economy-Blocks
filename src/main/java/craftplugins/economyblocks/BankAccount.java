package craftplugins.economyblocks;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BankAccount implements CommandExecutor {

    EconomyBlocks plugin;
    Player player;
    double balance;

    public BankAccount(EconomyBlocks plugin, Player player, double startingBalance) {
        this.plugin = plugin;
        this.player = player;
        this.balance = startingBalance;

        Bukkit.getServer().getPluginCommand("balance").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender == player) {
            player.sendMessage(Utils.chat("&aBalance $" + balance));
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            player.sendMessage(Utils.chat("&cCannot deposit a negative amount"));
            return;
        }

        balance += amount;
    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            player.sendMessage(Utils.chat("&cCannot withdraw a negative amount"));
            return;
        }

        if (balance - amount >= 0) {
            balance -= amount;

        } else {
            player.sendMessage(Utils.chat("&cCannot withdraw $" + amount + " Current Balance: $" + balance));
        }
    }

    public double getBalance() {
        return balance;
    }

}
