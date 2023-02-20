package online.starsmc.hubcore.server.command.server;

import online.starsmc.hubcore.server.manager.ServerGameManager;
import online.starsmc.hubcore.server.ServerModel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ServerCreateCommand implements CommandExecutor {

    private final ServerGameManager serverGameManager;

    public ServerCreateCommand(ServerGameManager serverGameManager) {
        this.serverGameManager = serverGameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("This command only can execute in game");
            return true;
        }

        Player player = (Player) sender;

        if(args.length != 5) {
            player.sendMessage("Usage: /createserver <id> <name> <server> <permission>");
            return true;
        }

        String id = args[0];
        String name = args[1];
        String server = args[2];
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("Lore");
        lore.add("");
        String permission = args[3];

        serverGameManager.createServer(
                player,
                new ServerModel(id, name, server, lore, permission)
        );

        return true;
    }
}
