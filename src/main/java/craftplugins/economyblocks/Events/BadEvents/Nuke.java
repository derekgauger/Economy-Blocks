package craftplugins.economyblocks.Events.BadEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

public class Nuke implements CarePackageEvent {

    int timer;
    Location location;

    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println("Nuke");

        this.location = player.getLocation();

        timer = Bukkit.getScheduler().scheduleSyncRepeatingTask(player.getServer().getPluginManager().getPlugin("EconomyBlocks"), new Runnable() {
            int count = 0;

            @Override
            public void run() {
                Bukkit.broadcastMessage(Utils.chat("&4NUKE IN " + (10 - count++) + "!!!!"));

                if (count > 10) {
                    nukeIt();
                }

            }
        }, 0, 20 * 1);
    }

    private void nukeIt() {
        Bukkit.getScheduler().cancelTask(timer);

        Random rand = new Random();
        double randomNum = rand.nextDouble() * 100;

        if (randomNum > 50) {

            location.getWorld().createExplosion(location, 100);
            location.getWorld().createExplosion(location.add(20, 0, 0), 75);
            location.getWorld().createExplosion(location.add(-20, 0, 0), 75);
            location.getWorld().createExplosion(location.add(0, 0, 20), 75);
            location.getWorld().createExplosion(location.add(0, 0, -20), 75);
            location.getWorld().createExplosion(location.add(0, 20, 0), 75);
            location.getWorld().createExplosion(location.add(0, -20, 0), 75);
        } else {
            Bukkit.broadcastMessage(Utils.chat("&dYou look real dumb running from nothing right now..."));

        }
    }
}
