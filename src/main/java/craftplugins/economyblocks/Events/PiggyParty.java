package craftplugins.economyblocks.Events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class PiggyParty implements CarePackageEvent{
    @Override
    public void runEvent(Player player) {
        player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
    }
}
