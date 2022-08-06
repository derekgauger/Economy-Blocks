package craftplugins.economyblocks.Events.GoodEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Party implements CarePackageEvent {

    int timer;
    List<BlockData> blockDatas = new ArrayList<>();
    List<Block> blocks;
    List<LivingEntity> spawns = new ArrayList<>();

    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println("Party");
        blocks = Utils.getBlocks(player.getLocation().getBlock(), 5);


        for (Block block : blocks) {
            blockDatas.add(block.getBlockData());
        }

        EntityType[] entities = {EntityType.PIG, EntityType.COW, EntityType.MUSHROOM_COW, EntityType.BEE, EntityType.CAT, EntityType.GLOW_SQUID, EntityType.WOLF, EntityType.TURTLE, EntityType.PANDA, EntityType.GOAT};
        Material[] wool = {Material.PINK_WOOL, Material.RED_WOOL, Material.LIGHT_BLUE_WOOL, Material.LIME_WOOL, Material.YELLOW_WOOL, Material.ORANGE_WOOL, Material.PURPLE_WOOL, Material.CYAN_WOOL, Material.MAGENTA_WOOL};
        for (Block b : blocks) {
            if (b.getType() != Material.AIR) {
                Material m = wool[Utils.getRandomNumber(0, wool.length)];
                b.setType(m);
            }
        }

        EntityType entity = entities[Utils.getRandomNumber(0, wool.length)];

        for (int i = 0; i < 10; i++) {

            Entity spawn = player.getWorld().spawnEntity(player.getLocation().add(1, 3,1), entity);
            spawn.setCustomNameVisible(true);
            spawn.setCustomName(Utils.chat("&d&lSexy " + entity.getName() + " #" + (i + 1)));
            spawns.add((LivingEntity) spawn);

        }



        timer = Bukkit.getScheduler().scheduleSyncRepeatingTask(player.getServer().getPluginManager().getPlugin("EconomyBlocks"), new Runnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ > 60) {
                    cancel();
                }
            }
        }, 0, 20 * 1);

    }

    private void cancel() {
        Bukkit.getScheduler().cancelTask(timer);
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).setBlockData(blockDatas.get(i));
        }

        for (LivingEntity e : spawns) {
            e.setHealth(0);
        }

    }

}
