package craftplugins.economyblocks;

import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.Serializable;

public final class EconomyBlocks extends JavaPlugin {

    BankHandler bankHandler;
    Home home;

    @Override
    public void onEnable() {
        // Plugin startup logic
        bankHandler = new BankHandler(this);
        home = new Home(this);

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
