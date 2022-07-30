package craftplugins.economyblocks;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class BankAccount implements Serializable {

    private static transient final long serialVersionUID = -1681012206529286330L;

    transient EconomyBlocks plugin;
    Player player;
    double balance;

    public BankAccount(EconomyBlocks plugin, Player player, double startingBalance) {
        this.plugin = plugin;
        this.player = player;
        this.balance = startingBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            player.sendMessage(Utils.chat("&cCannot deposit a negative amount"));
            return;
        }

        balance += amount;
        player.sendMessage(Utils.chat("&a$" + Utils.format(amount) + " has been sent to your account!"));

    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            player.sendMessage(Utils.chat("&cCannot withdraw a negative amount"));
            return;
        }

        if (balance - amount >= 0) {
            balance -= amount;
            player.sendMessage(Utils.chat("&c$" + Utils.format(amount) + " has been removed to your account!"));

        } else {
            player.sendMessage(Utils.chat("&cCannot withdraw $" + Utils.format(amount) + " Current Balance: $" + Utils.format(balance)));
        }
    }

    public double getBalance() {
        return balance;
    }

}
