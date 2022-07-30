package craftplugins.economyblocks.Events.BadEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

public class ChangeBlocksSuffocation implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {
        System.out.println("Suffocation");

        List<Block> blocks = Utils.getBlocks(player.getLocation().getBlock(), 5);

        Material[] types = {Material.GLASS, Material.STONE, Material.NETHERRACK, Material.BLACK_CONCRETE, Material.SAND, Material.RED_SAND, Material.SANDSTONE, Material.RED_SANDSTONE, Material.END_STONE, Material.OAK_LEAVES, Material.OBSIDIAN, Material.ANDESITE};
        Material m = types[Utils.getRandomNumber(0, types.length)];

        for (Block b : blocks) {
            b.setType(m);
        }

    }
}
