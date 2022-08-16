package craftplugins.economyblocks.Events.GoodEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.entity.Player;

public class GiveExp implements CarePackageEvent {

    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        int levels = Utils.getRandomNumber(10, 20);

        player.setLevel(player.getLevel() + levels);
        player.sendMessage(Utils.chat("&dYou have been given " + levels + " experience levels"));
    }
}
