package online.starsmc.hubcore.command.server;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import online.starsmc.hubcore.server.ServerModel;
import online.starsmc.hubcore.server.manager.ServerHubManager;
import online.starsmc.hubcore.utils.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.unnamed.inject.InjectAll;

import java.util.ArrayList;
import java.util.List;

@InjectAll
@Command(names = {"hub", "hubs"}, permission = "hubcore.hub")
public class HubCommand implements CommandClass {

    private ServerHubManager serverHubManager;

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

        serverHubManager.createServer(
                player,
                new ServerModel(id, id, lore, id, null)
        );
    }

    @Command(names = {"remove", "r"})
    public void remove(@Sender CommandSender sender, String id){
        if(!(sender instanceof Player)) {
            ChatUtil.sendMsgSender(sender, "&cThis command only can execute in game");
            return;
        }

        Player player = (Player) sender;

        serverHubManager.removeServer(player, id);
    }

    @Command(names = {"teleport", "tp"})
    public void teleport(@Sender CommandSender sender, String id){
        if(!(sender instanceof Player)) {
            ChatUtil.sendMsgSender(sender, "&cThis command only can execute in game");
            return;
        }

        Player player = (Player) sender;

        serverHubManager.teleportToServer(player, id);
    }
}
