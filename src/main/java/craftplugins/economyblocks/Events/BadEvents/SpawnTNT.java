package craftplugins.economyblocks.Events.BadEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SpawnTNT implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println("Spawn TNT");

        for (int i = 0; i < 9; i++) {
            player.getWorld().spawnEntity(player.getLocation(), EntityType.PRIMED_TNT);
        }
        player.sendMessage(Utils.chat("&dThere are 9 TNT here... Run."));
    }
}
