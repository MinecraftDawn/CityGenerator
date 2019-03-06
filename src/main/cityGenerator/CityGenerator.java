package main.cityGenerator;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class CityGenerator extends JavaPlugin{

    @Override
    public void onEnable(){

    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new testGenerator();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length > 0) {
            WorldCreator worldCreator = new WorldCreator(args[0]);
            worldCreator.generator(new testGenerator());
            worldCreator.createWorld();
        }
        return true;
    }
}
