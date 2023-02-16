package online.starsmc.hubcore.user.codec;

import online.starsmc.hubcore.model.YamlCodec;
import online.starsmc.hubcore.user.UserModel;

import java.util.*;

public class UserYamlCodec implements YamlCodec<UserModel> {

    @Override
    public Map<String, Object> serialize(UserModel model) {
        Map<String, Object> serializedModel = new HashMap<>();

        serializedModel.put("id", model.getId());
        serializedModel.put("canAccessToServers", model.getCanAccess());

        return serializedModel;
    }

    @Override
    public UserModel deserialize(Map<String, Object> modelData) {
        UUID uuid = UUID.fromString((String) modelData.get("id"));

        Map<String, Boolean> userAccessToServers = new HashMap<>();
        Map<String, Object> serializedUserAccessToServers = (Map<String, Object>) modelData.get("canAccessToServers");
        for (Map.Entry<String, Object> entry : serializedUserAccessToServers.entrySet()) {
            String serverId = entry.getKey();
            boolean canAccess = (boolean) entry.getValue();
            userAccessToServers.put(serverId, canAccess);
        }

        return new UserModel(uuid, userAccessToServers);
    }
}
