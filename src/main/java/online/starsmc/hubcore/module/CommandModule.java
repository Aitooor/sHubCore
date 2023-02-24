package online.starsmc.hubcore.module;

import me.fixeddev.commandflow.annotated.CommandClass;
import online.starsmc.hubcore.command.server.HubCommand;
import online.starsmc.hubcore.command.MainCommand;
import online.starsmc.hubcore.command.server.ServerCommand;
import team.unnamed.inject.AbstractModule;

public class CommandModule extends AbstractModule {

        @Override
        protected void configure() {
            multibind(CommandClass.class).asSet().to(MainCommand.class);
            multibind(CommandClass.class).asSet().to(HubCommand.class);
            multibind(CommandClass.class).asSet().to(ServerCommand.class);
        }
}
