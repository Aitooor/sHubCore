package online.starsmc.hubcore.module;

import online.starsmc.hubcore.Main;
import online.starsmc.hubcore.service.CommandService;
import online.starsmc.hubcore.service.Service;
import team.unnamed.inject.AbstractModule;

public class PluginModule extends AbstractModule {
        private final Main plugin;
        public PluginModule(Main plugin) {
            this.plugin = plugin;
        }
        @Override
        protected void configure() {
            bind(Main.class).toInstance(plugin);
            multibind(Service.class).asSet().to(CommandService.class);
            install(new CommandModule());
        }
}
