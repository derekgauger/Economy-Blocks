package craftplugins.economyblocks.Events.GoodEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.GiveItemInfo;
import craftplugins.economyblocks.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


import static craftplugins.economyblocks.Utils.createItem;

public class GiveArmor implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println(player.getName() + " has opened a care package : " + this.getClass().getSimpleName());

        ItemStack eNETHERITE_CHESTPLATE = createItem(Material.NETHERITE_CHESTPLATE, "", 1, new Enchantment[]{Enchantment.DURABILITY, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FALL}, new int[]{3, 4, 3});

        ItemStack fNETHERITE_HELMET = createItem(Material.NETHERITE_HELMET, "", 1, null, null);
        ItemStack fNETHERITE_CHESTPLATE = createItem(Material.NETHERITE_CHESTPLATE, "", 1, null, null);
        ItemStack fNETHERITE_LEGGINGS = createItem(Material.NETHERITE_LEGGINGS, "", 1, null, null);
        ItemStack fNETHERITE_BOOTS = createItem(Material.NETHERITE_BOOTS, "", 1, null, null);

        ItemStack eDIAMOND_CHESTPLATE = createItem(Material.DIAMOND_CHESTPLATE, "", 1, new Enchantment[]{Enchantment.DURABILITY, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FALL}, new int[]{3, 4, 3});

        ItemStack fDIAMOND_HELMET = createItem(Material.DIAMOND_HELMET, "", 1, null, null);
        ItemStack fDIAMOND_CHESTPLATE = createItem(Material.DIAMOND_CHESTPLATE, "", 1, null, null);
        ItemStack fDIAMOND_LEGGINGS = createItem(Material.DIAMOND_LEGGINGS, "", 1, null, null);
        ItemStack fDIAMOND_BOOTS = createItem(Material.DIAMOND_BOOTS, "", 1, null, null);

        ItemStack NETHERITE_HELMET = createItem(Material.NETHERITE_HELMET, "", 1, null, null);

        ItemStack NETHERITE_CHESTPLATE = createItem(Material.NETHERITE_CHESTPLATE, "", 1, null, null);

        ItemStack NETHERITE_LEGGINGS = createItem(Material.NETHERITE_LEGGINGS, "", 1, null, null);

        ItemStack NETHERITE_BOOTS = createItem(Material.NETHERITE_BOOTS, "", 1, null, null);

        ItemStack DIAMOND_HELMET = createItem(Material.DIAMOND_HELMET, "", 1, null, null);

        ItemStack DIAMOND_CHESTPLATE = createItem(Material.DIAMOND_CHESTPLATE, "", 1, null, null);

        ItemStack DIAMOND_LEGGINGS = createItem(Material.DIAMOND_LEGGINGS, "", 1, null, null);

        ItemStack DIAMOND_BOOTS = createItem(Material.DIAMOND_BOOTS, "", 1, null, null);

        ItemStack eIRON_CHESTPLATE = createItem(Material.IRON_CHESTPLATE, "", 1, new Enchantment[]{Enchantment.DURABILITY, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FALL, Enchantment.THORNS}, new int[]{3, 4, 4, 3});

        ItemStack fCHAINMAIL_HELMET = createItem(Material.CHAINMAIL_HELMET, "", 1, null, null);
        ItemStack fCHAINMAIL_CHESTPLATE = createItem(Material.CHAINMAIL_CHESTPLATE, "", 1, null, null);
        ItemStack fCHAINMAIL_LEGGINGS = createItem(Material.CHAINMAIL_LEGGINGS, "", 1, null, null);
        ItemStack fCHAINMAIL_BOOTS = createItem(Material.CHAINMAIL_BOOTS, "", 1, null, null);

        ItemStack fIRON_HELMET = createItem(Material.IRON_HELMET, "", 1, null, null);
        ItemStack fIRON_CHESTPLATE = createItem(Material.IRON_CHESTPLATE, "", 1, null, null);
        ItemStack fIRON_LEGGINGS = createItem(Material.IRON_LEGGINGS, "", 1, null, null);
        ItemStack fIRON_BOOTS = createItem(Material.IRON_BOOTS, "", 1, null, null);

        ItemStack fGOLDEN_HELMET = createItem(Material.GOLDEN_HELMET, "", 1, null, null);
        ItemStack fGOLDEN_CHESTPLATE = createItem(Material.GOLDEN_CHESTPLATE, "", 1, null, null);
        ItemStack fGOLDEN_LEGGINGS = createItem(Material.GOLDEN_LEGGINGS, "", 1, null, null);
        ItemStack fGOLDEN_BOOTS = createItem(Material.GOLDEN_BOOTS, "", 1, null, null);

        GiveItemInfo[] potentialItems = {
                new GiveItemInfo(eNETHERITE_CHESTPLATE, 1, "an enchanted netherite chestplate"),
                new GiveItemInfo(new ItemStack[]{fNETHERITE_HELMET, fNETHERITE_CHESTPLATE, fNETHERITE_LEGGINGS, fNETHERITE_BOOTS}, 1, "a full set of netherite armor"),
                new GiveItemInfo(eDIAMOND_CHESTPLATE, 1, "an enchanted netherite chestplate"),
                new GiveItemInfo(new ItemStack[]{fDIAMOND_HELMET, fDIAMOND_CHESTPLATE, fDIAMOND_LEGGINGS, fDIAMOND_BOOTS}, 1, "a full set of diamond armor"),
                new GiveItemInfo(NETHERITE_HELMET, 3, "a netherite helmet"),
                new GiveItemInfo(NETHERITE_CHESTPLATE, 3, "a netherite chestplate"),
                new GiveItemInfo(NETHERITE_LEGGINGS, 3, "netherite leggings"),
                new GiveItemInfo(NETHERITE_BOOTS, 3, "netherite boots"),
                new GiveItemInfo(DIAMOND_HELMET, 6, "a diamond helmet"),
                new GiveItemInfo(DIAMOND_CHESTPLATE, 6, "a diamond chestplate"),
                new GiveItemInfo(DIAMOND_LEGGINGS, 6, "diamond leggings"),
                new GiveItemInfo(DIAMOND_BOOTS, 6, "diamond boots"),
                new GiveItemInfo(eIRON_CHESTPLATE, 8, "an enchanted iron chestplate"),
                new GiveItemInfo(new ItemStack[]{fCHAINMAIL_HELMET, fCHAINMAIL_CHESTPLATE, fCHAINMAIL_LEGGINGS, fCHAINMAIL_BOOTS}, 12, "a full set of chainmail armor"),
                new GiveItemInfo(new ItemStack[]{fIRON_HELMET, fIRON_CHESTPLATE, fIRON_LEGGINGS, fIRON_BOOTS}, 15, "a full set of iron armor"),
                new GiveItemInfo(new ItemStack[]{fGOLDEN_HELMET, fGOLDEN_CHESTPLATE, fGOLDEN_LEGGINGS, fGOLDEN_BOOTS}, 25, "a full set of golden armor"),

        };

        Utils.picker(potentialItems, player);

    }
}
