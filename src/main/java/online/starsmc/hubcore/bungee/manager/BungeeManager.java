package online.starsmc.hubcore.bungee.manager;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import online.starsmc.hubcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.UUID;

public class BungeeManager {

    private final Main plugin;

    public BungeeManager(Main plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("UnstableApiUsage")
    public void teleportToServer(UUID uuid, String server) {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null) {
            return;
        }
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
        player.sendMessage("Teleported to the server " + server);
    }

    //TODO Server local count, Total in Hubs count
}
