package online.starsmc.hubcore.server.codec;

import online.starsmc.hubcore.model.YamlCodec;
import online.starsmc.hubcore.server.ServerModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerYamlCodec implements YamlCodec<ServerModel> {

    @Override
    public Map<String, Object> serialize(ServerModel model) {
        Map<String, Object> serializedModel = new HashMap<>();

        serializedModel.put("id", model.getId());
        serializedModel.put("name", model.getName());

        List<String> lore = new ArrayList<>(model.getLore());
        serializedModel.put("lore", lore);

        serializedModel.put("server", model.getServer());
        serializedModel.put("permission", model.getPermission());

        return serializedModel;
    }

    @SuppressWarnings({"unchecked", "CollectionAddedToSelf"})
    @Override
    public ServerModel deserialize(Map<String, Object> modelData) {
        String id = (String) modelData.get("id");
        String name = (String) modelData.get("name");

        List<String> serializedLore = (List<String>) modelData.get("lore");
        serializedLore.addAll(serializedLore);

        String server = (String) modelData.get("server");
        String permission = (String) modelData.get("permission");

        return new ServerModel(id, name, serializedLore, server, permission);
    }
}
