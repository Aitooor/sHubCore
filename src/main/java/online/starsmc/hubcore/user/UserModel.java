package online.starsmc.hubcore.user;

import online.starsmc.hubcore.model.Model;

import java.util.UUID;

public class UserModel implements Model {

    private final UUID uuid;
    private String name;
    private boolean isFlying;

    public UserModel(UUID uuid, String name, boolean isFlying) {
        this.uuid = uuid;
        this.name = name;
        this.isFlying = isFlying;
    }

    @Override
    public String getId() {
        return getUuid().toString();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFlying() {
        return isFlying;
    }

    public void setFlying(boolean flying) {
        isFlying = flying;
    }
}
