package online.starsmc.hubcore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        event.getFormat();

        //TODO Add to config
        event.setFormat(event.getPlayer().getDisplayName() + ": " + event.getMessage());
    }
}
