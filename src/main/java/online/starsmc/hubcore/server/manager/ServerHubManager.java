package online.starsmc.hubcore.server.manager;

import online.starsmc.hubcore.Main;
import online.starsmc.hubcore.bungee.BungeeManager;
import online.starsmc.hubcore.model.repository.CachedModelRepository;
import online.starsmc.hubcore.server.ServerModel;
import online.starsmc.hubcore.utils.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import java.util.logging.Level;

@SuppressWarnings("rawtypes")
public class ServerHubManager {

    private final Main plugin;
    private final CachedModelRepository<ServerModel> serversCachedModelRepository;
    private final BungeeManager bungeeManager;

    public ServerHubManager(Main plugin, CachedModelRepository<ServerModel> serversCachedModelRepository, BungeeManager bungeeManager) {
        this.plugin = plugin;
        this.serversCachedModelRepository = serversCachedModelRepository;
        this.bungeeManager = bungeeManager;
    }

    public void createServer(CommandSender sender, ServerModel serverModel) {
        //TODO Need to implement if server exist logic
        try {
            serversCachedModelRepository.saveInBoth(serverModel);
            ChatUtil.sendMsgSenderPrefix(sender, "&aThe server was create correctly");
        } catch (Exception e) {
            ChatUtil.sendMsgSenderPrefix(sender, "&cError, server was not created");
            plugin.getLogger().log(Level.WARNING, "Error, server was not created", e);
        }
    }

    public void removeServer(CommandSender sender, String id) {
        try {
            ServerModel serverModel = serversCachedModelRepository.getOrFind(id);
            if(serverModel == null) {
                ChatUtil.sendMsgSenderPrefix(sender, "&cThe server not exist");
                return;
            }
            serversCachedModelRepository.removeInBoth(serverModel);
            ChatUtil.sendMsgSenderPrefix(sender, "&aThe server was removed correctly");
        } catch (Exception e) {
            ChatUtil.sendMsgSenderPrefix(sender, "&cThe server couldn't be removed");
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

            bungeeManager.teleportToServer(entity.getUniqueId(), serverModel);
        } catch (Exception e) {
            entity.sendMessage("Error, can't teleport to the server");
            plugin.getLogger().log(Level.WARNING, "Can't teleport to the server", e);
        }
    }
}
