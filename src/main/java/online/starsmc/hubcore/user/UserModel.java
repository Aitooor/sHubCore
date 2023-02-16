package online.starsmc.hubcore.user;

import online.starsmc.hubcore.model.Model;

import java.util.Map;
import java.util.UUID;

public class UserModel implements Model {

    private final UUID uuid;
    private Map<String, Boolean> canAccessToServers;

    public UserModel(UUID uuid, Map<String, Boolean> canAccessToServers) {
        this.uuid = uuid;
        this.canAccessToServers = canAccessToServers;
    }

    @Override
    public String getId() {
        return uuid.toString();
    }

    public UUID getUuid() {
        return uuid;
    }

    public Map<String, Boolean> getCanAccess() {
        return canAccessToServers;
    }

    public void setCanAccess(Map<String, Boolean> canAccessToServers) {
        this.canAccessToServers = canAccessToServers;
    }
}
