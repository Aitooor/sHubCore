package online.starsmc.hubcore.server;

import online.starsmc.hubcore.model.Model;

import java.util.List;

public class ServerModel implements Model {

    private final String id;
    private String permission;
    private String name;
    private List<String> lore;

    public ServerModel(String id, String name, List<String> lore, String permission) {
        this.id = id;
        this.name = name;
        this.lore = lore;
        this.permission = permission;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
