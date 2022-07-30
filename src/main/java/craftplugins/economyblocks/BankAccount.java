package craftplugins.economyblocks;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

public class BankAccount implements Serializable {

    private static transient final long serialVersionUID = -1681012206529286330L;

    transient EconomyBlocks plugin;
    transient Player player;
    double balance;
    String uuid;

    public BankAccount(EconomyBlocks plugin, UUID uuid, double startingBalance) {
        this.plugin = plugin;
        this.balance = startingBalance;
        this.uuid = uuid.toString();
        setPlayer(uuid);
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

    public void setPlayer(UUID uuid) {
        player = Bukkit.getPlayer(uuid);

    }

}
