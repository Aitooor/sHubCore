package online.starsmc.hubcore;

import online.starsmc.hubcore.module.PluginModule;
import online.starsmc.hubcore.scoreboard.ScoreboardManager;
import online.starsmc.hubcore.service.Service;
import online.starsmc.hubcore.utils.BukkitConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import team.unnamed.inject.Injector;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Set;

public class Main extends JavaPlugin {

    @Inject private Set<Service> services;
    @Inject
    @Named("scoreboard")
    private BukkitConfiguration scoreboardConfig;
    private ScoreboardManager scoreboardManager = new ScoreboardManager(this);

    @Override
    public void onLoad() {
        Injector injector = Injector.create(new PluginModule(this));
        injector.injectMembers(this);
    }
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        services.forEach(Service::start);
        if(scoreboardConfig.get().getBoolean("enable")) {
            scoreboardManager.load();
        }
    }

    @Override
    public void onDisable() {
        //make sure to unregister the registered channels in case of a reload
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        services.forEach(Service::stop);
        if(scoreboardConfig.get().getBoolean("enable")) {
            scoreboardManager.disable();
        }
    }
}
