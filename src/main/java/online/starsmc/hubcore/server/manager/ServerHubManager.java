package online.starsmc.hubcore.server.manager;

import online.starsmc.hubcore.Main;
import online.starsmc.hubcore.bungee.BungeeManager;
import online.starsmc.hubcore.model.repository.CachedModelRepository;
import online.starsmc.hubcore.server.ServerModel;
import online.starsmc.hubcore.user.UserManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import java.util.logging.Level;

@SuppressWarnings("rawtypes")
public class ServerHubManager {

    private final Main plugin;
    private final CachedModelRepository<ServerModel> serversCachedModelRepository;
    private final UserManager userManager;
    private final BungeeManager bungeeManager;

    public ServerHubManager(Main plugin, CachedModelRepository<ServerModel> serversCachedModelRepository, UserManager userManager, BungeeManager bungeeManager) {
        this.plugin = plugin;
        this.serversCachedModelRepository = serversCachedModelRepository;
        this.userManager = userManager;
        this.bungeeManager = bungeeManager;
    }

    @SuppressWarnings("unchecked")
    public void createServer(CommandSender sender, ServerModel serverModel) {
        try {
            serversCachedModelRepository.saveInBoth(serverModel);
            sender.sendMessage("The server was create correctly");
        } catch (Exception e) {
            sender.sendMessage("Error, server was not created");
            plugin.getLogger().log(Level.WARNING, "Error, server was not created", e);
        }
    }

    @SuppressWarnings("unchecked")
    public void removeServer(CommandSender sender, String id) {
        try {
            ServerModel serverModel = serversCachedModelRepository.getOrFind(id);
            if(serverModel == null) {
                sender.sendMessage("The server not exist");
                return;
            }
            serversCachedModelRepository.removeInBoth(serverModel);
            sender.sendMessage("The server was removed correctly");
        } catch (Exception e) {
            sender.sendMessage("The server couldn't be removed");
            plugin.getLogger().log(Level.WARNING, "Error, the server couldn't be removed", e);
        }
    }

    public void teleportToServer(Entity entity, String id) {
        try {
            ServerModel serverModel = serversCachedModelRepository.getOrFindAndCache(id);

            if(serverModel == null) {
                entity.sendMessage("The server not exist");
                return;
            }

            if(!userManager.canAccessServer(entity.getUniqueId(), serverModel)){
                entity.sendMessage("You can't access to the server");
                return;
            }

            bungeeManager.teleportToServer(entity.getUniqueId(), serverModel);
        } catch (Exception e) {
            entity.sendMessage("Error, can't teleport to the server");
            plugin.getLogger().log(Level.WARNING, "Can't teleport to the server", e);
        }
    }
}
