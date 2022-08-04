package craftplugins.economyblocks.Events.BadEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.List;

public class SpawnEnemies implements CarePackageEvent {

    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println("Spawn Enemies");

        List<Block> blocks = Utils.getBlocks(player.getLocation().getBlock(), 5);


        EntityType[] entities = {EntityType.CREEPER, EntityType.SKELETON, EntityType.CAVE_SPIDER, EntityType.BLAZE, EntityType.WITHER_SKELETON, EntityType.ZOMBIE, EntityType.ZOGLIN, EntityType.PIGLIN_BRUTE, EntityType.PHANTOM, EntityType.MAGMA_CUBE};
        Material[] floors = {Material.OBSIDIAN, Material.END_STONE, Material.NETHERRACK, Material.STONE, Material.MOSSY_COBBLESTONE, Material.COBBLESTONE, Material.DEEPSLATE};

        for (Block b : blocks) {
            if (b.getType() != Material.AIR) {
                Material m = floors[Utils.getRandomNumber(0, floors.length)];
                b.setType(m);
            }
        }

        EntityType entity = entities[Utils.getRandomNumber(0, entities.length)];

        for (int i = 0; i < 10; i++) {

            Entity spawn = player.getWorld().spawnEntity(player.getLocation().add(1, 3,1), entity);
            piggy.setCustomNameVisible(true);
            piggy.setCustomName(Utils.chat("&d&lPapa " + spawn.getName() + " #" + (i + 1)));
        }
    }
}
