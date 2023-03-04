package online.starsmc.hubcore.module;

import com.google.common.cache.CacheBuilder;
import online.starsmc.hubcore.Main;
import online.starsmc.hubcore.bungee.BungeeManager;
import online.starsmc.hubcore.model.repository.CachedModelRepository;
import online.starsmc.hubcore.model.repository.GuavaModelRepository;
import online.starsmc.hubcore.model.repository.YamlModelRepository;
import online.starsmc.hubcore.server.ServerModel;
import online.starsmc.hubcore.server.codec.ServerYamlCodec;
import online.starsmc.hubcore.server.manager.ServerGameManager;
import online.starsmc.hubcore.server.manager.ServerHubManager;
import online.starsmc.hubcore.service.CommandService;
import online.starsmc.hubcore.service.ConfigurationService;
import online.starsmc.hubcore.service.Service;
import online.starsmc.hubcore.utils.BukkitConfiguration;
import team.unnamed.inject.AbstractModule;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class PluginModule extends AbstractModule {
        private final Main plugin;
        private final CachedModelRepository<ServerModel> serverCachedModelRepository;
        private final CachedModelRepository<ServerModel> hubCachedModelRepository;
        private final BungeeManager bungeeManager;
        private final ServerHubManager serverHubManager;
        private final ServerGameManager serverGameManager;

        public PluginModule(Main plugin) {
            File pluginFolder = plugin.getDataFolder();
            Path pluginPath = pluginFolder.toPath();

            this.plugin = plugin;
            this.serverCachedModelRepository = new CachedModelRepository<>(
                    new YamlModelRepository<>(pluginPath.resolve("servers"), new ServerYamlCodec()),
                    new GuavaModelRepository<>(
                            CacheBuilder.newBuilder()
                                    .expireAfterAccess(10, TimeUnit.MINUTES)
                                    .expireAfterWrite(10, TimeUnit.MINUTES)
                                    .build()
                    )
            );
            this.hubCachedModelRepository = new CachedModelRepository<>(
                    new YamlModelRepository<>(pluginPath.resolve("hubs"), new ServerYamlCodec()),
                    new GuavaModelRepository<>(
                            CacheBuilder.newBuilder()
                                    .expireAfterAccess(10, TimeUnit.MINUTES)
                                    .expireAfterWrite(10, TimeUnit.MINUTES)
                                    .build()
                    )
            );

            this.bungeeManager = new BungeeManager(plugin);
            this.serverGameManager = new ServerGameManager(plugin, serverCachedModelRepository, bungeeManager);
            this.serverHubManager = new ServerHubManager(plugin, hubCachedModelRepository, bungeeManager);
        }

        @Override
        protected void configure() {
            bind(Main.class).toInstance(plugin);

            bind(ServerHubManager.class).toInstance(serverHubManager);
            bind(ServerGameManager.class).toInstance(serverGameManager);
            bind(BungeeManager.class).toInstance(bungeeManager);

            multibind(Service.class)
                    .asSet()
                    .to(ConfigurationService.class)
                    .to(CommandService.class);

            bind(BukkitConfiguration.class)
                    .named("scoreboard")
                    .toInstance(new BukkitConfiguration(plugin, "scoreboard"));

            install(new CommandModule());
        }
}
