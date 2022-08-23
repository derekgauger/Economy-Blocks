package craftplugins.economyblocks.CarePackages;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.*;
import craftplugins.economyblocks.Events.BadEvents.*;
import craftplugins.economyblocks.Events.GoodEvents.*;
import craftplugins.economyblocks.Utils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

abstract class CarePackage {

    public void good(Player player, BankHandler bankHandler, int tier) {
        List<CarePackageEvent> goodEvents = getGoodEvents();

        int repeats = 1;

        if (tier == 5) {
            repeats = 3;
        } else if (tier == 3 || tier == 4) {
            repeats = 2;
        }

        for (int i = 0; i < repeats; i++) {
            int randomNum = Utils.getRandomNumber(0, goodEvents.size());
            CarePackageEvent event = goodEvents.get(randomNum);
            if (event.getClass() == Party.class || event.getClass() == ChangeBlocksToOres.class) {
                goodEvents.remove(0);
                goodEvents.remove(0);
            }
            event.runEvent(player, bankHandler);

        }
    }

    public void bad(Player player, BankHandler bankHandler) {
        boolean alternateDimension = false;
        if (Utils.getWorldName(player.getWorld().getUID()).equalsIgnoreCase("End") || Utils.getWorldName(player.getWorld().getUID()).equalsIgnoreCase("Nether")) {
            alternateDimension = true;
        }
        List<CarePackageEvent> badEvents = getBadEvents(alternateDimension);

        int randomNum = Utils.getRandomNumber(0, badEvents.size());

        badEvents.get(randomNum).runEvent(player, bankHandler);
    }

    private List<CarePackageEvent> getGoodEvents() {
        List<CarePackageEvent> goodEvents = new ArrayList<>();
        goodEvents.add(new ChangeBlocksToOres());
        goodEvents.add(new Party());
        goodEvents.add(new GiveArmor());
        goodEvents.add(new GiveBlocks());
        goodEvents.add(new GiveEffects());
        goodEvents.add(new GiveItems());
        goodEvents.add(new GiveMoreCarePackages());
        goodEvents.add(new GiveTools());
        goodEvents.add(new GiveWeaponsAndHorseArmor());
        goodEvents.add(new MoneyEvent());
        goodEvents.add(new GiveExp());

        return goodEvents;
    }

    private List<CarePackageEvent> getBadEvents(boolean alternateDimension) {
        List<CarePackageEvent> badEvents = new ArrayList<>();
        badEvents.add(new Boost());
        badEvents.add(new ChangeBlocksHarmful());
        badEvents.add(new ChangeBlocksSuffocation());
        badEvents.add(new Death());
        badEvents.add(new Fire());
        badEvents.add(new Nuke());
        if (!alternateDimension) {
            badEvents.add(new RandomTeleport());
        }
        badEvents.add(new RemoveMoney());
        badEvents.add(new SpawnEnemies());
        badEvents.add(new SpawnTNT());
        badEvents.add(new Insult());

        return badEvents;
    }



}
