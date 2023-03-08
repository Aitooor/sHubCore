package online.starsmc.hubcore.user.codec;

import online.starsmc.hubcore.model.YamlCodec;
import online.starsmc.hubcore.user.UserModel;

import java.util.*;

public class UserYamlCodec implements YamlCodec<UserModel> {

    @Override
    public Map<String, Object> serialize(UserModel model) {
        Map<String, Object> serializedModel = new HashMap<>();

        serializedModel.put("id", model.getId());
        serializedModel.put("name", model.getName());
        serializedModel.put("isFlying", model.isFlying());

        return serializedModel;
    }

    @Override
    public UserModel deserialize(Map<String, Object> modelData) {
        UUID uuid = UUID.fromString((String) modelData.get("id"));
        String name = (String) modelData.get("name");
        boolean isFlying = (boolean) modelData.get("isFlying");

        return new UserModel(uuid, name, isFlying);
    }
}
