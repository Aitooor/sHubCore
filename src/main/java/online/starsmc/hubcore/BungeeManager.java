package online.starsmc.hubcore;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.UUID;

public class BungeeManager {

    private final Main plugin;

    public BungeeManager(Main plugin) {
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

    public void teleportToServer(UUID uuid, String server) {
        Player player = Bukkit.getPlayer(uuid);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
        player.sendMessage("Teleported to the server " + server);
    }
}
