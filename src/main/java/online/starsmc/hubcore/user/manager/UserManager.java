package online.starsmc.hubcore.user.manager;

import online.starsmc.hubcore.Main;
import online.starsmc.hubcore.model.repository.CachedModelRepository;
import online.starsmc.hubcore.user.UserModel;
import online.starsmc.hubcore.utils.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.logging.Level;


public class UserManager {

    private final Main plugin;
    private final CachedModelRepository<UserModel> userCachedModelRepository;

    public UserManager(Main plugin, CachedModelRepository<UserModel> userCachedModelRepository) {
        this.plugin = plugin;
        this.userCachedModelRepository = userCachedModelRepository;
    }

    public void createUser(CommandSender sender, UserModel userModel) {
        try {
            UserModel findServerModel = userCachedModelRepository.getOrFind(userModel.getId());
            if(findServerModel != null) {
                ChatUtil.sendMsgSenderPrefix(sender, "&cThe server already exist");
                return;
            }
            userCachedModelRepository.saveInBoth(userModel);
        } catch (Exception e) {
            ChatUtil.sendMsgSenderPrefix(sender, "&cError, user was not created");
            plugin.getLogger().log(Level.WARNING, "Error, user was not created", e);
        }
    }

    public void removeUser(CommandSender sender, String id) {
        try {
            UserModel serverModel = userCachedModelRepository.getOrFind(id);
            if(serverModel == null) {
                ChatUtil.sendMsgSenderPrefix(sender, "&cThe user not exist");
                return;
            }
            userCachedModelRepository.removeInBoth(serverModel);
            ChatUtil.sendMsgSenderPrefix(sender, "&aThe user was removed correctly");
        } catch (Exception e) {
            ChatUtil.sendMsgSenderPrefix(sender, "&cThe user couldn't be removed");
            plugin.getLogger().log(Level.WARNING, "Error, the user couldn't be removed", e);
        }
    }

    public void isFlying(Player player) {
        try {
            UserModel userModel = userCachedModelRepository.getOrFindAndCache(player.getUniqueId().toString());

            if(userModel == null) {
                userModel = new UserModel(player.getUniqueId(), player.getName(), false);
                player.setAllowFlight(false);
                player.setFlying(false);
                userModel.setFlying(false);
                userCachedModelRepository.saveInBoth(userModel);
                return;
            }

            if(userModel.isFlying()) {
                player.setAllowFlight(true);
                player.setFlying(true);
                userModel.setFlying(true);
                ChatUtil.sendMsgPlayerPrefix(player, "&aFly activado");
                userCachedModelRepository.saveInBoth(userModel);
            }
        } catch (Exception e) {
            player.sendMessage("Error, can't toggle fly to the user");
            plugin.getLogger().log(Level.WARNING, "Can't toggle fly to the user", e);
        }
    }

    public void toggleFly(Player player) {
        try {
            UserModel userModel = userCachedModelRepository.getOrFindAndCache(player.getUniqueId().toString());

            if(userModel == null) {
                userModel = new UserModel(player.getUniqueId(), player.getName(), true);
                player.setAllowFlight(true);
                player.setFlying(true);
                userModel.setFlying(true);
                ChatUtil.sendMsgPlayerPrefix(player, "&aFly activado");
                userCachedModelRepository.saveInBoth(userModel);
                return;
            }

            if(!player.isFlying()) {
                player.setAllowFlight(true);
                player.setFlying(true);
                userModel.setFlying(true);
                ChatUtil.sendMsgPlayerPrefix(player, "&aFly activado");
                userCachedModelRepository.saveInBoth(userModel);
                return;
            }

            player.setAllowFlight(false);
            player.setFlying(false);
            userModel.setFlying(false);
            ChatUtil.sendMsgPlayerPrefix(player, "&cFly desactivado");
            userCachedModelRepository.saveInBoth(userModel);
        } catch (Exception e) {
            player.sendMessage("Error, can't toggle fly to the user");
            plugin.getLogger().log(Level.WARNING, "Can't toggle fly to the user", e);
        }
    }
}
