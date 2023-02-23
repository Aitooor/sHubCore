package online.starsmc.hubcore.bungee;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import online.starsmc.hubcore.Main;
import online.starsmc.hubcore.server.ServerModel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import java.util.UUID;

public class BungeeManager {

    @Inject private Main plugin;

    @SuppressWarnings("UnstableApiUsage")
    public void teleportToServer(UUID uuid, ServerModel serverModel) {
        Player player = Bukkit.getPlayer(uuid);
        String server = serverModel.getServer();

        if (player == null) {
            return;
        }
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendMessage("Teleported to the server " + server);
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

    @SuppressWarnings("UnstableApiUsage")
    public void serverPlayerCount(UUID uuid, ServerModel serverModel) {
        Player player = Bukkit.getPlayer(uuid);
        String server = serverModel.getServer();

        if (player == null) {
            return;
        }

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF(server);
        player.sendMessage("Teleported to the server " + server);
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

    @SuppressWarnings("UnstableApiUsage")
    public void allServerPlayerCount(UUID uuid, ServerModel serverModel) {
        Player player = Bukkit.getPlayer(uuid);
        String server = serverModel.getServer();

        if (player == null) {
            return;
        }

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF("ALL");
        player.sendMessage("Teleported to the server " + server);
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }
}
