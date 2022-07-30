package craftplugins.economyblocks.CarePackages;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.EconomyBlocks;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class TierFour extends CarePackage implements Listener {

    EconomyBlocks plugin;
    ItemStack carePackage;
    BankHandler bankHandler;

    public TierFour(EconomyBlocks plugin, ItemStack carePackage, BankHandler bankHandler) {
        this.plugin = plugin;
        this.carePackage = carePackage;
        this.bankHandler = bankHandler;

        Bukkit.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        Block block = event.getBlock();

        ItemStack item = event.getItemInHand();
        Player player = event.getPlayer();

        if (!item.isSimilar(carePackage)) {
            return;
        }

        Random rand = new Random();
        double randDouble = rand.nextDouble();

        if (randDouble >= .80) {
            bad(player, bankHandler);
        } else {
            good(player, bankHandler);
        }

        block.setType(Material.AIR);

    }
}
