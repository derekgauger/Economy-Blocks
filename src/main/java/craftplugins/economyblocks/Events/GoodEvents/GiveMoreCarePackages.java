package craftplugins.economyblocks.Events.GoodEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class GiveMoreCarePackages implements CarePackageEvent {

    static final double tier1Cost = 2000;
    static final double tier2Cost = 4000;
    static final double tier3Cost = 6000;
    static final double tier4Cost = 10000;
    static final double tier5Cost = 16000;

    // 30%
    ItemStack tierOne = Utils.createItem(Material.WHITE_WOOL, Utils.chat("&f&lCare Package Tier 1"),1, new Enchantment[]{Enchantment.MENDING}, new int[]{1}, Utils.chat("&aCost: $" + tier1Cost));

    // 25%
    ItemStack tierTwo = Utils.createItem(Material.LIME_WOOL,Utils.chat("&a&lCare Package Tier 2"),1, new Enchantment[]{Enchantment.MENDING}, new int[]{1}, Utils.chat("&aCost: $" + tier2Cost));

    // 20%
    ItemStack tierThree = Utils.createItem(Material.LIGHT_BLUE_WOOL,Utils.chat("&b&lCare Package Tier 3"),1, new Enchantment[]{Enchantment.MENDING}, new int[]{1}, Utils.chat("&aCost: $" + tier3Cost));

    // 15%
    ItemStack tierFour = Utils.createItem(Material.PURPLE_WOOL,Utils.chat("&5&lCare Package Tier 4"), 1, new Enchantment[]{Enchantment.MENDING}, new int[]{1}, Utils.chat("&aCost: $" + tier4Cost));

    // 10%
    ItemStack tierFive = Utils.createItem(Material.ORANGE_WOOL,Utils.chat("&6&lCare Package Tier 5"), 1, new Enchantment[]{Enchantment.MENDING}, new int[]{1}, Utils.chat("&aCost: $" + tier5Cost));


    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println(player.getName() + " has opened a care package : " + this.getClass().getSimpleName());

        Random rand = new Random();
        double randomNum = rand.nextDouble() * 100;

        int randomAmount = Utils.getRandomNumber(1,5);

        if (randomNum > 90) {
            for (int i = 0; i < randomAmount; i++) {
                Utils.addItemToInventory(tierFive, player);
            }
            player.sendMessage(Utils.chat("&dYou have been given " + randomAmount + " &6Tier 5 Care Package(s)"));

        } else if (randomNum > 75) {
            for (int i = 0; i < randomAmount; i++) {
                Utils.addItemToInventory(tierFour, player);
            }
            player.sendMessage(Utils.chat("&dYou have been given " + randomAmount + " &6Tier 4 Care Package(s)"));

        } else if (randomNum > 55) {
            for (int i = 0; i < randomAmount; i++) {
                Utils.addItemToInventory(tierThree, player);
            }
            player.sendMessage(Utils.chat("&dYou have been given " + randomAmount + " &6Tier 3 Care Package(s)"));

        } else if (randomNum > 35) {
            for (int i = 0; i < randomAmount; i++) {
                Utils.addItemToInventory(tierTwo, player);
            }
            player.sendMessage(Utils.chat("&dYou have been given " + randomAmount + " &6Tier 2 Care Package(s)"));

        } else {
            for (int i = 0; i < randomAmount; i++) {
                Utils.addItemToInventory(tierOne, player);
            }
            player.sendMessage(Utils.chat("&dYou have been given " + randomAmount + " &6Tier 1 Care Package(s)"));

        }
    }
}
