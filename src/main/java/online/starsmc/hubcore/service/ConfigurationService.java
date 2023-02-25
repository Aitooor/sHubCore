package online.starsmc.hubcore.service;

import online.starsmc.hubcore.Main;
import online.starsmc.hubcore.utils.BukkitConfiguration;
import team.unnamed.inject.InjectAll;

import javax.inject.Named;

@InjectAll
public class ConfigurationService implements Service {

    private Main plugin;
    @Named("scoreboard")
    private BukkitConfiguration scoreboardConfig;
    @Override
    public void start() {
        plugin.getLogger().info("Configuration load!");
    }

    @Override
    public void stop() {
        scoreboardConfig.save();
        plugin.getLogger().info("Configuration files saved!");
    }
}
