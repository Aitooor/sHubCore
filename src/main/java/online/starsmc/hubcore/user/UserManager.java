package online.starsmc.hubcore.user;

import online.starsmc.hubcore.model.repository.CachedModelRepository;
import online.starsmc.hubcore.server.ServerModel;

import javax.inject.Inject;
import java.util.UUID;

public class UserManager {

    @Inject private CachedModelRepository<UserModel> cachedRepository;

    public boolean canAccessServer(UUID userId, ServerModel serverModel) throws Exception {
        UserModel userModel = cachedRepository.getOrFindAndCache(userId.toString());
        if(userModel == null) {
            return false;
        }

        return userModel.getCanAccess().get(serverModel.getId());
    }
}
