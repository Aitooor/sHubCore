package online.starsmc.hubcore.user;

import online.starsmc.hubcore.model.repository.CachedModelRepository;
import online.starsmc.hubcore.server.ServerModel;

import java.util.UUID;

public class UserManager {

    private CachedModelRepository<UserModel> usersCachedModelRepository;

    public UserManager(CachedModelRepository<UserModel> usersCachedModelRepository) {
        this.usersCachedModelRepository = usersCachedModelRepository;
    }

    public boolean canAccessServer(UUID userId, ServerModel serverModel) throws Exception {
        UserModel userModel = usersCachedModelRepository.getOrFindAndCache(userId.toString());
        if(userModel == null) {
            return false;
        }

        return userModel.getCanAccess().get(serverModel.getId());
    }
}
