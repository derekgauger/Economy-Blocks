package craftplugins.economyblocks.Events;

import craftplugins.economyblocks.BankAccount;
import craftplugins.economyblocks.BankHandler;
import org.bukkit.entity.Player;

import java.util.Random;

public class MoneyEvent implements CarePackageEvent{
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {
        BankAccount bankAccount = bankHandler.getBankAccount(player);

        Random rand = new Random();
        double randomNum = rand.nextDouble() * 100;

        if (randomNum > 95) {
            bankAccount.deposit(21000);

        } else if (randomNum > 90) {
            bankAccount.deposit(16000);

        } else if (randomNum > 80) {
            bankAccount.deposit(8000);

        } else if (randomNum > 65) {
            bankAccount.deposit(5000);

        } else if (randomNum > 50) {
            bankAccount.deposit(3000);

        } else if (randomNum > 30) {
            bankAccount.deposit(2000);

        } else {
            bankAccount.deposit(1000);

        }
    }
}
