package online.starsmc.hubcore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage(null);

        player.teleport(player.getLocation()); //Change to spawn point
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(player.getLocation().getY() < 0) {
            player.teleport(player.getLocation()); //Change to spawn point
        }
    }
}
