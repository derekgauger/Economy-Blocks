package craftplugins.economyblocks.Events.GoodEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChangeBlocksToOres implements CarePackageEvent {

    int timer;
    List<BlockData> blockDatas = new ArrayList<>();
    List<Block> blocks;

    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println(player.getName() + " has opened a care package : " + this.getClass().getSimpleName());
        blocks = Utils.getBlocks(player.getLocation().getBlock(), 3);

        for (Block block : blocks) {
            blockDatas.add(block.getBlockData());
        }

        Material[] types = {Material.COAL_ORE, Material.EMERALD_ORE, Material.DIAMOND_ORE, Material.IRON_ORE, Material.IRON_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.GOLD_ORE, Material.GOLD_ORE, Material.COPPER_ORE, Material.COPPER_ORE, Material.COPPER_ORE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE};

        for (Block b : blocks) {
            if (b.getType() != Material.AIR) {
                int randomIndex = Utils.getRandomNumber(0, types.length);
                b.setType(types[randomIndex]);
            }
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
    }

}
