package online.starsmc.hubcore.model.repository;

import online.starsmc.hubcore.model.Model;
import online.starsmc.hubcore.model.YamlCodec;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YamlModelRepository<ModelType extends Model>
        implements ModelRepository<ModelType> {

    private final File folder;
    private final YamlCodec<ModelType> yamlCodec;

    public YamlModelRepository(File folder, YamlCodec<ModelType> yamlCodec) {
        this.folder = folder;
        this.yamlCodec = yamlCodec;
    }


    @Override
    public ModelType find(String id) {
        File modelFile = new File(folder, id + ".yml");
        if(!modelFile.exists()) return null;

        YamlConfiguration modelYaml = YamlConfiguration.loadConfiguration(modelFile);

        return yamlCodec.deserialize(modelYaml.getValues(true));
    }

    @Override
    public void save(ModelType model) throws IOException {
        File modelFile = new File(folder, model.getId() + ".yml");
        if(!modelFile.exists()) modelFile.createNewFile();

        YamlConfiguration modelYaml = new YamlConfiguration();
        modelYaml.addDefaults(yamlCodec.serialize(model));
        modelYaml.save(modelFile);
    }

    @Override
    public void remove(ModelType model) {
        File modelFile = new File(folder, model.getId() + ".yml");
        if (!modelFile.exists()) return;

        modelFile.delete();
    }
}
