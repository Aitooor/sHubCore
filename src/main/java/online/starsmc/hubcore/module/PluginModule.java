package online.starsmc.hubcore.module;

import online.starsmc.hubcore.Main;
import online.starsmc.hubcore.bungee.BungeeManager;
import online.starsmc.hubcore.model.Model;
import online.starsmc.hubcore.model.repository.CachedModelRepository;
import online.starsmc.hubcore.server.ServerModel;
import online.starsmc.hubcore.server.manager.ServerGameManager;
import online.starsmc.hubcore.server.manager.ServerHubManager;
import online.starsmc.hubcore.service.CommandService;
import online.starsmc.hubcore.service.Service;
import online.starsmc.hubcore.user.UserManager;
import online.starsmc.hubcore.user.UserModel;
import team.unnamed.inject.AbstractModule;

public class PluginModule extends AbstractModule {
        private final Main plugin;
        private final CachedModelRepository<UserModel> usersCachedModelRepository = new CachedModelRepository<>();
        private final CachedModelRepository<ServerModel> serversCachedModelRepository = new CachedModelRepository<>();
        private final ServerHubManager serverHubManager = new ServerHubManager();
        private final ServerGameManager serverGameManager = new ServerGameManager();
        private final UserManager userManager = new UserManager();
        private final BungeeManager bungeeManager = new BungeeManager();

        public PluginModule(Main plugin) {
            this.plugin = plugin;
        }

        @Override
        protected void configure() {
            bind(Main.class).toInstance(plugin);
            bind(CachedModelRepository.class).toInstance(usersCachedModelRepository);
            bind(CachedModelRepository.class).toInstance(serversCachedModelRepository);

            bind(ServerHubManager.class).toInstance(serverHubManager);
            bind(ServerGameManager.class).toInstance(serverGameManager);
            bind(UserManager.class).toInstance(userManager);
            bind(BungeeManager.class).toInstance(bungeeManager);

            multibind(Service.class).asSet().to(CommandService.class);

            install(new CommandModule());
        }
}
