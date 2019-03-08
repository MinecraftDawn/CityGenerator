package main.cityGenerator;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.SchematicFormat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;
import java.util.Random;

public class testGenerator extends ChunkGenerator {

    int height = 60;
    int streeWidth = 7;
    int buildingWidth = 30;

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                chunk.setBlock(x, 0, z, Material.BEDROCK);
                for (int y = 1; y < height / 3; y++) {
                    chunk.setBlock(x, y, z, Material.STONE);
                }
                for (int y = height / 3; y < height; y++) {
                    chunk.setBlock(x, y, z, Material.DIRT);
                }

                int currentX = chunkX * 16 + x;
                int currentZ = chunkZ * 16 + z;
                if (Math.abs(currentX % buildingWidth) < streeWidth || Math.abs(currentZ % buildingWidth) < streeWidth) {
                    chunk.setBlock(x, height, z, Material.COAL_BLOCK);
                } else {
                    chunk.setBlock(x, height, z, Material.QUARTZ_BLOCK);
                }
            }
        }


        Location location = new Location(world, chunkX * 16, height, chunkZ * 16);

        WorldEditPlugin worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");

        File schematic = new File(CityGenerator.plugin.getDataFolder() + File.separator + "Block.schematic");

        EditSession session = worldEditPlugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(world), 5000);

        try {
            SchematicFormat format = SchematicFormat.getFormat(schematic);
            CuboidClipboard cc = format.load(schematic);
            BaseBlock block = cc.getBlock(new Vector(0,0,0));
            chunk.setBlock(0,height,0,Material.getMaterial(block.getId()));
        } catch (Exception e) {
            Bukkit.getServer().getLogger().info("發生錯誤");
        }


        return chunk;

    }
}
