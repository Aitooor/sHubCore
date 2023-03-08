package online.starsmc.hubcore.listener;

import online.starsmc.hubcore.Main;
import online.starsmc.hubcore.user.manager.UserManager;
import online.starsmc.hubcore.utils.location.LocationCodec;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import team.unnamed.inject.InjectAll;

import java.util.Objects;

@InjectAll
public class PlayerListeners implements Listener {

    private Main plugin;
    private FileConfiguration config;
    private UserManager userManager;

    public PlayerListeners() {
        this.config = plugin.getConfig();
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage(null);
        player.teleport(Objects.requireNonNull(LocationCodec.deserialize(Objects.requireNonNull(config.getString("spawn_location")))));

        userManager.isFlying(player);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(player.getLocation().getY() < 0) {
            player.teleport(Objects.requireNonNull(LocationCodec.deserialize(Objects.requireNonNull(config.getString("spawn_location")))));
        }
    }

    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent event) {
        Entity player = event.getEntity();

        if(player instanceof Player) {
            event.setCancelled(true);
            event.setFoodLevel(20);
        }
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event) {
        Entity player = event.getEntity();

        if(player instanceof Player) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onWorldLoadEvent(WorldLoadEvent event) {
        World world = event.getWorld();

        world.setTime(3600);
        world.setStorm(false);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.FALL_DAMAGE, false);
        world.setSpawnLimit(SpawnCategory.AMBIENT, 0);
        world.setSpawnLimit(SpawnCategory.ANIMAL, 0);
        world.setSpawnLimit(SpawnCategory.MONSTER, 0);
        world.setSpawnLimit(SpawnCategory.MISC, 0);
        world.setSpawnLimit(SpawnCategory.AXOLOTL, 0);
        world.setSpawnLimit(SpawnCategory.WATER_AMBIENT, 0);
        world.setSpawnLimit(SpawnCategory.WATER_ANIMAL, 0);
        world.setSpawnLimit(SpawnCategory.WATER_UNDERGROUND_CREATURE, 0);
    }
}
