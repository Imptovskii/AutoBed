package me.xavivi.autobed;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.File;

public class PlayerDeathListener implements Listener {
    @EventHandler
    private void onPlayerDeath(PlayerDeathEvent event){
        Player player = event.getEntity();

        if (player.getWorld().isBedWorks()){
            Location spawnLocation = getSpawnLocation(player, "overworld.yml");
            if (spawnLocation != null) {
                player.setBedSpawnLocation(spawnLocation);
            }
        }
        else if (player.getWorld().isRespawnAnchorWorks()){
            Location spawnLocation = getSpawnLocation(player, "nether.yml");
            if (spawnLocation != null) {
                player.setBedSpawnLocation(spawnLocation);
            }
        }
    }

    private Location getSpawnLocation(Player player, String fileName) {
        String key = player.getUniqueId() + (fileName.equals("overworld.yml") ? ".bedLocation" : ".anchorlocation");

        File configFile = new File(AutoBed.INSTANCE.getDataFolder(), fileName);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        if (config.isSet(key)) {
            Location spawnLocation = (Location)config.get(key);
            return spawnLocation;
        }
        return null;
    }
}
