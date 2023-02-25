package online.starsmc.hubcore.command.server;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import online.starsmc.hubcore.server.ServerModel;
import online.starsmc.hubcore.server.manager.ServerGameManager;
import online.starsmc.hubcore.utils.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.unnamed.inject.InjectAll;

import java.util.ArrayList;
import java.util.List;

@InjectAll
@Command(names = {"server", "servers"}, permission = "hubcore.server")
public class ServerCommand implements CommandClass {

    private ServerGameManager serverGameManager;

    @Command(names = {"create", "c"})
    public void create(@Sender CommandSender sender, String id){
        if(!(sender instanceof Player)) {
            ChatUtil.sendMsgSender(sender, "&cThis command only can execute in game");
            return;
        }

        Player player = (Player) sender;

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("Lore of " + id);
        lore.add("");

        serverGameManager.createServer(
                player,
                new ServerModel(id, id, id, lore, null)
        );
    }

    @Command(names = {"remove", "r"})
    public void remove(@Sender CommandSender sender, String id){
        if(!(sender instanceof Player)) {
            ChatUtil.sendMsgSender(sender, "&cThis command only can execute in game");
            return;
        }

        Player player = (Player) sender;

        serverGameManager.removeServer(player, id);
    }

    @Command(names = {"teleport", "tp"})
    public void teleport(@Sender CommandSender sender, String id){
        if(!(sender instanceof Player)) {
            ChatUtil.sendMsgSender(sender, "&cThis command only can execute in game");
            return;
        }

        Player player = (Player) sender;

        serverGameManager.teleportToServer(player, id);
    }
}
