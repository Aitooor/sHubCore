package online.starsmc.hubcore.user;

import online.starsmc.hubcore.model.repository.CachedModelRepository;
import online.starsmc.hubcore.server.ServerModel;

import java.util.UUID;

public class UserManager {

    private final CachedModelRepository<UserModel> cachedRepository;

    public UserManager(CachedModelRepository<UserModel> cachedRepository) {
        this.cachedRepository = cachedRepository;
    }

    public boolean canAccessServer(UUID userId, ServerModel serverModel) throws Exception {
        UserModel userModel = cachedRepository.getOrFindAndCache(userId.toString());
        if(userModel == null) {
            return false;
        }

        return userModel.getCanAccess().get(serverModel.getId());
    }
}
