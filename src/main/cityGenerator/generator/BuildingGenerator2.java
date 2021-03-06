package main.cityGenerator.generator;

import main.cityGenerator.BuildDecide2;
import main.cityGenerator.CityGenerator;
import main.cityGenerator.fileProcess.SchematicReader;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class BuildingGenerator2 implements IGenerator {
    @Override
    public ChunkGenerator.ChunkData generate(ChunkGenerator.ChunkData chunk, World world, Random random, int chunkX, int chunkZ, ChunkGenerator.BiomeGrid biome) {

        SchematicReader scheReader = BuildDecide2.getBuildType(chunkX * 16, 1, chunkZ * 16);
        int scheIndex = BuildDecide2.getBuildTypeIndex(chunkX * 16, 1, chunkZ * 16);
        int scheSizeX = scheReader.getSize().getBlockX();
        int scheSizeZ = scheReader.getSize().getBlockZ();

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {

                int preSizeX = 0;
                int sumSizeX = scheSizeX + streetWidth;

                if (Math.abs(chunkX * 16 + x) % (streetWidth + buildingWidth) > sumSizeX) {
                    scheReader = BuildDecide2.getBuildType(chunkX * 16 +  1000, 1, chunkZ * 16);
                    preSizeX += scheSizeX;
                    scheSizeX = scheReader.getSize().getBlockX();
                    scheSizeZ = scheReader.getSize().getBlockZ();
                    sumSizeX += scheSizeX;
                }

                if (Math.abs(chunkX * 16 + x) % (streetWidth + buildingWidth) > sumSizeX) {
                    continue;
                }

//                while(chunkX * 16 + x > sumSizeX){
//                    scheReader = BuildDecide2.getBuildType(chunkX * 16 + sumSizeX * 50, 1, chunkZ * 16);
//                    scheSizeX = scheReader.getSize().getBlockX();
//                    scheSizeZ = scheReader.getSize().getBlockZ();
//                    preSizeX += scheSizeX;
//                    sumSizeX += scheSizeX;
//                }

                int buildStartX = preSizeX;
                int buildStartZ = (int) Math.ceil((double) scheSizeZ / 2);
                int buildEndX = (int) Math.floor((double) scheSizeX / 2);
                int buildEndZ = (int) Math.floor((double) scheSizeZ / 2);

//                int sumSizeX = scheSizeX;
//                // 如果目前座標大於sumSize，代表須使用下一個Building
//                int a = Math.abs((chunkX * 16) % (streetWidth + buildingWidth)) % 16 ;
//                while (Math.abs((chunkX * 16 + x) % (streetWidth + buildingWidth)) - a * 16 > sumSizeX) {
//                    scheReader = BuildDecide2.getBuildType(chunkX * 16 + sumSizeX * 50, 1, chunkZ * 16);
//                    scheSizeX = scheReader.getSize().getBlockX();
//                    scheSizeZ = scheReader.getSize().getBlockZ();
//                    sumSizeX += scheSizeX;
//                }
//
//                int buildStartX = (int) Math.ceil((double) scheSizeX / 2);
//                int buildStartZ = (int) Math.ceil((double) scheSizeZ / 2);
//                int buildEndX = (int) Math.floor((double) scheSizeX / 2);
//                int buildEndZ = (int) Math.floor((double) scheSizeZ / 2);

                int currentX = chunkX * 16 + x;
                int currentZ = chunkZ * 16 + z;

                //計算目前座標轉換成自定方塊網格後的X與Z
                int modX = Math.abs(currentX % (buildingWidth + streetWidth));
                int modZ = Math.abs(currentZ % (buildingWidth + streetWidth));

                if (modX < streetWidth || modZ < streetWidth) {
                } else {//若X與Z同時不在街道範圍

                    //若在Schematic範圍
                    if (scheSizeX > buildingWidth || scheSizeZ > buildingWidth) continue;

                    if (modX - streetWidth >= buildStartX && sumSizeX < (buildingWidth + streetWidth)
                            && modZ - streetWidth >= buildStartZ) {

                        //判斷座標正負，決定是否要反轉讀取Schematic檔
                        int buildingX, buildingZ;
                        if (currentX >= 0) {
                            buildingX = (modX - streetWidth - buildStartX) % scheSizeX;
                        } else {
                            buildingX = scheSizeX - 1 - (modX - streetWidth - buildStartX) % scheSizeX;
                        }
                        if (currentZ >= 0) {
                            buildingZ = (modZ - streetWidth - buildStartZ) % scheSizeZ;
                        } else {
                            buildingZ = scheSizeZ - 1 - (modZ - streetWidth - buildStartZ) % scheSizeZ;
                        }

                        if ((modX - streetWidth - buildStartX) / scheSizeX > 0) continue;

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
