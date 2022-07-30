package craftplugins.economyblocks;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.Serializable;

public final class EconomyBlocks extends JavaPlugin {

    BankHandler bankHandler;

    @Override
    public void onEnable() {
        // Plugin startup logic
        bankHandler = new BankHandler(this);
        new Utils(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        bankHandler.saveData("bank.data");
    }
}
