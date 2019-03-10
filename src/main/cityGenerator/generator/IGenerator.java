package main.cityGenerator.generator;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.ChunkGenerator.ChunkData;

import java.util.Random;

public interface IGenerator {
    int height = 60;
    int streetWidth = 20;
    int buildingWidth = 50;
    public ChunkData generate(ChunkData chunk, World world, Random random, int chunkX, int chunkZ, ChunkGenerator.BiomeGrid biome);
}
