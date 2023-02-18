package online.starsmc.hubcore.server;

import com.sun.org.slf4j.internal.Logger;
import online.starsmc.hubcore.Main;
import online.starsmc.hubcore.bungee.manager.BungeeManager;
import online.starsmc.hubcore.model.repository.CachedModelRepository;
import online.starsmc.hubcore.user.UserManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

@SuppressWarnings("rawtypes")
public class ServerManager {

    private final Main plugin;
    private final CachedModelRepository cachedRepository;
    private final Logger logger;
    private final UserManager userManager;
    private final BungeeManager bungeeManager;

    public ServerManager(Main plugin, CachedModelRepository cachedRepository, Logger logger, UserManager userManager, BungeeManager bungeeManager) {
        this.plugin = plugin;
        this.cachedRepository = cachedRepository;
        this.logger = logger;
        this.userManager = userManager;
        this.bungeeManager = bungeeManager;
    }

    @SuppressWarnings("unchecked")
    public void createServer(CommandSender sender, ServerModel serverModel) {
        try {
            cachedRepository.save(serverModel);
            sender.sendMessage("The server was create correctly");
        } catch (Exception e) {
            sender.sendMessage("Error, server was not created");
            logger.error("Error, server was not created", e);
        }
    }

    @SuppressWarnings("unchecked")
    public void removeServer(CommandSender sender, String id) {
        try {
            ServerModel serverModel = (ServerModel) cachedRepository.getOrFind(id);
            if(serverModel == null) {
                sender.sendMessage("The server not exist");
                return;
            }
            cachedRepository.removeInBoth(serverModel);
            sender.sendMessage("The server was removed correctly");
        } catch (Exception e) {
            sender.sendMessage("The server couldn't be removed");
            logger.error("Error, the server couldn't be removed");
        }
    }

    public void teleportToServer(Entity entity, String id) {
        try {
            ServerModel serverModel = (ServerModel) cachedRepository.getOrFindAndCache(id);

            if(serverModel == null) {
                entity.sendMessage("The server not exist");
                return;
            }

            if(!userManager.canAccessServer(entity.getUniqueId(), serverModel)){
                entity.sendMessage("You can't access to the server");
                return;
            }

            bungeeManager.teleportToServer(entity.getUniqueId(), serverModel.getServer());
        } catch (Exception e) {
            entity.sendMessage("Error, can't teleport to the server");
            logger.error("Can't teleport to the server", e);
        }
    }
}
