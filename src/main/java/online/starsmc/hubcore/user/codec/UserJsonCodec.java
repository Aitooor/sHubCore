package online.starsmc.hubcore.user.codec;

import com.google.gson.*;
import online.starsmc.hubcore.user.UserModel;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserJsonCodec implements JsonSerializer<UserModel>, JsonDeserializer<UserModel> {

    @Override
    public JsonElement serialize(UserModel model, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject serializedModel = new JsonObject();

        serializedModel.addProperty("id", model.getId());

        JsonObject serializedWarpTimestamp = new JsonObject();
        for (Map.Entry<String, Boolean> entry : model.getCanAccess().entrySet()) {
            String warpId = entry.getKey();
            Boolean canAccess = entry.getValue();

            serializedWarpTimestamp.addProperty(warpId, canAccess);
        }
        serializedModel.add("canAccess", serializedWarpTimestamp);

        return serializedModel;
    }

    @Override
    public UserModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject serializedModel = json.getAsJsonObject();

        String id = serializedModel.get("id").getAsString();
        UUID uuid = UUID.fromString(id);

        JsonObject serializedTimestamp = serializedModel.getAsJsonObject("canAccess");
        Map<String, Boolean> canAccessMap = new HashMap<>();
        for (Map.Entry<String, JsonElement> entry : serializedTimestamp.entrySet()) {
            String warpId = entry.getKey();
            boolean canAccess = entry.getValue().getAsBoolean();
            canAccessMap.put(warpId, canAccess);
        }

        return new UserModel(uuid, canAccessMap);
    }
}
