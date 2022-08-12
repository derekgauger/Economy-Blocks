package craftplugins.economyblocks;

import org.bukkit.inventory.ItemStack;

public class GiveItemInfo {

    private String message;
    private int chance;
    private ItemStack[] items;

    public GiveItemInfo(ItemStack item, int chance, String message) {
        this.chance = chance;
        this.message = message;
        this.items = new ItemStack[] {item};

    }

    public GiveItemInfo(ItemStack[] items, int chance, String message) {
        this.items = items;
        this.chance = chance;
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public int getChance() {
        return this.chance;
    }

    public ItemStack[] getItems() {
        return this.items;
    }
}