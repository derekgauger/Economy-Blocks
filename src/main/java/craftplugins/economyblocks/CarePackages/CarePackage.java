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

    public void good(Player player, BankHandler bankHandler) {
        List<CarePackageEvent> goodEvents = getGoodEvents();

        int randomNum = Utils.getRandomNumber(0, goodEvents.size());

        goodEvents.get(randomNum).runEvent(player, bankHandler);
    }

    public void bad(Player player, BankHandler bankHandler) {
        List<CarePackageEvent> badEvents = getBadEvents();

        int randomNum = Utils.getRandomNumber(0, badEvents.size());

        badEvents.get(randomNum).runEvent(player, bankHandler);
    }

    private List<CarePackageEvent> getGoodEvents() {
        List<CarePackageEvent> goodEvents = new ArrayList<>();
        goodEvents.add(new ChangeBlocksToOres());
        goodEvents.add(new GiveArmor());
        goodEvents.add(new GiveBlocks());
        goodEvents.add(new GiveEffects());
        goodEvents.add(new GiveItems());
        goodEvents.add(new GiveMoreCarePackages());
        goodEvents.add(new GiveTools());
        goodEvents.add(new GiveWeaponsAndHorseArmor());
        goodEvents.add(new MoneyEvent());
        goodEvents.add(new Party());
        goodEvents.add(new GiveExp());

        return goodEvents;
    }

    private List<CarePackageEvent> getBadEvents() {
        List<CarePackageEvent> badEvents = new ArrayList<>();
        badEvents.add(new Boost());
        badEvents.add(new ChangeBlocksHarmful());
        badEvents.add(new ChangeBlocksSuffocation());
        badEvents.add(new Death());
        badEvents.add(new Fire());
        badEvents.add(new Nuke());
        badEvents.add(new RandomTeleport());
        badEvents.add(new RemoveMoney());
        badEvents.add(new SpawnEnemies());
        badEvents.add(new SpawnTNT());
        badEvents.add(new Insult());

        return badEvents;
    }

}
