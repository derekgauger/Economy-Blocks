package craftplugins.economyblocks.Events.GoodEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

import static craftplugins.economyblocks.Utils.createItem;

public class GiveBlocks implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println("Give Blocks");

        Random rand = new Random();
        double randomNum = rand.nextDouble() * 100;

        if (randomNum > 99) {
            ItemStack giveItem = createItem(Material.BEACON, Utils.chat("&bBeacon"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getName() + " has been given a &bBeacon"));

        } else if (randomNum > 98) {
            ItemStack giveItem = createItem(Material.NETHERITE_BLOCK, Utils.chat("&fNetherite Block"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getName() + " has been given a netherite block"));

        } else if (randomNum > 97) {
            ItemStack giveItem = createItem(Material.DIAMOND_BLOCK, Utils.chat("&fDiamond Block"), 4, null, null);
            Utils.addItemToInventory(giveItem, player);
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getName() + " has been given 4 diamond blocks"));

        } else if (randomNum > 95) {
            ItemStack giveItem = createItem(Material.SHULKER_BOX, Utils.chat("&5Shulker Box"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            Bukkit.broadcastMessage(Utils.chat("&d" + player.getName() + " has been given a shulker box"));

        } else if (randomNum > 92) {
            ItemStack giveItem = createItem(Material.CHEST, Utils.chat("&fChest"), 128, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given 2 stacks of chests"));

        } else if (randomNum > 88) {
            ItemStack giveItem = createItem(Material.ENDER_CHEST, Utils.chat("&fEnderchest"), 1, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given an enderchest"));

        } else if (randomNum > 83) {
            ItemStack giveItem = createItem(Material.GOLD_BLOCK, Utils.chat("&fGold Block"), 8, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given 8 gold blocks"));

        } else if (randomNum > 75) {
            ItemStack giveItem = createItem(Material.IRON_BLOCK, Utils.chat("&fIron Block"), 8, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given 8 iron blocks"));

        } else if (randomNum > 65) {
            ItemStack giveItem = createItem(Material.OBSIDIAN, Utils.chat("&fObsidian"), 32, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given 32 obsidian"));

        } else if (randomNum > 50) {
            ItemStack giveItem = createItem(Material.END_STONE, Utils.chat("&fEndstone"), 64, null, null);
            Utils.addItemToInventory(giveItem, player);
            player.sendMessage(Utils.chat("&dYou have been given a stack of endstone"));

        } else if (randomNum > 30) {
            ItemStack oak = createItem(Material.OAK_LOG, Utils.chat("&fOak Log"), 64, null, null);
            ItemStack spruce = createItem(Material.SPRUCE_LOG, Utils.chat("&fSpruce Log"), 64, null, null);
            ItemStack dark = createItem(Material.DARK_OAK_LOG, Utils.chat("&fDark Oak Log"), 64, null, null);
            ItemStack birch = createItem(Material.BIRCH_LOG, Utils.chat("&fBirch Log"), 64, null, null);
            ItemStack acacia = createItem(Material.ACACIA_LOG, Utils.chat("&few  Acacia Log"), 64, null, null);
            ItemStack jungle = createItem(Material.JUNGLE_LOG, Utils.chat("&fJungle Log"), 64, null, null);
            Utils.addItemToInventory(oak, player);
            Utils.addItemToInventory(spruce, player);
            Utils.addItemToInventory(dark, player);
            Utils.addItemToInventory(birch, player);
            Utils.addItemToInventory(acacia, player);
            Utils.addItemToInventory(jungle, player);
            player.sendMessage(Utils.chat("&dYou have been given a lot of wood"));

        } else {
            ItemStack grass = createItem(Material.GRASS_BLOCK, Utils.chat("&fGrass Block"), 64, null, null);
            ItemStack sand = createItem(Material.SAND, Utils.chat("&fSand"), 64, null, null);
            ItemStack stone = createItem(Material.STONE, Utils.chat("&fStone"), 64, null, null);
            ItemStack mossyCobble = createItem(Material.MOSSY_COBBLESTONE, Utils.chat("&fMossy Cobblestone"), 64, null, null);
            ItemStack honey = createItem(Material.HONEY_BLOCK, Utils.chat("&fHoney Block"), 64, null, null);
            ItemStack slime = createItem(Material.SLIME_BLOCK, Utils.chat("&fSlime Block"), 64, null, null);
            Utils.addItemToInventory(grass, player);
            Utils.addItemToInventory(sand, player);
            Utils.addItemToInventory(stone, player);
            Utils.addItemToInventory(mossyCobble, player);
            Utils.addItemToInventory(honey, player);
            Utils.addItemToInventory(slime, player);
            player.sendMessage(Utils.chat("&dYou have been given some blocks"));

        }

    }
}
