package craftplugins.economyblocks.Events.GoodEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

import static craftplugins.economyblocks.Utils.createItem;

public class GiveArmor implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println(player.getName() + " has opened a care package : " + this.getClass().getSimpleName());

        Random rand = new Random();
        double randomNum = rand.nextDouble() * 100;

        if (randomNum > 99) {
            ItemStack giveItem = createItem(Material.NETHERITE_CHESTPLATE, Utils.chat("&fNetherite Chestplate"), 1, new Enchantment[]{Enchantment.DURABILITY, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FALL}, new int[]{3, 4, 3});
            Utils.addItemToInventory(giveItem, player);
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getName() + " has been given an enchanted netherite chestplate"));

        } else if (randomNum > 98) {
            ItemStack netherHelm = createItem(Material.NETHERITE_HELMET, Utils.chat("&fNetherite Helmet"), 1, null, null);
            ItemStack netherChest = createItem(Material.NETHERITE_CHESTPLATE, Utils.chat("&fNetherite Chestplate"), 1, null, null);
            ItemStack netherPants = createItem(Material.NETHERITE_LEGGINGS, Utils.chat("&fNetherite Leggings"), 1, null, null);
            ItemStack netherBoots = createItem(Material.NETHERITE_BOOTS, Utils.chat("&fNetherite Boots"), 1, null, null);
            Utils.addItemToInventory(netherHelm, player);
            Utils.addItemToInventory(netherChest, player);
            Utils.addItemToInventory(netherPants, player);
            Utils.addItemToInventory(netherBoots, player);
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getName() + " has been given a full set of netherite armor"));

        } else if (randomNum > 97) {
            ItemStack giveItem = createItem(Material.DIAMOND_CHESTPLATE, Utils.chat("&fDiamond Chestplate"), 1, new Enchantment[]{Enchantment.DURABILITY, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FALL}, new int[]{3, 4, 3});
            Utils.addItemToInventory(giveItem, player);
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getName() + " has been given an enchanted diamond chestplate"));

        } else if (randomNum > 96) {
            ItemStack diamondHelm = createItem(Material.DIAMOND_HELMET, Utils.chat("&fDiamond Helmet"), 1, null, null);
            ItemStack diamondChest = createItem(Material.DIAMOND_CHESTPLATE, Utils.chat("&fDiamond Chestplate"), 1, null, null);
            ItemStack diamondPants = createItem(Material.DIAMOND_LEGGINGS, Utils.chat("&fDiamond Leggings"), 1, null, null);
            ItemStack diamondBoots = createItem(Material.DIAMOND_BOOTS, Utils.chat("&fDiamond Boots"), 1, null, null);
            Utils.addItemToInventory(diamondHelm, player);
            Utils.addItemToInventory(diamondChest, player);
            Utils.addItemToInventory(diamondPants, player);
            Utils.addItemToInventory(diamondBoots, player);
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getName() + " has been given a full set of diamond armor"));

        } else if (randomNum > 93) {
            ItemStack giveItem = createItem(Material.NETHERITE_HELMET, Utils.chat("&fNetherite Helmet"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given a netherite helmet"));

        } else if (randomNum > 90) {
            ItemStack giveItem = createItem(Material.NETHERITE_CHESTPLATE, Utils.chat("&fNetherite Chestplate"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given a netherite chestplate"));

        } else if (randomNum > 87) {
            ItemStack giveItem = createItem(Material.NETHERITE_LEGGINGS, Utils.chat("&fNetherite Leggings"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given netherite leggings"));

        } else if (randomNum > 84) {
            ItemStack giveItem = createItem(Material.NETHERITE_BOOTS, Utils.chat("&fNetherite Boots"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given netherite boots"));

        } else if (randomNum > 78) {
            ItemStack giveItem = createItem(Material.DIAMOND_HELMET, Utils.chat("&fDiamond Helmet"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given a diamond helmet"));

        } else if (randomNum > 72) {
            ItemStack giveItem = createItem(Material.DIAMOND_CHESTPLATE, Utils.chat("&fDiamond Chestplate"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given a diamond chestplate"));

        } else if (randomNum > 66) {
            ItemStack giveItem = createItem(Material.DIAMOND_LEGGINGS, Utils.chat("&fDiamond Leggings"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given diamond leggings"));

        } else if (randomNum > 60) {
            ItemStack giveItem = createItem(Material.DIAMOND_BOOTS, Utils.chat("&fDiamond Boots"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given a diamond boots"));

        } else if (randomNum > 52) {
            ItemStack giveItem = createItem(Material.IRON_CHESTPLATE, Utils.chat("&fIron Chestplate"), 1, new Enchantment[]{Enchantment.DURABILITY, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FALL, Enchantment.THORNS}, new int[]{3, 4, 4, 3});
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given an enchanted iron chestplate"));

        } else if (randomNum > 40) {
            ItemStack chainHelm = createItem(Material.CHAINMAIL_HELMET, Utils.chat("&fChainmail Helmet"), 1, null, null);
            ItemStack chainChest = createItem(Material.CHAINMAIL_CHESTPLATE, Utils.chat("&fChainmail Chestplate"), 1, null, null);
            ItemStack chainPants = createItem(Material.CHAINMAIL_LEGGINGS, Utils.chat("&fChainmail Leggings"), 1, null, null);
            ItemStack chainBoots = createItem(Material.CHAINMAIL_BOOTS, Utils.chat("&fChainmail Boots"), 1, null, null);
            Utils.addItemToInventory(chainHelm, player);
            Utils.addItemToInventory(chainChest, player);
            Utils.addItemToInventory(chainPants, player);
            Utils.addItemToInventory(chainBoots, player);
            player.sendMessage(Utils.chat("&dYou have been given a full set of chain armor"));

        } else {
            ItemStack ironHelm = createItem(Material.IRON_HELMET, Utils.chat("&fIron Helmet"), 1, null, null);
            ItemStack ironChest = createItem(Material.IRON_CHESTPLATE, Utils.chat("&fIron Chestplate"), 1, null, null);
            ItemStack ironPants = createItem(Material.IRON_LEGGINGS, Utils.chat("&fIron Leggings"), 1, null, null);
            ItemStack ironBoots = createItem(Material.IRON_BOOTS, Utils.chat("&fIron Boots"), 1, null, null);
            Utils.addItemToInventory(ironHelm, player);
            Utils.addItemToInventory(ironChest, player);
            Utils.addItemToInventory(ironPants, player);
            Utils.addItemToInventory(ironBoots, player);
            player.sendMessage(Utils.chat("&dYou have been given a full set of iron armor"));
        }

    }
}
