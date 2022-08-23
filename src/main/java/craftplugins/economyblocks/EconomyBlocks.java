package craftplugins.economyblocks;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class EconomyBlocks extends JavaPlugin {

    BankHandler bankHandler;
    Homes home;
    CommunityHandler communityHandler;
    Nicknames nicknames;

    @Override
    public void onEnable() {
        // Plugin startup logic
        bankHandler = new BankHandler(this);
        home = new Homes(this, bankHandler);
        communityHandler = new CommunityHandler(this, bankHandler);
        nicknames = new Nicknames(this);

        new Shops(this, bankHandler, communityHandler);
        new Teleport(this, bankHandler);
        new Utils(this, communityHandler, nicknames);
        new CommandsInfo(this);
        new ColorsCommand(this);

        new BukkitRunnable() {
            @Override
            public void run() {
                save();
            }
        }.runTaskTimer(this, 20 * 60, 20 * 60);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        save();
    }

    public void save() {
        bankHandler.saveData("bank.data");
        home.saveData("homes.data");
        communityHandler.saveData("communities.data");
        nicknames.saveData("nicknames.data");
        System.out.println("Saved information to the bank.data, homes.data, nicknames.data and communities.data files");
    }
}
