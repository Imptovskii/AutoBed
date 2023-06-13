package me.xavivi.autobed;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class PlayerSetSpawnListener implements Listener {
    @EventHandler
    private void onSetSpawn(PlayerBedEnterEvent event){
        Player player = event.getPlayer();
        Location bedLocation = event.getBed().getLocation();
        AutoBed.INSTANCE.config.set(player.getUniqueId() + ".bedLocation", bedLocation);
        AutoBed.INSTANCE.saveConfig();
    }
}
