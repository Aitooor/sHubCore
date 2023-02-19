package online.starsmc.hubcore;

import online.starsmc.hubcore.scoreboard.ScoreboardManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private final ScoreboardManager scoreboardManager = new ScoreboardManager(this);

    @Override
    public void onLoad() {

    }
    @Override
    public void onEnable() {
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        scoreboardManager.load();
    }

    @Override
    public void onDisable() {
        //make sure to unregister the registered channels in case of a reload
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
        scoreboardManager.disable();
    }
}
