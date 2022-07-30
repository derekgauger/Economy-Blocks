package craftplugins.economyblocks.Events.GoodEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

import static craftplugins.economyblocks.Utils.createItem;

public class GiveTools implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println("Give Tools");

        Random rand = new Random();
        double randomNum = rand.nextDouble() * 100;

        if (randomNum > 99) {
            ItemStack giveItem = createItem(Material.NETHERITE_PICKAXE, Utils.chat("&fNetherite Pickaxe"), 1, new Enchantment[]{Enchantment.DURABILITY, Enchantment.DIG_SPEED, Enchantment.LOOT_BONUS_BLOCKS}, new int[]{3, 5, 3});
            player.getInventory().addItem(giveItem);
            player.sendMessage(Utils.chat("&dYou have been given an enchanted netherite pickaxe"));

        } else if (randomNum > 98) {
            ItemStack giveItem = createItem(Material.DIAMOND_PICKAXE, Utils.chat("&fDiamond Pickaxe"), 1, new Enchantment[]{Enchantment.DURABILITY, Enchantment.DIG_SPEED, Enchantment.LOOT_BONUS_BLOCKS}, new int[]{3, 5, 3});
            player.getInventory().addItem(giveItem);
            player.sendMessage(Utils.chat("&dYou have been given an enchanted diamond pickaxe"));

        } else if (randomNum > 95) {
            ItemStack giveItem = createItem(Material.DIAMOND_PICKAXE, Utils.chat("&fDiamond Pickaxe"), 1, new Enchantment[]{Enchantment.SILK_TOUCH}, new int[]{1});
            player.getInventory().addItem(giveItem);
            player.sendMessage(Utils.chat("&dYou have been given a silk touch diamond pickaxe"));

        } else if (randomNum > 92) {
            ItemStack giveItem = createItem(Material.NETHERITE_PICKAXE, Utils.chat("&fNetherite Pickaxe"), 1, null, null);
            player.getInventory().addItem(giveItem);
            player.sendMessage(Utils.chat("&dYou have been given a netherite pickaxe"));

        } else if (randomNum > 87) {
            ItemStack giveItem = createItem(Material.DIAMOND_PICKAXE, Utils.chat("&fDiamond Pickaxe"), 1, null, null);
            player.getInventory().addItem(giveItem);
            player.sendMessage(Utils.chat("&dYou have been given a diamond pickaxe"));

        } else if (randomNum > 80) {
            ItemStack giveItem = createItem(Material.NETHERITE_SHOVEL, Utils.chat("&fNetherite Shovel"), 1, null, null);
            player.getInventory().addItem(giveItem);
            player.sendMessage(Utils.chat("&dYou have been given a netherite shovel"));

        } else if (randomNum > 73) {
            ItemStack giveItem = createItem(Material.DIAMOND_SHOVEL, Utils.chat("&fDiamond Shovel"), 1, null, null);
            player.getInventory().addItem(giveItem);
            player.sendMessage(Utils.chat("&dYou have been given a diamond shovel"));

        } else if (randomNum > 60) {
            ItemStack giveItem = createItem(Material.NETHERITE_HOE, Utils.chat("&fNetherite Hoe"), 1, new Enchantment[]{Enchantment.DURABILITY}, new int[]{3});
            player.getInventory().addItem(giveItem);
            player.sendMessage(Utils.chat("&dYou have been given a netherite hoe"));

        } else if (randomNum > 40) {
            ItemStack giveItem = createItem(Material.DIAMOND_HOE, Utils.chat("&fDiamond Hoe"), 1, new Enchantment[]{Enchantment.DURABILITY}, new int[]{3});
            player.getInventory().addItem(giveItem);
            player.sendMessage(Utils.chat("&dYou have been given a diamond hoe"));

        } else {
            ItemStack ironPick = createItem(Material.IRON_PICKAXE, Utils.chat("&fIron Pickaxe"), 1, null, null);
            ItemStack ironAxe = createItem(Material.IRON_AXE, Utils.chat("&fIron Axe"), 1, null, null);
            ItemStack ironShovel = createItem(Material.IRON_SHOVEL, Utils.chat("&fIron Shovel"), 1, null, null);
            ItemStack ironHoe = createItem(Material.IRON_HOE, Utils.chat("&fIron Hoe"), 1, null, null);
            player.getInventory().addItem(ironPick);
            player.getInventory().addItem(ironAxe);
            player.getInventory().addItem(ironShovel);
            player.getInventory().addItem(ironHoe);
            player.sendMessage(Utils.chat("&dYou have been given a set of iron tools"));

        }

    }
}
