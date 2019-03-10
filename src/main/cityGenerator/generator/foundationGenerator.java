package main.cityGenerator.generator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator.*;

import java.util.Random;

public class foundationGenerator implements IGenerator {

    @Override
    public ChunkData generate(ChunkData chunk, World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        //設置底層基岩
        chunk.setRegion(0, 0, 0, 16, 1, 16, Material.BEDROCK);
        //設置下層石頭
        chunk.setRegion(0, 1, 0, 16, height / 3, 16, Material.STONE);
        //設置上層泥土
        chunk.setRegion(0, height / 3, 0, 16, height, 16, Material.DIRT);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
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
                }
            }
        }



        return chunk;
    }
}
