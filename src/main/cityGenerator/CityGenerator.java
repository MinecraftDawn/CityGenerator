package main.cityGenerator;

import main.cityGenerator.fileProcess.SchematicManager;
import main.cityGenerator.generator.CityChunkGenerator;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


public class CityGenerator extends JavaPlugin {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        //從Jar檔內複製檔案出來
        InputStream src = getClass().getResourceAsStream("/main/resource/Air.schematic");
        Path dest = new File(getDataFolder().toString()
                + File.separator + "Air.schematic").toPath();
        try {
            Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
        }



        SchematicManager.loadAllSchematic();

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

}
