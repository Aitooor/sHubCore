package online.starsmc.hubcore.listener;

import me.clip.placeholderapi.PlaceholderAPI;
import online.starsmc.hubcore.Main;
import online.starsmc.hubcore.utils.ChatUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import team.unnamed.inject.InjectAll;

import java.util.Objects;

@InjectAll
public class ChatListener implements Listener {

    private FileConfiguration config;

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        event.setFormat(ChatUtil.translate(
                PlaceholderAPI.setPlaceholders(
                        player,
                        Objects.requireNonNull(config.getString("chat_format"))
                                .replace("%player%", player.getDisplayName())
                                .replace("%message%", event.getMessage())
                )
        ));
    }
}
