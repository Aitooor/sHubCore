package online.starsmc.hubcore;

import online.starsmc.hubcore.bungee.BungeeService;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private BungeeService bungeeService;

    @Override
    public void onLoad() {

    }
    @Override
    public void onEnable() {
        bungeeService.run();
    }

    @Override
    public void onDisable() {
        bungeeService.disable();
    }
}
