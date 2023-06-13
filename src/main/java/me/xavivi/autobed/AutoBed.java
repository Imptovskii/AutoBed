package me.xavivi.autobed;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class AutoBed extends JavaPlugin {
    public static AutoBed INSTANCE;

    public FileConfiguration config;


    @Override
    public void onEnable() {
        INSTANCE = this;

        saveDefaultConfig();
        config = getConfig();

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerSetSpawnListener(), this);
    }

    @Override
    public void onDisable() {
    }
}
