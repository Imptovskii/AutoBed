package me.xavivi.autobed;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class  AutoBed extends JavaPlugin {
    public static AutoBed INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        if (!getDataFolder().exists()){
            getDataFolder().mkdir();
        }

        loadConfig("overworld.yml");
        loadConfig("nether.yml");

        getLogger().info("Plugin <AutoBed> has been enabled!");

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerSetSpawnListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin <AutoBed> has been disabled!");
    }

    private void loadConfig(String fileName){
        File configFile = new File(getDataFolder(), fileName);

        if (!configFile.exists()){
            saveResource(fileName, false);
        }
    }
}
