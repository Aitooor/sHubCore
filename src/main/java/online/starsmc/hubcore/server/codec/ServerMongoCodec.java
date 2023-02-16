package online.starsmc.hubcore.server.codec;

import online.starsmc.hubcore.model.YamlCodec;
import online.starsmc.hubcore.server.ServerModel;

import java.util.Map;

public class ServerMongoCodec implements YamlCodec<ServerModel> {

    //TODO Implement Mongo and create Codec
    // for implementation on code
    @Override
    public Map<String, Object> serialize(ServerModel model) {
        return null;
    }

    @Override
    public ServerModel deserialize(Map<String, Object> modelData) {
        return null;
    }
}
