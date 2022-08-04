package craftplugins.economyblocks.Events.BadEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class RandomTeleport implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println("Random Teleport");

        double randomXDelta = Utils.getRandomNumber(0, 500);
        double randomZDelta = Utils.getRandomNumber(0, 500);

        Location location = player.getLocation();

        double yValue = player.getWorld().getHighestBlockYAt((int) (player.getLocation().getX() + randomXDelta), (int) (player.getLocation().getZ() + randomZDelta));

        Location newLocation = new Location(player.getWorld(), player.getLocation().getX() + randomXDelta, yValue, player.getLocation().getZ() + randomZDelta);

        player.teleport(newLocation);
        player.sendMessage(Utils.chat("&dTeleported you away..."));
        player.sendMessage(Utils.chat("&dPrevious Location - X: " + Utils.format(location.getX()) + ", Y: " + Utils.format(location.getY()) + ", Z: " + Utils.format(location.getZ())));

    }
}
