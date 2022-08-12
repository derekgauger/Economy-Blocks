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

public class GiveItems implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println(player.getName() + " has opened a care package : " + this.getClass().getSimpleName());

        ItemStack smiteStick = Utils.createItem(Material.STICK, "", 1,null, null);
        ItemStack totem = Utils.createItem(Material.TOTEM_OF_UNDYING, "", 1,null, null);
        ItemStack kbStick = Utils.createItem(Material.STICK, "", 1, new Enchantment[]{Enchantment.KNOCKBACK}, new int[]{10});
        ItemStack goldenApples = Utils.createItem(Material.GOLDEN_APPLE, "", 10,null, null);
        ItemStack enchantedGoldenApples = Utils.createItem(Material.ENCHANTED_GOLDEN_APPLE, "", 5,null, null);
        ItemStack netherite = Utils.createItem(Material.NETHERITE_INGOT, "", 1, null, null);
        ItemStack diamond = Utils.createItem(Material.DIAMOND, "", 9, null, null);
        ItemStack gold = Utils.createItem(Material.GOLD_INGOT, "", 16, null, null);
        ItemStack iron = Utils.createItem(Material.IRON_INGOT, "", 16, null, null);
        ItemStack lapis = Utils.createItem(Material.LAPIS_ORE, "", 16, null, null);
        ItemStack cookedBeef = Utils.createItem(Material.COOKED_BEEF, "", 32, null, null);
        ItemStack cake = Utils.createItem(Material.CAKE, "", 1, null, null);
        ItemStack beef = Utils.createItem(Material.BEEF, "", 32, null, null);

        GiveItemInfo[] potentialItems = {
                new GiveItemInfo(smiteStick, 1, "a smite stick"),
                new GiveItemInfo(totem, 1, "a totem of undying"),
                new GiveItemInfo(kbStick, 2, "a knockback stick"),
                new GiveItemInfo(enchantedGoldenApples, 3, "some enchanted golden apples"),
                new GiveItemInfo(netherite, 3, "a netherite ingot"),
                new GiveItemInfo(diamond, 6, "some diamonds"),
                new GiveItemInfo(goldenApples, 6, "some golden apples"),
                new GiveItemInfo(gold, 10, "some gold ingots"),
                new GiveItemInfo(iron, 10, "some iron ingots"),
                new GiveItemInfo(lapis, 10, "some lapis ore"),
                new GiveItemInfo(cookedBeef, 13, "some cookedBeef"),
                new GiveItemInfo(beef, 15, "some beef"),
                new GiveItemInfo(cake, 20, "a cake"),

        };
        Utils.picker(potentialItems, player);

    }
}
