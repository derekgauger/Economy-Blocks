package craftplugins.economyblocks;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class BankHandler implements Listener, CommandExecutor {

    EconomyBlocks plugin;
    List<BankAccount> bankAccounts;

    public BankHandler(EconomyBlocks plugin) {
        this.plugin = plugin;

        bankAccounts = loadData("bank.data");

        if (bankAccounts == null) {
            bankAccounts = new ArrayList<>();
        }

        for (BankAccount ba : bankAccounts) {
            Player p = Bukkit.getPlayer(UUID.fromString(ba.uuid));
            if (p != null) {
                ba.setPlayer(UUID.fromString(ba.uuid));
            }
        }

        new CarePackageShop(plugin, this);
        new MineralShop(plugin, this);

        Bukkit.getServer().getPluginManager().registerEvents(this,plugin);
        Bukkit.getServer().getPluginCommand("send").setExecutor(this);
        Bukkit.getServer().getPluginCommand("balance").setExecutor(this);
    }

    private void print_bank(BankAccount ba) {
        if (ba.uuid != null) {
            System.out.println(ba.uuid);
            ba.setPlayer(UUID.fromString(ba.uuid));
            System.out.println(ba.player.getName());
            System.out.println(ba.balance);
        }
    }

    public boolean saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(bankAccounts);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<BankAccount> loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            bankAccounts = (List<BankAccount>) in.readObject();
            in.close();
            return bankAccounts;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }



    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        if (!p.hasPlayedBefore()) {
            bankAccounts.add(new BankAccount(plugin,p.getUniqueId(), 0));
        }

        for (BankAccount ba : bankAccounts) {
            if (ba.uuid.equals(p.getUniqueId().toString())) {
                ba.setPlayer(UUID.fromString(ba.uuid));

            }
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (label.equalsIgnoreCase("send")) {
            try {
                String toUsername = args[0];

                double amount = Double.parseDouble(args[1]);

                if (!checkPlayerExists(toUsername)) {
                    sender.sendMessage(Utils.chat("&cUsername: '&4" + toUsername + "&c' not found."));
                    return false;
                }

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
                sender.sendMessage(Utils.chat("&cUsage: /send {player} {amount}"));
                return false;
            }
        } else if (label.equalsIgnoreCase("bal") || label.equalsIgnoreCase("balance")) {

            if (!(sender instanceof Player)) {
                System.out.println("Only players in game can do that");
                return true;
            }

            Player player = (Player) sender;

            BankAccount bankAccount = getBankAccount(player);
            player.sendMessage(Utils.chat("&aBalance: $" + Utils.format(bankAccount.getBalance())));

        }

        return true;
    }

    private boolean checkPlayerExists(String username) {
        return Bukkit.getPlayer(username) != null;
    }

    public BankAccount getBankAccount(String username) {

        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount.player.getName().equalsIgnoreCase(username)) {
                return bankAccount;
            }
        }

        Player player = Bukkit.getServer().getPlayer(username);

        BankAccount ba = new BankAccount(plugin, player.getUniqueId(), 0);
        bankAccounts.add(ba);

        return ba;
    }

    public BankAccount getBankAccount(Player player) {

        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount.player == player) {
                return bankAccount;
            }
        }

        BankAccount ba = new BankAccount(plugin, player.getUniqueId(), 0);
        bankAccounts.add(ba);

        return ba;
    }
}
