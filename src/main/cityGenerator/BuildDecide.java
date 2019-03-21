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

        /*
        if (chunkX < 0 || chunkZ < 0) return chunk;

        for (int xx = 0; xx < 16; xx++) {
            for (int zz = 0; zz < 16; zz++) {
                int x = chunkX * 16 + xx;
                int z = chunkZ * 16 + zz;

//                if (x < 0 || z < 0) return chunk;

                //int quotientX = Math.abs(x / (streetWidth + buildingWidth));
                int X = x % (streetWidth + buildingWidth);
                //int quotientZ = Math.abs(z / (streetWidth + buildingWidth));
                int Z = z % (streetWidth + buildingWidth);

                //道路
                if (x < streetWidth) {

                    //建築
                } else {
                    int centerX = Math.abs(x) - X + buildingWidth / 2;
                    int startX = centerX - scheReader.getSize().getBlockX() / 2;
                    int endX = centerX + scheReader.getSize().getBlockX() / 2;
                    int centerZ = Math.abs(z) - Z + buildingWidth / 2;
                    int startZ = centerZ - scheReader.getSize().getBlockZ();
                    int endZ = centerZ + scheReader.getSize().getBlockZ() / 2;


                    if (x > startX && x < endX && z > startZ && z < endZ) {
                        for (int y = height + 2; y < height + 2 + scheReader.getSize().getBlockY(); y++) {
                            int BlockID = scheReader.getBlockID(x - startX, y, z - startZ);
                            byte BlockData = scheReader.getBlockData(x - startX, y, z - startZ);
                            chunk.setBlock(x%16, y, z%16, BlockID, BlockData);
                        }
                    }
                }


            }
        }
        */
        return chunk;
    }

    public void getBuildType(int x, int y, int z) {
        /*
        if (x < 0 || z < 0) return;

        //int quotientX = Math.abs(x / (streetWidth + buildingWidth));
        int X = Math.abs(x % (streetWidth + buildingWidth));
        //int quotientZ = Math.abs(z / (streetWidth + buildingWidth));
        int Z = Math.abs(z % (streetWidth + buildingWidth));

        //道路
        if (x < streetWidth) {

            //建築
        } else {
            int centerX = Math.abs(x) - X + buildingWidth / 2;
            int startX = centerX - scheReader.getSize().getBlockX() / 2;
            int endX = centerX + scheReader.getSize().getBlockX() / 2;
            int centerZ = Math.abs(z) - Z + buildingWidth / 2;
            int startZ = centerZ - scheReader.getSize().getBlockZ();
            int endXZ = centerZ + scheReader.getSize().getBlockZ() / 2;


            if (x > startX && x < endX && z > startX && z < startZ) {

            }

        }
        */
    }


}
