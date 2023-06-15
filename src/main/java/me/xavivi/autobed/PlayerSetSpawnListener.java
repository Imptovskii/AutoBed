package me.xavivi.autobed;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;
import java.io.IOException;

public class PlayerSetSpawnListener implements Listener {
    @EventHandler
    private void onSetSpawn(PlayerBedEnterEvent event){
        Player player = event.getPlayer();

        if (!player.getWorld().isBedWorks()) return;

        File configFile = new File(AutoBed.INSTANCE.getDataFolder(), "overworld.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        Location bedLocation = event.getBed().getLocation();
        config.set(player.getUniqueId() + ".bedLocation", bedLocation);
        try {
            config.save(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @EventHandler
    private void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if (!player.getWorld().isRespawnAnchorWorks()) return;

        Block clickedBlock = event.getClickedBlock();

        if (clickedBlock != null && clickedBlock.getType() == Material.RESPAWN_ANCHOR){
            int chargedLevel = ((RespawnAnchor)(clickedBlock.getBlockData())).getCharges();

            if (chargedLevel > 0 && event.getItem() != null) {
                Material itemType = event.getItem().getType();
                Location clickedBlockLocation = event.getClickedBlock().getLocation();

                if (itemType != Material.GLOWSTONE || chargedLevel == 4) {
                    saveAnchorPosition(player, clickedBlockLocation);
                }
            }
        }
    }

    private void saveAnchorPosition(Player player, Location location){
        File configFile = new File(AutoBed.INSTANCE.getDataFolder(), "nether.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        config.set(player.getUniqueId() + ".anchorlocation", location);
        try {
            config.save(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
