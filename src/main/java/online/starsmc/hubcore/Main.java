package online.starsmc.hubcore;

import online.starsmc.hubcore.scoreboard.ScoreboardManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Main extends JavaPlugin {

    private ScoreboardManager scoreboardManager;

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
