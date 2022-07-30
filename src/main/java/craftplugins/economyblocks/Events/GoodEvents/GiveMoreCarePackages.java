package craftplugins.economyblocks.Events.GoodEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.EconomyBlocks;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
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

        System.out.println("Give Care Package");

        Random rand = new Random();
        double randomNum = rand.nextDouble() * 100;

        if (randomNum > 90) {
            player.getInventory().addItem(tierFive);
            player.sendMessage(Utils.chat("&dYou have been given a &6Tier 5 Care Package"));

        } else if (randomNum > 75) {
            player.getInventory().addItem(tierFour);
            player.sendMessage(Utils.chat("&dYou have been given a &5Tier 4 Care Package"));

        } else if (randomNum > 55) {
            player.getInventory().addItem(tierThree);
            player.sendMessage(Utils.chat("&dYou have been given a &bTier 3 Care Package"));

        } else if (randomNum > 35) {
            player.getInventory().addItem(tierTwo);
            player.sendMessage(Utils.chat("&dYou have been given a &aTier 2 Care Package"));

        } else {
            player.getInventory().addItem(tierOne);
            player.sendMessage(Utils.chat("&dYou have been given a &fTier 1 Care Package"));

        }
    }
}
