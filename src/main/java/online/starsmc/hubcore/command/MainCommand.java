package online.starsmc.hubcore.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import online.starsmc.hubcore.utils.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.unnamed.inject.InjectAll;

@InjectAll
@Command(names = {"hubcore", "sHubCore", "sHubcore", "shubcore", "shubCore"})
public class MainCommand implements CommandClass {
    @Command(names = {"reload", "rl"}, permission = "hubcore.reload")
    public void reloadCommand(@Sender CommandSender sender){
        if(!(sender instanceof Player)) {
            ChatUtil.sendMsgSenderPrefix(sender, "&cNeed to implement");
            return;
        }
        ChatUtil.sendMsgSenderPrefix(sender, "&cNot done yet");
    }
    @Command(names = "setspawn", permission = "hubcore.setspawn")
    public void setSpawn(@Sender Player player, String arg){
        ChatUtil.sendMsgSender(player, "Not done yet");
    }
}
