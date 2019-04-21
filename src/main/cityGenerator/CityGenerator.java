package main.cityGenerator;

import main.cityGenerator.fileProcess.SchematicManager;
import main.cityGenerator.generator.CityChunkGenerator;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public class CityGenerator extends JavaPlugin {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        /*
        File f = new File(getClass().getResource("/main/resource/Air.schematic").getFile());
        File out = new File(getDataFolder().toString()
                + File.separator + "Test.aaa");
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(f);
            outputStream = new FileOutputStream(out);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(f.getPath());
        System.out.println(f.getName());
        */

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
