package online.starsmc.hubcore.model;

import java.util.Map;

public interface YamlCodec<ModelType extends Model> {

    Map<String, Object> serialize(ModelType model);

    ModelType deserialize(Map<String, Object> modelData);
}
