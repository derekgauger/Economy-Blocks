package craftplugins.economyblocks.Events.GoodEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.GiveItemInfo;
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

        System.out.println(player.getName() + " has opened a care package : " + this.getClass().getSimpleName());

        ItemStack beacon = createItem(Material.BEACON, "", 1, null, null);

        ItemStack netheriteBlock = createItem(Material.NETHERITE_BLOCK, "", 1, null, null);

        ItemStack diamondBlock = createItem(Material.DIAMOND_BLOCK, "", 2, null, null);

        ItemStack shulker = createItem(Material.SHULKER_BOX, "", 1, null, null);

        ItemStack chests = createItem(Material.CHEST, "", 128, null, null);

        ItemStack enderchest = createItem(Material.ENDER_CHEST, "", 1, null, null);

        ItemStack gold = createItem(Material.GOLD_BLOCK, "", 8, null, null);

        ItemStack iron = createItem(Material.IRON_BLOCK, "", 8, null, null);

        ItemStack obsidian = createItem(Material.OBSIDIAN, "", 32, null, null);

        ItemStack endstone = createItem(Material.END_STONE, "", 64, null, null);

        ItemStack oak = createItem(Material.OAK_LOG, "", 64, null, null);
        ItemStack spruce = createItem(Material.SPRUCE_LOG, "", 64, null, null);
        ItemStack dark = createItem(Material.DARK_OAK_LOG, "", 64, null, null);
        ItemStack birch = createItem(Material.BIRCH_LOG, "", 64, null, null);
        ItemStack acacia = createItem(Material.ACACIA_LOG, "", 64, null, null);
        ItemStack jungle = createItem(Material.JUNGLE_LOG, "", 64, null, null);

        ItemStack grass = createItem(Material.GRASS_BLOCK, "", 64, null, null);
        ItemStack sand = createItem(Material.SAND, "", 64, null, null);
        ItemStack stone = createItem(Material.STONE, "", 64, null, null);
        ItemStack mossyCobble = createItem(Material.MOSSY_COBBLESTONE, "", 64, null, null);
        ItemStack honey = createItem(Material.HONEY_BLOCK, "", 64, null, null);
        ItemStack slime = createItem(Material.SLIME_BLOCK, "", 64, null, null);

        ItemStack sandstone = createItem(Material.SANDSTONE, "", 64, null, null);
        ItemStack redSand = createItem(Material.RED_SAND, "", 64, null, null);
        ItemStack redSandstone = createItem(Material.RED_SANDSTONE, "", 64, null, null);
        ItemStack soulSand = createItem(Material.SOUL_SAND, "", 64, null, null);

        GiveItemInfo[] potentialItems = {
                new GiveItemInfo(beacon, 1, "a beacon"),
                new GiveItemInfo(netheriteBlock, 1, "a netherite block"),
                new GiveItemInfo(diamondBlock, 1, "some diamond blocks"),
                new GiveItemInfo(shulker, 2, "a shulker box"),
                new GiveItemInfo(chests, 3, "a lot of chests"),
                new GiveItemInfo(enderchest, 4, "an enderchest"),
                new GiveItemInfo(gold, 5, "some gold blocks"),
                new GiveItemInfo(iron, 8, "some iron blocks"),
                new GiveItemInfo(obsidian, 10, "some obsidian"),
                new GiveItemInfo(endstone, 15, "some endstone"),
                new GiveItemInfo(new ItemStack[] {oak, spruce, dark, birch, acacia, jungle}, 15, "a lot of wood"),
                new GiveItemInfo(new ItemStack[] {sandstone, redSand, redSandstone, soulSand}, 15, "some sand"),
                new GiveItemInfo(new ItemStack[] {grass, sand, stone, mossyCobble, honey, slime}, 20, "some blocks"),
        };

        Utils.picker(potentialItems, player);
    }
}
