package online.starsmc.hubcore.user.codec;

import com.google.gson.*;
import online.starsmc.hubcore.server.ServerModel;
import online.starsmc.hubcore.user.UserModel;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

public class UserJsonCodec implements JsonSerializer<UserModel>, JsonDeserializer<UserModel> {

    @Override
    public JsonElement serialize(UserModel model, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject serializedModel = new JsonObject();

        serializedModel.addProperty("id", model.getId());
        serializedModel.addProperty("name", model.getName());
        serializedModel.addProperty("isFlying", model.isFlying());

        return serializedModel;
    }

    @Override
    public UserModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject serializedModel = json.getAsJsonObject();

        String id = serializedModel.get("id").getAsString();
        UUID uuid = UUID.fromString(id);
        String name = serializedModel.get("name").getAsString();
        boolean isFlying = serializedModel.get("isFlying").getAsBoolean();

        return new UserModel(uuid, name, isFlying);
    }
}
