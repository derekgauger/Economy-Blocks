package craftplugins.economyblocks.CarePackages;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.EconomyBlocks;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;

public class TierTwo extends CarePackage implements Listener {

    EconomyBlocks plugin;
    ItemStack carePackage;
    BankHandler bankHandler;

    public TierTwo(EconomyBlocks plugin, ItemStack carePackage, BankHandler bankHandler) {
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

        if (randDouble >= .50) {
            bad(player, bankHandler);
        } else {
            good(player, bankHandler);
            Firework fw = (Firework) block.getWorld().spawnEntity(block.getLocation().add(.5,8.5,.5), EntityType.FIREWORK);
            FireworkMeta fwm = fw.getFireworkMeta();
            fwm.setPower(2);
            fwm.addEffect(FireworkEffect.builder().withColor(Color.PURPLE).flicker(true).build());

            fw.setFireworkMeta(fwm);
            fw.detonate();
        }

        block.setType(Material.AIR);

    }
}
