package online.starsmc.hubcore.scoreboard;

import me.clip.placeholderapi.PlaceholderAPI;
import online.starsmc.hubcore.Main;
import online.starsmc.hubcore.scoreboard.fastboard.FastBoard;
import online.starsmc.hubcore.utils.BukkitConfiguration;
import online.starsmc.hubcore.utils.ChatUtil;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.inject.Named;
import java.util.*;

import static org.bukkit.Bukkit.getServer;

public class ScoreboardManager implements Listener {

    private final Main plugin;
    private final FileConfiguration config;
    @Named("scoreboard")
    private BukkitConfiguration scoreboard;
    private final Server getServer = getServer();
    private final Map<UUID, FastBoard> boards = new HashMap<>();

    public ScoreboardManager(Main plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        this.scoreboard = new BukkitConfiguration(plugin, "scoreboard");
    }

    public void load() {
        getServer.getPluginManager().registerEvents(this, plugin);

        getServer.getScheduler().runTaskTimer(plugin, () -> {
            for (FastBoard board : this.boards.values()) {
                updateBoard(board);
            }
        }, 0, 20);
    }

    public void disable() {
        boards.clear();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        FastBoard board = new FastBoard(player);
        board.updateTitle(
                ChatUtil.translate(Objects.requireNonNull(scoreboard.get().getString("title"))
                        .replace("%id%", Objects.requireNonNull(config.getString("hub_id")))
                )
        );

        this.boards.put(player.getUniqueId(), board);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        FastBoard board = this.boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }

    private void updateBoard(FastBoard board) {
        board.updateLines(ChatUtil.translate(PlaceholderAPI.setPlaceholders(
            board.getPlayer(), scoreboard.get().getStringList("lines")
        )));
    }

}
