package online.starsmc.hubcore.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;
import team.unnamed.inject.InjectAll;

@InjectAll
@Command(names = {"hubcore", "sHubCore", "sHubcore", "shubcore", "shubCore"})
public class MainCommand implements CommandClass {
    @Command(names = {"reload", "rl"},permission = "hubcore.reload")
    public void reloadCommand(@Sender Player player){
        //plugin.reload();
    }
    @Command(names = "",permission = "hubcore.example")
    public void commandWithArg(@Sender Player player, String arg){
        //player.sendMessage(arg);
    }
}
