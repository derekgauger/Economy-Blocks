package craftplugins.economyblocks.Events;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.EconomyBlocks;
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
    ItemStack tierOne = Utils.createItem(Material.WHITE_WOOL, Utils.chat("&f&lCare Package Tier 1"),1,Enchantment.MENDING, 1, Utils.chat("&aCost: $" + tier1Cost));

    // 25%
    ItemStack tierTwo = Utils.createItem(Material.LIME_WOOL,Utils.chat("&a&lCare Package Tier 2"),1,Enchantment.MENDING, 1, Utils.chat("&aCost: $" + tier2Cost));

    // 20%
    ItemStack tierThree = Utils.createItem(Material.LIGHT_BLUE_WOOL,Utils.chat("&b&lCare Package Tier 3"),1,Enchantment.MENDING, 1, Utils.chat("&aCost: $" + tier3Cost));

    // 15%
    ItemStack tierFour = Utils.createItem(Material.PURPLE_WOOL,Utils.chat("&5&lCare Package Tier 4"), 1,Enchantment.MENDING, 1, Utils.chat("&aCost: $" + tier4Cost));

    // 10%
    ItemStack tierFive = Utils.createItem(Material.ORANGE_WOOL,Utils.chat("&6&lCare Package Tier 5"), 1,Enchantment.MENDING, 1, Utils.chat("&aCost: $" + tier5Cost));


    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        Random rand = new Random();
        double randomNum = rand.nextDouble() * 100;

        if (randomNum > 90) {
            player.getInventory().addItem(tierFive);

        } else if (randomNum > 75) {
            player.getInventory().addItem(tierFour);

        } else if (randomNum > 55) {
            player.getInventory().addItem(tierThree);

        } else if (randomNum > 35) {
            player.getInventory().addItem(tierTwo);

        } else {
            player.getInventory().addItem(tierOne);

        }
    }
}
