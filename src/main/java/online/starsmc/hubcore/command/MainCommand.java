package online.starsmc.hubcore.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import online.starsmc.hubcore.Main;
import online.starsmc.hubcore.utils.BukkitConfiguration;
import online.starsmc.hubcore.utils.ChatUtil;
import online.starsmc.hubcore.utils.codec.LocationCodec;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import team.unnamed.inject.InjectAll;

import javax.inject.Inject;
import javax.inject.Named;

@InjectAll
@Command(names = {"hubcore", "sHubCore", "sHubcore", "shubcore", "shubCore"}, permission = "hubcore.admin")
public class MainCommand implements CommandClass {

    private Main plugin;
    @Named("scoreboard")
    private BukkitConfiguration scoreboard;

    @Command(names = {"reload", "rl"}, permission = "hubcore.reload")
    public void reloadCommand(@Sender CommandSender sender){
        if(!(sender instanceof Player)) {
            plugin.reloadConfig();
            scoreboard.reload();
            ChatUtil.sendMsgSenderPrefix(sender, "&cReloaded correctly");
            return;
        }
        Player player = (Player) sender;
        plugin.reloadConfig();
        scoreboard.reload();
        ChatUtil.sendMsgPlayerPrefix(player, "&cReloaded correctly");
    }
    @Command(names = "setspawn", permission = "hubcore.setspawn")
    public void setSpawn(@Sender Player player) {
        plugin.getConfig().set("spawn_location", LocationCodec.serialize(player.getLocation()));
        plugin.saveConfig();
        plugin.reloadConfig();

        ChatUtil.sendMsgPlayerPrefix(player, "&aSpawn correctly set");
    }

    @Command(names = "spawn", permission = "hubcore.spawn")
    public void spawn(@Sender Player player) {
        player.teleport(LocationCodec.deserialize(plugin.getConfig().getString("spawn_location")));
        ChatUtil.sendMsgPlayerPrefix(player, "&aTeleported to spawn");
    }
}
