package main.cityGenerator;

import main.cityGenerator.generator.CityChunkGenerator;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class CityGenerator extends JavaPlugin {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        loadAllSchematic();
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new CityChunkGenerator();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length > 0) {
            WorldCreator worldCreator = new WorldCreator(args[0]);
            worldCreator.generator(new CityChunkGenerator());
            worldCreator.createWorld();
        }
        return true;
    }

    private void loadAllSchematic(){
        /*
        Test Code
         */
        Bukkit.getServer().getLogger().info(plugin.getDataFolder().toString());
        Bukkit.getServer().getLogger().info(plugin.getDataFolder().getName());

        File fileList = new File(plugin.getDataFolder().toString());

        for(File f : fileList.listFiles()){
            if(f.isDirectory()){
                Bukkit.getServer().getLogger().info("資料夾 " + f.getName());
            }else{
                Bukkit.getServer().getLogger().info("檔案 " + f.getName());
            }
        }
    }
}
