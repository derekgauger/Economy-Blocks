package craftplugins.economyblocks.Events;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Utils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.List;

public class Party implements CarePackageEvent{
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {
        List<Block> blocks = Utils.getBlocks(player.getLocation().getBlock(), 5);


        EntityType[] entities = {EntityType.PIG, EntityType.COW, EntityType.MUSHROOM_COW, EntityType.BEE, EntityType.BOAT, EntityType.CAT, EntityType.GLOW_SQUID, EntityType.WOLF, EntityType.TURTLE, EntityType.PANDA, EntityType.GOAT};
        Material[] wool = {Material.PINK_WOOL, Material.RED_WOOL, Material.LIGHT_BLUE_WOOL, Material.LIME_WOOL, Material.YELLOW_WOOL, Material.ORANGE_WOOL, Material.PURPLE_WOOL, Material.CYAN_WOOL, Material.MAGENTA_WOOL};
        for (Block b : blocks) {
            if (b.getType() != Material.AIR) {
                Material m = wool[Utils.getRandomNumber(0, wool.length)];
                b.setType(m);
            }
        }

        EntityType entity = entities[Utils.getRandomNumber(0, wool.length)];

        for (int i = 0; i < 20; i++) {

            Entity piggy = player.getWorld().spawnEntity(player.getLocation().add(1, 3,1), entity);
            piggy.setCustomNameVisible(true);
            piggy.setCustomName(Utils.chat("&d&lSexy " + entity.getName() + " #" + (i + 1)));
        }
    }
}
