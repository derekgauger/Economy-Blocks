package craftplugins.economyblocks.CarePackages;

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

public class TierThree extends CarePackage implements Listener {

    EconomyBlocks plugin;
    ItemStack carePackage;

    public TierThree(EconomyBlocks plugin, ItemStack carePackage) {
        this.plugin = plugin;
        this.carePackage = carePackage;

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

        if (randDouble >= .67) {
            bad(player);
        } else {
            good(player);
        }

        block.setType(Material.AIR);

    }
}
