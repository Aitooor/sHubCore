package online.starsmc.hubcore.bungee;

import online.starsmc.hubcore.Main;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BungeeService {

    private final Main plugin;

    public BungeeService(Main plugin) {
        this.plugin = plugin;
    }

    public void run() {
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord", (PluginMessageListener) plugin);
    }

    public void disable() {
        //make sure to unregister the registered channels in case of a reload
        plugin.getServer().getMessenger().unregisterOutgoingPluginChannel(plugin);
        plugin.getServer().getMessenger().unregisterIncomingPluginChannel(plugin);
    }
}
