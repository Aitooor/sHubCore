package online.starsmc.hubcore.module;

import online.starsmc.hubcore.Main;
import online.starsmc.hubcore.server.manager.ServerGameManager;
import online.starsmc.hubcore.server.manager.ServerHubManager;
import online.starsmc.hubcore.service.CommandService;
import online.starsmc.hubcore.service.Service;
import team.unnamed.inject.AbstractModule;

public class PluginModule extends AbstractModule {
        private final Main plugin;
        private final ServerHubManager serverHubManager = new ServerHubManager();
        private final ServerGameManager serverGameManager = new ServerGameManager();

        public PluginModule(Main plugin) {
            this.plugin = plugin;
        }

        @Override
        protected void configure() {
            bind(Main.class).toInstance(plugin);
            bind(ServerHubManager.class).toInstance(serverHubManager);
            bind(ServerGameManager.class).toInstance(serverGameManager);

            multibind(Service.class).asSet().to(CommandService.class);

            install(new CommandModule());
        }
}
