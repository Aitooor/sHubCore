package online.starsmc.hubcore.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import online.starsmc.hubcore.utils.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.unnamed.inject.InjectAll;

@InjectAll
@Command(names = {"fly", "volar"}, permission = "hubcore.fly")
public class FlyCommand implements CommandClass {


    @Command(names = {""})
    public void mainCommand(@Sender CommandSender sender, String id){
        //TODO Need to create UserModel(with Json) and implement using it.
        if(!(sender instanceof Player)) {
            ChatUtil.sendMsgSender(sender, "&cThis command only can execute in game");
            return;
        }

        Player player = (Player) sender;

        if(player.isFlying()) {
            player.setFlying(false);
            ChatUtil.sendMsgPlayerPrefix(player, "&cFly desactivado");
            return;
        }

        player.setFlying(true);
        ChatUtil.sendMsgPlayerPrefix(player, "&aFly activado");
    }
}
