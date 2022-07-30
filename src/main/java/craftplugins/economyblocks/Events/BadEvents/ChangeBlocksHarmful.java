package craftplugins.economyblocks.Events.BadEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

public class ChangeBlocksHarmful implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {
        System.out.println("Harmful Blocks");
        List<Block> blocks = Utils.getBlocks(player.getLocation().getBlock(), 5);

        Material[] types = {Material.LAVA, Material.TNT, Material.FIRE};
        int randomIndex = Utils.getRandomNumber(0, types.length);

        for (Block b : blocks) {
            if (b.getType() != Material.AIR) {
                b.setType(types[randomIndex]);
            }
        }
    }
}
