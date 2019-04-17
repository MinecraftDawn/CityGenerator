package main.cityGenerator.generator;

import main.cityGenerator.BuildDecide;
import main.cityGenerator.fileProcess.SchematicManager;
import main.cityGenerator.fileProcess.SchematicReader;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class BuildingGenerator implements IGenerator {
    @Override
    public ChunkGenerator.ChunkData generate(ChunkGenerator.ChunkData chunk, World world, Random random, int chunkX, int chunkZ, ChunkGenerator.BiomeGrid biome) {

        SchematicReader scheReader = SchematicManager.buildings.get(BuildDecide.getBuildType(chunkX * 16, 1, chunkZ * 16));

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

                    if (scheSizeX > streetWidth + buildingWidth || scheSizeZ > streetWidth + buildingWidth) continue;
                    //若在Schematic範圍
                    if (modX - streetWidth >= buildStartX && modX - streetWidth < buildEndX
                            && modZ - streetWidth >= buildStartZ && modZ - streetWidth < buildEndZ) {

                        //判斷座標正負，決定是否要反轉讀取Schematic檔
                        int buildingX, buildingZ;
                        if (currentX >= 0) {
                            buildingX = (modX - streetWidth - buildStartX) % scheSizeX;
                        } else {
                            buildingX = scheSizeX - 1 - (modX - streetWidth - buildStartX) % scheSizeX;
                        }

                        if (currentZ > 0) {
                            buildingZ = (modZ - streetWidth - buildStartZ) % scheSizeZ;
                        } else {
                            buildingZ = scheSizeZ - 1 - (modZ - streetWidth - buildStartZ) % scheSizeZ;
                        }

                        //建造
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
