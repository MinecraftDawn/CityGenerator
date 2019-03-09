package main.cityGenerator;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class testGenerator extends ChunkGenerator {

    int height = 60;
    int streeWidth = 7;
    int buildingWidth = 30;

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);
        File schematic = new File(CityGenerator.plugin.getDataFolder() + File.separator + "Block.schematic");
        SchematicFormat format = SchematicFormat.getFormat(schematic);
        CuboidClipboard cc = null;
        try {
            cc = format.load(schematic);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataException e) {
            e.printStackTrace();
        }

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

                int modX = Math.abs(currentX % buildingWidth);
                int modZ = Math.abs(currentZ % buildingWidth);

                if (modX < streeWidth || modZ < streeWidth) {
                    chunk.setBlock(x, height, z, Material.COAL_BLOCK);
                } else {
                    chunk.setBlock(x, height, z, Material.QUARTZ_BLOCK);

                    if (cc != null)
                        for (int y = 0; y < cc.getHeight(); y++) {
                            BaseBlock block = cc.getBlock(new Vector(modX - streeWidth, y, modZ - streeWidth));
                            Material material = Material.getMaterial(block.getId());
                            if (material != Material.AIR)
                                chunk.setBlock(x, y + height + 1, z, material.getId(), (byte) block.getData());
                        }
                }
            }
        }

        return chunk;

    }
}
