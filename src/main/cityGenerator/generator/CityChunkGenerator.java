package main.cityGenerator.generator;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class CityChunkGenerator extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);

        //生成地基
        chunk = new foundationGenerator().generate(chunk, world, random, chunkX, chunkZ, biome);
        //生成建築
        chunk = new BuildingGenerator().generate(chunk, world, random, chunkX, chunkZ, biome);

        return chunk;
    }


}
