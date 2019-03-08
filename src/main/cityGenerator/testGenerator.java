package main.cityGenerator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Arrays;
import java.util.List;
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

        return chunk;
    }

//    @Override
//    public List<BlockPopulator> getDefaultPopulators(World world) {
//        return Arrays.asList((BlockPopulator) new testPopulator());
//    }
}
