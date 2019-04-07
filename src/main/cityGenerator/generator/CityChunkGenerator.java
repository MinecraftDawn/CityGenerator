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


        /*
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                //設置底層基岩
//                chunk.setBlock(x, 0, z, Material.BEDROCK);
                for (int y = 1; y < height / 3; y++) {
                    //設置下層石頭
//                    chunk.setBlock(x, y, z, Material.STONE);
                }
                for (int y = height / 3; y < height; y++) {
                    //設置上層泥土
//                    chunk.setBlock(x, y, z, Material.DIRT);
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

                    //生成建築物

                    for (int y = 0; y < scheReader.getSize().getY(); y++) {
                        int buildingX = (modX - streetWidth) % (int) scheReader.getSize().getX();
                        int buildingZ = (modZ - streetWidth) % (int) scheReader.getSize().getZ();

                        int BlockID = scheReader.getBlockID(buildingX, y, buildingZ);
                        byte BlockData = scheReader.getBlockData(buildingX, y, buildingZ);

                        chunk.setBlock(x, y + height + 1, z, BlockID, BlockData);
                    }

                }
            }
        }
        */

        return chunk;
    }


}
