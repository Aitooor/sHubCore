package online.starsmc.hubcore.server.command.server;

import online.starsmc.hubcore.server.manager.ServerGameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ServerCommand implements CommandExecutor {

    private final ServerGameManager serverGameManager;

    public ServerCommand(ServerGameManager serverGameManager) {
        this.serverGameManager = serverGameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("This command only can execute in game");
            return true;
        }

        Player player = (Player) sender;

        if(args.length != 1) {
            player.sendMessage("Usage: /server <id>");
            return true;
        }

        String id = args[0];

        serverGameManager.teleportToServer(player, id);

        return true;
    }
}
