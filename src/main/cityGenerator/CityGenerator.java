package main.cityGenerator;

import main.cityGenerator.fileProcess.SchematicManager;
import main.cityGenerator.generator.CityChunkGenerator;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.LinkedList;

public class CityGenerator extends JavaPlugin {

    public static Plugin plugin;
    public static LinkedList<SchematicManager> buildings;

    @Override
    public void onEnable() {
        plugin = this;
        buildings = new LinkedList<>();
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

    private void loadAllSchematic() {
        File fileList = new File(plugin.getDataFolder().toString() + File.separator + "Building");

        for (File f : fileList.listFiles()) {
            if (f.isFile()) {
                Bukkit.getServer().getLogger().info("檔案 " + f.getName());
                buildings.add(new SchematicManager(f.getName()));
            }
        }
    }
}
