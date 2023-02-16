package online.starsmc.hubcore.user;

import online.starsmc.hubcore.model.Model;

import java.util.Map;
import java.util.UUID;

public class UserModel implements Model {

    private final UUID uuid;
    private Map<String, Boolean> canAccess;

    public UserModel(UUID uuid, Map<String, Boolean> canAccess) {
        this.uuid = uuid;
        this.canAccess = canAccess;
    }

    @Override
    public String getId() {
        return uuid.toString();
    }

    public UUID getUuid() {
        return uuid;
    }

    public Map<String, Boolean> getCanAccess() {
        return canAccess;
    }

    public void setCanAccess(Map<String, Boolean> canAccess) {
        this.canAccess = canAccess;
    }
}
