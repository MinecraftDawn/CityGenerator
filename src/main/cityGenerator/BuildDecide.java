package main.cityGenerator;

import main.cityGenerator.generator.IGenerator;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator.*;

import java.util.Random;

public class BuildDecide implements IGenerator {

    @Override
    public ChunkData generate(ChunkData chunk, World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        return chunk;
    }

    public int getBuildType(int chunkX, int chunkZ) {

        return 0;
    }


}
