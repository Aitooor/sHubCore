package online.starsmc.hubcore.server;

import com.sun.org.slf4j.internal.Logger;
import online.starsmc.hubcore.BungeeManager;
import online.starsmc.hubcore.Main;
import online.starsmc.hubcore.model.repository.CachedModelRepository;
import online.starsmc.hubcore.user.UserManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

public class ServerManager {

    private Main plugin;
    private CachedModelRepository cachedRepository;
    private Logger logger;
    private UserManager userManager;

    public ServerManager(Main plugin, CachedModelRepository cachedRepository, Logger logger, UserManager userManager) {
        this.plugin = plugin;
        this.cachedRepository = cachedRepository;
        this.logger = logger;
        this.userManager = userManager;
    }

    public void createServer(CommandSender sender, ServerModel serverModel) {
        try {
            cachedRepository.save(serverModel);
            sender.sendMessage("El servidor se ha creado correctamente");
        } catch (Exception e) {
            sender.sendMessage("Error, server was not created");
            logger.error("Error, server was not created", e);
        }
    }

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

            new BungeeManager(plugin).teleportToServer(entity.getUniqueId(), serverModel.getServer());
        } catch (Exception e) {
            entity.sendMessage("Error, can't teleport to the server");
            logger.error("Can't teleport to the server", e);
        }
    }
}
