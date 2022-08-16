package craftplugins.economyblocks.Events.GoodEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.GiveItemInfo;
import craftplugins.economyblocks.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

import static craftplugins.economyblocks.Utils.createItem;

public class GiveTools implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println(player.getName() + " has opened a care package : " + this.getClass().getSimpleName());

        ItemStack eNETHERITE_PICKAXE = createItem(Material.NETHERITE_PICKAXE, "", 1, new Enchantment[]{Enchantment.DURABILITY, Enchantment.DIG_SPEED, Enchantment.LOOT_BONUS_BLOCKS}, new int[]{3, 5, 3});

        ItemStack eDIAMOND_PICKAXE = createItem(Material.DIAMOND_PICKAXE, "", 1, new Enchantment[]{Enchantment.DURABILITY, Enchantment.DIG_SPEED, Enchantment.LOOT_BONUS_BLOCKS}, new int[]{3, 5, 3});

        ItemStack sDIAMOND_PICKAXE = createItem(Material.DIAMOND_PICKAXE, "", 1, new Enchantment[]{Enchantment.SILK_TOUCH}, new int[]{1});

        ItemStack NETHERITE_PICKAXE = createItem(Material.NETHERITE_PICKAXE, "", 1, null, null);

        ItemStack DIAMOND_PICKAXE = createItem(Material.DIAMOND_PICKAXE, "", 1, null, null);

        ItemStack NETHERITE_SHOVEL = createItem(Material.NETHERITE_SHOVEL, "", 1, null, null);

        ItemStack DIAMOND_SHOVEL = createItem(Material.DIAMOND_SHOVEL, "", 1, null, null);

        ItemStack eIRON_PICKAXE = createItem(Material.IRON_PICKAXE, "", 1, new Enchantment[]{Enchantment.LOOT_BONUS_BLOCKS}, new int[]{3});

        ItemStack NETHERITE_HOE = createItem(Material.NETHERITE_HOE, "", 1, new Enchantment[]{Enchantment.DURABILITY}, new int[]{3});

        ItemStack DIAMOND_HOE = createItem(Material.DIAMOND_HOE, "", 1, new Enchantment[]{Enchantment.DURABILITY}, new int[]{3});


        ItemStack WOODEN_PICKAXE = createItem(Material.WOODEN_PICKAXE, "", 1, new Enchantment[]{Enchantment.DURABILITY, Enchantment.DIG_SPEED, Enchantment.LOOT_BONUS_BLOCKS}, new int[]{3, 5, 3});
        ItemStack WOODEN_SHOVEL = createItem(Material.WOODEN_SHOVEL, "", 1, new Enchantment[]{Enchantment.DURABILITY, Enchantment.DIG_SPEED, Enchantment.LOOT_BONUS_BLOCKS}, new int[]{3, 5, 3});

        ItemStack fIRON_PICKAXE = createItem(Material.IRON_PICKAXE, "", 1, null, null);
        ItemStack fIRON_AXE = createItem(Material.IRON_AXE, "", 1, null, null);
        ItemStack fIRON_SHOVEL = createItem(Material.IRON_SHOVEL, "", 1, null, null);
        ItemStack fIRON_HOE = createItem(Material.IRON_HOE, "", 1, null, null);

        GiveItemInfo[] potentialItems = {
                new GiveItemInfo(eNETHERITE_PICKAXE, 1, "an enchanted netherite pickaxe"),
                new GiveItemInfo(eDIAMOND_PICKAXE, 1, "an enchanted diamond pickaxe"),
                new GiveItemInfo(sDIAMOND_PICKAXE, 2, "a silk touch diamond pickaxe"),
                new GiveItemInfo(new ItemStack[] {DIAMOND_PICKAXE, DIAMOND_SHOVEL, DIAMOND_HOE}, 2, "a full set of diamond tools"),
                new GiveItemInfo(NETHERITE_PICKAXE, 4, "a netherite pickaxe"),
                new GiveItemInfo(NETHERITE_SHOVEL, 4, "a netherite shovel"),
                new GiveItemInfo(DIAMOND_PICKAXE, 6, "a diamond pickaxe"),
                new GiveItemInfo(DIAMOND_SHOVEL, 6, "a diamond shovel"),
                new GiveItemInfo(eIRON_PICKAXE, 8, "an enchanted iron pickaxe"),
                new GiveItemInfo(NETHERITE_HOE, 10, "a netherite hoe"),
                new GiveItemInfo(DIAMOND_HOE, 12, "a diamond hoe"),
                new GiveItemInfo(WOODEN_PICKAXE, 13, "a wooden pickaxe"),
                new GiveItemInfo(WOODEN_SHOVEL, 13, "a wooden shovel"),
                new GiveItemInfo(new ItemStack[] {fIRON_PICKAXE, fIRON_AXE, fIRON_SHOVEL, fIRON_HOE}, 18, "a full set of iron tools"),

        };

        Utils.picker(potentialItems, player);

    }
}
