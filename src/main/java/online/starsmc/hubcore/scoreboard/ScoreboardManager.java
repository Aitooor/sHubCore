package online.starsmc.hubcore.scoreboard;

import online.starsmc.hubcore.Main;
import online.starsmc.hubcore.scoreboard.fastboard.FastBoard;
import online.starsmc.hubcore.utils.ChatUtil;
import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import team.unnamed.inject.InjectAll;

import javax.inject.Inject;
import java.util.*;

import static org.bukkit.Bukkit.getServer;

public class ScoreboardManager implements Listener {

    @Inject private Main plugin;
    private final Server getServer = getServer();
    private final Map<UUID, FastBoard> boards = new HashMap<>();

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
        //TODO Add to config file
        board.updateTitle(ChatUtil.translate("&b&lsHub &7#1"));

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
        //TODO Add to config file
        List<String> lines = new ArrayList<>();
        lines.add("");
        lines.add("Players " + getServer().getOnlinePlayers().size());
        lines.add("");
        lines.add("Kills " + board.getPlayer().getStatistic(Statistic.PLAYER_KILLS));
        lines.add("");

        board.updateLines(ChatUtil.translate(lines));
    }

}
