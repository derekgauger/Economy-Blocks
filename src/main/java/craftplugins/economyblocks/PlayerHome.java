package craftplugins.economyblocks;

import java.io.Serializable;

public class PlayerHome implements Serializable {

    private static transient final long serialVersionUID = -1681012206529286331L;

    String uuid;
    int homeNumber;
    String worldUID;
    double x;
    double y;
    double z;

    public PlayerHome(String uuid, int homeNumber, String worldUID, double x, double y, double z) {

        this.uuid = uuid;
        this.homeNumber = homeNumber;
        this.worldUID = worldUID;
        this.x = x;
        this.y = y;
        this.z = z;

    }

    public void setWorld(String worldUID) {
        this.worldUID = worldUID;
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

}