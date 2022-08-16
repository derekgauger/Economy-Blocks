package craftplugins.economyblocks.Events.GoodEvents;

import craftplugins.economyblocks.BankAccount;
import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.entity.Player;


public class MoneyEvent implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println(player.getName() + " has opened a care package : " + this.getClass().getSimpleName());

        BankAccount bankAccount = bankHandler.getBankAccount(player);

        int amount = Utils.getRandomNumber(2500, 30000);

        bankAccount.deposit(amount);

    }
}
