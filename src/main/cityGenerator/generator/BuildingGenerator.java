package main.cityGenerator.generator;

import main.cityGenerator.CityGenerator;
import main.cityGenerator.SchematicFileLoader;
import main.cityGenerator.SchematicReader;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;
import java.util.Random;

public class BuildingGenerator implements IGenerator {
    @Override
    public ChunkGenerator.ChunkData generate(ChunkGenerator.ChunkData chunk, World world, Random random, int chunkX, int chunkZ, ChunkGenerator.BiomeGrid biome) {
        File schematicFile = new File(CityGenerator.plugin.getDataFolder() + File.separator + "3.schematic");
        SchematicFileLoader scheFileLoader = new SchematicFileLoader(schematicFile);
        SchematicReader scheReader = scheFileLoader.getSchematicInfo();

        int scheSizeX = scheReader.getSize().getBlockX();
        int scheSizeZ = scheReader.getSize().getBlockZ();

        int buildStartX = (buildingWidth - scheSizeX) / 2;
        int buildStartZ = (buildingWidth - scheSizeZ) / 2;
        int buildEndX = (buildingWidth + scheSizeX) / 2;
        int buildEndZ = (buildingWidth + scheSizeZ) / 2;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int currentX = chunkX * 16 + x;
                int currentZ = chunkZ * 16 + z;

                //計算目前座標轉換成自定方塊網格後的X與Z
                int modX = Math.abs(currentX % (buildingWidth + streetWidth));
                int modZ = Math.abs(currentZ % (buildingWidth + streetWidth));

                if (modX < streetWidth || modZ < streetWidth) {
                } else {//若X與Z同時不在街道範圍

                    //若在Schematic範圍
                    if (modX - streetWidth > buildStartX && modX - streetWidth < buildEndX
                            && modZ - streetWidth > buildStartZ && modZ - streetWidth < buildEndZ) {

                        int buildingX = (modX - streetWidth - buildStartX) % scheSizeX;
                        int buildingZ = (modZ - streetWidth - buildStartZ) % scheSizeZ;

                        for (int y = 0; y < scheReader.getSize().getBlockY(); y++) {

                            int BlockID = scheReader.getBlockID(buildingX, y, buildingZ);
                            byte BlockData = scheReader.getBlockData(buildingX, y, buildingZ);

                            chunk.setBlock(x, y + height + 2, z, BlockID, BlockData);
                        }
                    }
                }
            }

        }
        return chunk;
    }
}
