package online.starsmc.hubcore.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import online.starsmc.hubcore.Main;
import online.starsmc.hubcore.utils.ChatUtil;
import online.starsmc.hubcore.utils.codec.LocationCodec;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import team.unnamed.inject.InjectAll;

@InjectAll
@Command(names = {"hubcore", "sHubCore", "sHubcore", "shubcore", "shubCore"}, permission = "hubcore.admin")
public class MainCommand implements CommandClass {

    private Main plugin;
    @Command(names = {"reload", "rl"}, permission = "hubcore.reload")
    public void reloadCommand(@Sender CommandSender sender){
        if(!(sender instanceof Player)) {
            ChatUtil.sendMsgSenderPrefix(sender, "&cNeed to implement");
            return;
        }
        ChatUtil.sendMsgSenderPrefix(sender, "&cNot done yet");
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
