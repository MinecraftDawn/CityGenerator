package main.cityGenerator;

import main.cityGenerator.generator.IGenerator;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator.BiomeGrid;
import org.bukkit.generator.ChunkGenerator.ChunkData;

import java.io.File;
import java.util.Random;

public class BuildDecide implements IGenerator {

    @Override
    public ChunkData generate(ChunkData chunk, World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        File schematicFile = new File(CityGenerator.plugin.getDataFolder() + File.separator + "3.schematic");
        SchematicFileLoader scheFileLoader = new SchematicFileLoader(schematicFile);
        SchematicReader scheReader = scheFileLoader.getSchematicInfo();

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int currentX = chunkX * 16 + x;
                int currentZ = chunkZ * 16 + z;

                int modX = Math.abs(currentX % (buildingWidth + streetWidth));
                int modZ = Math.abs(currentZ % (buildingWidth + streetWidth));

                if (modX < streetWidth || modZ < streetWidth) {
                    //設置道路煤炭磚
                    //chunk.setBlock(x, height, z, Material.COAL_BLOCK);
                } else {
                    //設置建築基底石英磚
                    //chunk.setBlock(x, height, z, Material.QUARTZ_BLOCK);

                    for (int y = 0; y < scheReader.getSize().getBlockY(); y++) {
                        int buildingX = (modX - streetWidth) % scheReader.getSize().getBlockX();
                        int buildingZ = (modZ - streetWidth) % scheReader.getSize().getBlockZ();

                        int BlockID = scheReader.getBlockID(buildingX, y, buildingZ);
                        byte BlockData = scheReader.getBlockData(buildingX, y, buildingZ);

                        chunk.setBlock(x, y + height + 2, z, BlockID, BlockData);
                    }
                }
            }

        }
        return chunk;
    }

    public void getBuildType(int x, int y, int z) {

    }

}
