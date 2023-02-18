package online.starsmc.hubcore;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private BungeeManager bungeeManager;

    @Override
    public void onLoad() {

    }
    @Override
    public void onEnable() {
        bungeeManager.run();
    }

    @Override
    public void onDisable() {
        bungeeManager.disable();
    }
}
