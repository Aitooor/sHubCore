package online.starsmc.hubcore.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import online.starsmc.hubcore.server.ServerModel;
import online.starsmc.hubcore.server.manager.ServerGameManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.unnamed.inject.InjectAll;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@InjectAll
@Command(names = {"server", "servers"}, permission = "hubcore.server")
public class ServerCommand implements CommandClass {

    private ServerGameManager serverGameManager;

    @Command(names = {"create", "c"})
    public void create(@Sender CommandSender sender, String id){
        if(!(sender instanceof Player)) {
            sender.sendMessage("This command only can execute in game");
            return;
        }

        Player player = (Player) sender;

        String name = id;
        String server = id;
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("Lore of " + id);
        lore.add("");
        String permission = "hubcore.server." + id;

        serverGameManager.createServer(
                player,
                new ServerModel(id, name, server, lore, permission)
        );
    }

    @Command(names = {"remove", "r"})
    public void remove(@Sender CommandSender sender, String id){
        if(!(sender instanceof Player)) {
            sender.sendMessage("This command only can execute in game");
            return;
        }

        Player player = (Player) sender;

        if(serverGameManager == null) {
            player.sendMessage("This server not exist");
            return;
        }
        serverGameManager.removeServer(player, id);
    }

    @Command(names = {"teleport", "tp"})
    public void teleport(@Sender CommandSender sender, String id){
        if(!(sender instanceof Player)) {
            sender.sendMessage("This command only can execute in game");
            return;
        }

        Player player = (Player) sender;

        if(serverGameManager == null) {
            player.sendMessage("This server not exist");
            return;
        }
        serverGameManager.teleportToServer(player, id);
    }
}
