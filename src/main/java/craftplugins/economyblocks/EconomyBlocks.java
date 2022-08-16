package craftplugins.economyblocks;

import org.bukkit.plugin.java.JavaPlugin;

public final class EconomyBlocks extends JavaPlugin {

    BankHandler bankHandler;
    Homes home;
    CommunityHandler communityHandler;

    @Override
    public void onEnable() {
        // Plugin startup logic
        bankHandler = new BankHandler(this);
        home = new Homes(this, bankHandler);
        communityHandler = new CommunityHandler(this, bankHandler);

        new Shops(this, bankHandler, communityHandler);
        new Teleport(this, bankHandler);
        new Utils(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        bankHandler.saveData("bank.data");
        home.saveData("homes.data");
        communityHandler.saveData("communities.data");
    }
}
