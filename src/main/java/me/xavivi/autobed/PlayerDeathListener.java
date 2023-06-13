package me.xavivi.autobed;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    @EventHandler
    private void onPlayerDeath(PlayerDeathEvent event){
        Player player = event.getEntity();

        Location bedLocation = getBedLocation(player);
        if (bedLocation != null) {
            player.setBedSpawnLocation(bedLocation);
        }
    }

    private Location getBedLocation(Player player) {
        String key = player.getUniqueId() + ".bedLocation";
        if (AutoBed.INSTANCE.config.isSet(key)) {
            Location bedLocation = (Location) AutoBed.INSTANCE.config.get(key);
            return bedLocation;
        }
        return null;
    }
}
