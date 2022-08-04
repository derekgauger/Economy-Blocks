package craftplugins.economyblocks.Events.GoodEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class GiveItems implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println("Give Items");

        ItemStack smiteStick = Utils.createItem(Material.STICK, Utils.chat("&bLightning Stick"), 1,null, null);
        ItemStack kbStick = Utils.createItem(Material.STICK, Utils.chat("&6Knockback Stick"), 1, new Enchantment[]{Enchantment.KNOCKBACK}, new int[]{10});
        ItemStack goldenApples = Utils.createItem(Material.GOLDEN_APPLE, Utils.chat("&6Golden Apple"), 64,null, null);
        ItemStack enchantedGoldenApples = Utils.createItem(Material.ENCHANTED_GOLDEN_APPLE, Utils.chat("&6Enchanted Golden Apple"), 5,null, null);
        ItemStack enderChest = Utils.createItem(Material.ENDER_CHEST, Utils.chat("&fEnderchest"), 1, null, null);
        ItemStack netherite = Utils.createItem(Material.NETHERITE_INGOT, Utils.chat("&fNetherite Ingot"), 2, null, null);
        ItemStack diamond = Utils.createItem(Material.DIAMOND, Utils.chat("&fDiamond"), 16, null, null);
        ItemStack gold = Utils.createItem(Material.GOLD_INGOT, Utils.chat("&fGold Ingot"), 32, null, null);
        ItemStack iron = Utils.createItem(Material.IRON_INGOT, Utils.chat("&fIron Ingot"), 32, null, null);
        ItemStack lapis = Utils.createItem(Material.LAPIS_ORE, Utils.chat("&fLapis Ore"), 20, null, null);
        ItemStack redstone = Utils.createItem(Material.REDSTONE_ORE, Utils.chat("&fRedstone Ore"), 20, null, null);

        Random rand = new Random();
        double randomNum = rand.nextDouble() * 100;

        if (randomNum > 98) {
            Utils.addItemToInventory(smiteStick, player);
            player.sendMessage(Utils.chat("&dYou have been given a smite stick!"));

        } else if (randomNum > 93) {
            Utils.addItemToInventory(kbStick, player);
            player.sendMessage(Utils.chat("&dYou have been given a knockback stick!"));

        } else if (randomNum > 85) {
            Utils.addItemToInventory(enderChest, player);
            player.sendMessage(Utils.chat("&dYou have been given an Enderchest!"));

        } else if (randomNum > 80) {
            Utils.addItemToInventory(enchantedGoldenApples, player);
            player.sendMessage(Utils.chat("&dYou have been given golden apples"));

        } else if (randomNum > 75) {
            Utils.addItemToInventory(goldenApples, player);
            player.sendMessage(Utils.chat("&dYou have been given enchanted golden apples!"));

        } else if (randomNum > 70) {
            Utils.addItemToInventory(netherite, player);
            player.sendMessage(Utils.chat("&dYou have been given netherite ingots!"));

        }  else if (randomNum > 65) {
            Utils.addItemToInventory(diamond, player);
            player.sendMessage(Utils.chat("&dYou have been given diamonds!"));

        } else if (randomNum > 50) {
            Utils.addItemToInventory(gold, player);
            player.sendMessage(Utils.chat("&dYou have been given gold ingots!"));

        } else if (randomNum > 40) {
            Utils.addItemToInventory(iron, player);
            player.sendMessage(Utils.chat("&dYou have been given iron ingots!"));

        } else if (randomNum > 25) {
            Utils.addItemToInventory(lapis, player);
            player.sendMessage(Utils.chat("&dYou have been given lapis ore!"));

        } else {
            Utils.addItemToInventory(redstone, player);
            player.sendMessage(Utils.chat("&dYou have been given redstone ore!"));

        }
    }
}
