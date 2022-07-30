package craftplugins.economyblocks.Events.BadEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

public class Death implements CarePackageEvent {

    @Override
    public void runEvent(Player player, BankHandler bankHandler) {
        System.out.println("Death");

        Location location = player.getLocation();

        Random rand = new Random();
        double randomNum = rand.nextDouble() * 100;

        if (randomNum > 50) {

            player.setHealth(0);
            player.sendMessage(Utils.chat("&dThe care package killed you..."));
            player.sendMessage(Utils.chat("&dPrevious Location - X: " + Utils.format(location.getX()) + ", Y: " + Utils.format(location.getY()) + ", Z: " + Utils.format(location.getZ())));
        } else {
            player.sendMessage(Utils.chat("&dThe care package tried to kill you but failed..."));
        }
    }
}
