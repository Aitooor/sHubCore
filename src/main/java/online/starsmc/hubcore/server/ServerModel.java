package online.starsmc.hubcore.server;

import online.starsmc.hubcore.model.Model;

import java.util.List;

public class ServerModel implements Model {

    private final String id;
    private String name;
    private List<String> lore;
    private String server;
    private String permission;

    public ServerModel(String id, String name, List<String> lore, String server, String permission) {
        this.id = id;
        this.lore = lore;
        this.name = name;
        this.server = server;
        this.permission = permission;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
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
