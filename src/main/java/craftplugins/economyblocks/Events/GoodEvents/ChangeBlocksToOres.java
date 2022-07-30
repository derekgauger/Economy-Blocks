package craftplugins.economyblocks.Events.GoodEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

public class ChangeBlocksToOres implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println("Change Blocks to Ores");
        List<Block> blocks = Utils.getBlocks(player.getLocation().getBlock(), 5);

        Material[] types = {Material.EMERALD_ORE, Material.DIAMOND_ORE, Material.IRON_ORE, Material.IRON_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.GOLD_ORE, Material.GOLD_ORE, Material.COPPER_ORE, Material.COPPER_ORE, Material.COPPER_ORE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE};

        for (Block b : blocks) {
            if (b.getType() != Material.AIR) {
                int randomIndex = Utils.getRandomNumber(0, types.length);
                b.setType(types[randomIndex]);
            }
        }
    }
}
