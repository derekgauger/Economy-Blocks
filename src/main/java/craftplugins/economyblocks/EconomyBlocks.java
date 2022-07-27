package craftplugins.economyblocks;

import org.bukkit.plugin.java.JavaPlugin;

public final class EconomyBlocks extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new BankHandler(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
