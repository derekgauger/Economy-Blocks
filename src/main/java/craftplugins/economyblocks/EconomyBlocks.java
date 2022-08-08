package craftplugins.economyblocks;

import org.bukkit.plugin.java.JavaPlugin;

public final class EconomyBlocks extends JavaPlugin {

    BankHandler bankHandler;
    Homes home;

    @Override
    public void onEnable() {
        // Plugin startup logic
        bankHandler = new BankHandler(this);
        home = new Homes(this, bankHandler);

        new CommandsInfo(this);
        new Utils(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        bankHandler.saveData("bank.data");
        home.saveData("homes.data");
    }
}
