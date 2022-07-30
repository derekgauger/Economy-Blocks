package craftplugins.economyblocks.Events.BadEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.entity.Player;

public class Fire implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println("Fire");

        player.setFireTicks(20 * 60 * 2);
        player.setFoodLevel(0);
        player.setHealth(10);
        player.sendMessage(Utils.chat("&cFIRE!!!!!"));
    }
}
