package craftplugins.economyblocks.CarePackages;

import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Events.PiggyParty;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

abstract class CarePackage {

    public void good(Player player) {
        List<CarePackageEvent> goodEvents = getGoodEvents();
        goodEvents.get(0).runEvent(player);
        player.sendMessage("good");
    }

    public void bad(Player player) {
        player.sendMessage("bad");
    }

    private List<CarePackageEvent> getGoodEvents() {
        List<CarePackageEvent> goodEvents = new ArrayList<>();
        goodEvents.add(new PiggyParty());
        return goodEvents;
    }

}
