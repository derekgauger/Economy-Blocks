package craftplugins.economyblocks.Events;

import craftplugins.economyblocks.BankHandler;
import org.bukkit.entity.Player;

public interface CarePackageEvent {

    void runEvent(Player player, BankHandler bankHandler);

}
