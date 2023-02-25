package online.starsmc.hubcore.server.codec;

import com.google.gson.*;
import online.starsmc.hubcore.server.ServerModel;

import java.lang.reflect.Type;
import java.util.List;

public class ServerJsonCodec implements JsonSerializer<ServerModel>, JsonDeserializer<ServerModel> {

    @Override
    public JsonElement serialize(ServerModel model, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject serializedModel = new JsonObject();

        serializedModel.addProperty("id", model.getId());
        serializedModel.addProperty("name", model.getName());
        serializedModel.addProperty("server", model.getServer());
        serializedModel.addProperty("permission", model.getPermission());

        JsonArray lore = new JsonArray();
        serializedModel.add("lore", lore);

        return serializedModel;
    }

    @Override
    public ServerModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject serializedModel = json.getAsJsonObject();

        String id = serializedModel.get("id").getAsString();
        String name = serializedModel.get("name").getAsString();
        String server = serializedModel.get("server").getAsString();
        JsonElement permissionElement = serializedModel.get("permission");
        String permission = permissionElement == null ? null : permissionElement.getAsString();
        List<String> lore = (List<String>) serializedModel.get("lore");

        return new ServerModel(id, name, server, lore, permission);
    }
}
