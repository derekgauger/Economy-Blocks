package craftplugins.economyblocks.Events.BadEvents;

import craftplugins.economyblocks.BankAccount;
import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.entity.Player;

public class RemoveMoney implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {
        System.out.println(player.getName() + " has opened a care package : " + this.getClass().getSimpleName());
        BankAccount bankAccount = bankHandler.getBankAccount(player);

        double balance = bankAccount.getBalance();

        if (balance == 0.0) {
            player.sendMessage(Utils.chat("&dI would have taken your money... but you are broke... that's rough buddy"));
            return;
        }
        double removeAmount = balance / 5;

        bankAccount.withdraw(removeAmount);

    }
}
