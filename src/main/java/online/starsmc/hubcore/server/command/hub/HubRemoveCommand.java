package online.starsmc.hubcore.server.command.hub;

import online.starsmc.hubcore.server.manager.ServerHubManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubRemoveCommand implements CommandExecutor {

    private final ServerHubManager serverHubManager;

    public HubRemoveCommand(ServerHubManager serverHubManager) {
        this.serverHubManager = serverHubManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("This command only can execute in game");
            return true;
        }

        Player player = (Player) sender;

        if(args.length != 1) {
            player.sendMessage("Usage: /removehub <id>");
            return true;
        }

        String id = args[0];

        serverHubManager.removeServer(player, id);

        return true;
    }
}
