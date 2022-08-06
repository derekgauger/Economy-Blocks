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

public class GiveWeaponsAndHorseArmor implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println(player.getName() + " has opened a care package : " + this.getClass().getSimpleName());

        Random rand = new Random();
        double randomNum = rand.nextDouble() * 100;

        if (randomNum > 99) {
            ItemStack giveItem = createItem(Material.TRIDENT, Utils.chat("&fTrident"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getName() + " has been given a trident"));

        } else if (randomNum > 98) {
            ItemStack giveItem = createItem(Material.NETHERITE_AXE, Utils.chat("&fNetherite Axe"), 1, new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.DURABILITY}, new int[]{5, 3});
            Utils.addItemToInventory(giveItem, player);
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getName() + " has been given an enchanted netherite axe"));

        } else if (randomNum > 97) {
            ItemStack giveItem = createItem(Material.NETHERITE_SWORD, Utils.chat("&fNetherite Sword"), 1, new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.DURABILITY, Enchantment.LOOT_BONUS_MOBS}, new int[]{5, 3, 3});
            Utils.addItemToInventory(giveItem, player);
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getName() + " has been given an enchanted netherite sword"));

        } else if (randomNum > 95) {
            ItemStack giveItem = createItem(Material.DIAMOND_AXE, Utils.chat("&fDiamond Axe"), 1, new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.DURABILITY}, new int[]{5, 3});
            Utils.addItemToInventory(giveItem, player);
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getName() + " has been given an enchanted diamond axe"));

        } else if (randomNum > 93) {
            ItemStack giveItem = createItem(Material.DIAMOND_SWORD, Utils.chat("&fDiamond Sword"), 1, new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.DURABILITY, Enchantment.LOOT_BONUS_MOBS}, new int[]{5, 3, 3});
            Utils.addItemToInventory(giveItem, player);
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getName() + " has been given an enchanted diamond sword"));

        } else if (randomNum > 91) {
            ItemStack giveItem = createItem(Material.NETHERITE_AXE, Utils.chat("&fNetherite Axe"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given a netherite axe"));

        } else if (randomNum > 89) {
            ItemStack giveItem = createItem(Material.NETHERITE_SWORD, Utils.chat("&fNetherite Sword"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given a netherite sword"));

        } else if (randomNum > 86) {
            ItemStack giveItem = createItem(Material.DIAMOND_AXE, Utils.chat("&fDiamond Axe"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given a diamond axe"));

        } else if (randomNum > 83) {
            ItemStack giveItem = createItem(Material.DIAMOND_SWORD, Utils.chat("&fDiamond Sword"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given a diamond sword"));

        } else if (randomNum > 78) {
            ItemStack giveItem = createItem(Material.DIAMOND_HORSE_ARMOR, Utils.chat("&fDiamond Horse Armor"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given diamond horse armor"));

        } else if (randomNum > 70) {
            ItemStack giveItem = createItem(Material.GOLDEN_HORSE_ARMOR, Utils.chat("&fGolden Horse Armor"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given golden horse armor"));

        } else if (randomNum > 60) {
            ItemStack giveItem = createItem(Material.IRON_HORSE_ARMOR, Utils.chat("&fIron Horse Armor"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given iron horse armor"));

        } else if (randomNum > 50) {
            ItemStack goldSword = createItem(Material.GOLDEN_SWORD, Utils.chat("&fGolden Sword"), 1, null, null);
            ItemStack goldAxe = createItem(Material.GOLDEN_AXE, Utils.chat("&fGolden Axe"), 1, null, null);
            Utils.addItemToInventory(goldSword, player);
            Utils.addItemToInventory(goldAxe, player);
            player.sendMessage(Utils.chat("&dYou have been given a set of golden weapons"));

        } else if (randomNum > 40) {
            ItemStack ironSword = createItem(Material.IRON_SWORD, Utils.chat("&fIron Sword"), 1, null, null);
            ItemStack ironAxe = createItem(Material.IRON_AXE, Utils.chat("&fIron Axe"), 1, null, null);
            Utils.addItemToInventory(ironSword, player);
            Utils.addItemToInventory(ironAxe, player);
            player.sendMessage(Utils.chat("&dYou have been given a set of iron weapons"));

        } else if (randomNum > 25) {
            ItemStack giveItem = createItem(Material.LEATHER_HORSE_ARMOR, Utils.chat("&fLeather Horse Armor"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given leather horse armor"));

        } else {
            ItemStack giveItem = createItem(Material.WOODEN_SWORD, Utils.chat("&6&lGod Sword"), 1, new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.LOOT_BONUS_MOBS, Enchantment.KNOCKBACK, Enchantment.FIRE_ASPECT, Enchantment.DURABILITY}, new int[]{5, 3, 2, 2, 3});
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given a wooden sword"));

        }

    }
}
