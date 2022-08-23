package craftplugins.economyblocks;

import org.bukkit.Bukkit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Community implements Serializable {

    private static transient final long serialVersionUID = -1681012206529286332L;

    List<String> uuids = new ArrayList<>();;
    String name;
    int level;
    String ownerUUID;
    String ownerName;
    List<String> invited = new ArrayList<>();
    double x, y, z;
    String worldUUID;
    double progress;
    double goal;
    List<String> playerNames = new ArrayList<>();
    String primaryColor = "&f";
    String secondaryColor = "&f";

    List<String> admins = new ArrayList<>();
    List<String> adminNames = new ArrayList<>();

    public Community (String ownerUUID, String name, int level, double goal) {
        this.name = name;
        this.level = level;
        this.ownerUUID = ownerUUID;
        uuids.add(ownerUUID);
        this.progress = 0;
        this.goal = goal;
        this.ownerName = Bukkit.getPlayer(UUID.fromString(ownerUUID)).getDisplayName();
        playerNames.add(ownerName);
        admins.add(ownerUUID);
        adminNames.add(ownerName);

    }

    public void setPrimaryColor(String color) {
        primaryColor = color;
    }

    public void setSecondaryColor(String color) {
        secondaryColor = color;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public double getGoal() {
        return goal;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getProgress() {
        return progress;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public void setX(double x) {
        this.x = x;

    }

    public void setY(double y) {
        this.y = y;

    }

    public void setZ(double z) {
        this.z = z;

    }

    public void setWorld(String worldUUID) {
        this.worldUUID = worldUUID;
    }

    public void invitePlayer(String uuid) {
        invited.add(uuid);
    }

    public String getOwner() {
        return ownerUUID;

    }

    public int getLevel() {
        return level;

    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPlayer(String uuid) {
        uuids.add(uuid);
        playerNames.add(Bukkit.getPlayer(UUID.fromString(uuid)).getDisplayName());

    }

    public void addAdmin(String uuid) {
        admins.add(uuid);
        adminNames.add(Bukkit.getPlayer(UUID.fromString(uuid)).getDisplayName());

    }

    public void removeAdmin(String uuid) {
        adminNames.remove(Bukkit.getPlayer(UUID.fromString(uuid)).getDisplayName());
        admins.remove(uuid);
    }

    public void removePlayer(String uuid) {
        playerNames.remove(Bukkit.getPlayer(UUID.fromString(uuid)).getDisplayName());
        uuids.remove(uuid);

    }

    public boolean contains(String uuid) {
        return uuids.contains(uuid);

    }

    public int getSize() {
        return uuids.size();

    }

    @Override
    public String toString() {
        return getName() + " - Owner: " + getOwnerName() + ", Size: " + getSize() + ", Level: " + getLevel();
    }

}
