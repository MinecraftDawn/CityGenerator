package main.cityGenerator;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CityChunkGenerator extends ChunkGenerator {

    int height = 60;
    int streetWidth = 20;
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
                //設置底層基岩
                chunk.setBlock(x, 0, z, Material.BEDROCK);
                for (int y = 1; y < height / 3; y++) {
                    //設置下層石頭
                    chunk.setBlock(x, y, z, Material.STONE);
                }
                for (int y = height / 3; y < height; y++) {
                    //設置上層泥土
                    chunk.setBlock(x, y, z, Material.DIRT);
                }

                int currentX = chunkX * 16 + x;
                int currentZ = chunkZ * 16 + z;

                int modX = Math.abs(currentX % (buildingWidth + streetWidth));
                int modZ = Math.abs(currentZ % (buildingWidth + streetWidth));

                if (modX < streetWidth || modZ < streetWidth) {
                    //設置道路煤炭磚
                    chunk.setBlock(x, height, z, Material.COAL_BLOCK);
                } else {
                    //設置建築基底石英磚
                    chunk.setBlock(x, height, z, Material.QUARTZ_BLOCK);

                    if (cc != null)
                        for (int y = 0; y < cc.getHeight(); y++) {
                            BaseBlock block = cc.getBlock(new Vector(modX - streetWidth, y, modZ - streetWidth));
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
