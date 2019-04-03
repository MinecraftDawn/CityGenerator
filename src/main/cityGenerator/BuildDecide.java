package main.cityGenerator;

import main.cityGenerator.generator.IGenerator;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator.BiomeGrid;
import org.bukkit.generator.ChunkGenerator.ChunkData;

import java.util.Random;

public class BuildDecide implements IGenerator {

    @Override
    public ChunkData generate(ChunkData chunk, World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        return null;
    }

    public int getBuildType(int x, int y, int z) {
        return (x + z) % 10;
    }

}
