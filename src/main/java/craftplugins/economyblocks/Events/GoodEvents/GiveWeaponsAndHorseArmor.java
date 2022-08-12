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

public class GiveWeaponsAndHorseArmor implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println(player.getName() + " has opened a care package : " + this.getClass().getSimpleName());

        ItemStack TRIDENT = createItem(Material.TRIDENT, "", 1, null, null);

        ItemStack eNETHERITE_AXE = createItem(Material.NETHERITE_AXE, "", 1, new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.DURABILITY}, new int[]{5, 3});

        ItemStack eNETHERITE_SWORD = createItem(Material.NETHERITE_SWORD, "", 1, new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.DURABILITY, Enchantment.LOOT_BONUS_MOBS}, new int[]{5, 3, 3});

        ItemStack eDIAMOND_AXE = createItem(Material.DIAMOND_AXE, "", 1, new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.DURABILITY}, new int[]{5, 3});

        ItemStack eDIAMOND_SWORD = createItem(Material.DIAMOND_SWORD, "", 1, new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.DURABILITY, Enchantment.LOOT_BONUS_MOBS}, new int[]{5, 3, 3});

        ItemStack NETHERITE_AXE = createItem(Material.NETHERITE_AXE, "", 1, null, null);

        ItemStack NETHERITE_SWORD = createItem(Material.NETHERITE_SWORD, "", 1, null, null);

        ItemStack DIAMOND_AXE = createItem(Material.DIAMOND_AXE, "", 1, null, null);

        ItemStack DIAMOND_SWORD = createItem(Material.DIAMOND_SWORD, "", 1, null, null);

        ItemStack DIAMOND_HORSE_ARMOR = createItem(Material.DIAMOND_HORSE_ARMOR, "", 1, null, null);

        ItemStack IRON_HORSE_ARMOR = createItem(Material.IRON_HORSE_ARMOR, "", 1, null, null);

        ItemStack fGOLDEN_SWORD = createItem(Material.GOLDEN_SWORD, "", 1, null, null);
        ItemStack fGOLDEN_AXE = createItem(Material.GOLDEN_AXE, "", 1, null, null);

        ItemStack fIRON_SWORD = createItem(Material.IRON_SWORD, "", 1, null, null);
        ItemStack fIRON_AXE = createItem(Material.IRON_AXE, "", 1, null, null);

        ItemStack WOODEN_SWORD = createItem(Material.WOODEN_SWORD, "", 1, new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.LOOT_BONUS_MOBS, Enchantment.KNOCKBACK, Enchantment.FIRE_ASPECT, Enchantment.DURABILITY}, new int[]{5, 3, 2, 2, 3});

        ItemStack eBOW = createItem(Material.BOW, "", 1, new Enchantment[]{Enchantment.ARROW_DAMAGE, Enchantment.ARROW_FIRE, Enchantment.ARROW_KNOCKBACK, Enchantment.ARROW_INFINITE, Enchantment.DURABILITY}, new int[]{3, 2, 2, 1, 3});

        ItemStack BOW = createItem(Material.BOW, "", 1, null, null);

        ItemStack ARROW = createItem(Material.ARROW, "", 64, null, null);

        GiveItemInfo[] potentialItems = {
                new GiveItemInfo(TRIDENT, 1, "a trident"),
                new GiveItemInfo(eNETHERITE_AXE, 1, "an enchanted netherite axe"),
                new GiveItemInfo(eNETHERITE_SWORD, 1, "an enchanted netherite sword"),
                new GiveItemInfo(eBOW, 2, "an enchanted bow"),
                new GiveItemInfo(eDIAMOND_AXE, 2, "an enchanted diamond axe"),
                new GiveItemInfo(eDIAMOND_SWORD, 2, "an enchanted diamond sword"),
                new GiveItemInfo(NETHERITE_AXE, 2, "a netherite axe"),
                new GiveItemInfo(NETHERITE_SWORD, 3, "a netherite sword"),
                new GiveItemInfo(DIAMOND_AXE, 3, "a diamond axe"),
                new GiveItemInfo(DIAMOND_SWORD, 3, "a diamond sword"),
                new GiveItemInfo(DIAMOND_HORSE_ARMOR, 5, "some diamond horse armor"),
                new GiveItemInfo(IRON_HORSE_ARMOR, 8, "some iron horse armor"),
                new GiveItemInfo(new ItemStack[] {fIRON_SWORD, fIRON_AXE}, 8, "a full set of iron weapons"),
                new GiveItemInfo(new ItemStack[] {fGOLDEN_SWORD, fGOLDEN_AXE}, 10, "full set of golden weapons"),
                new GiveItemInfo(BOW, 14, "a bow"),
                new GiveItemInfo(WOODEN_SWORD, 15, "a wooden sword"),
                new GiveItemInfo(ARROW, 20, "some arrows"),

        };

        Utils.picker(potentialItems, player);

    }
}
