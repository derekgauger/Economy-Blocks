package craftplugins.economyblocks.Events.BadEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Boost implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {
        System.out.println("Boost");
        player.setVelocity(new Vector(0, 10, 0));
        player.sendMessage(Utils.chat("&dWeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee...."));
    }
}
