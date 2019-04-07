package main.cityGenerator;

import main.cityGenerator.generator.IGeneratorInfo;

public class BuildDecide implements IGeneratorInfo {

    public static int getBuildType(int x, int y, int z) {
        return (x / (streetWidth + buildingWidth) + z / (streetWidth + buildingWidth)) % 2;
    }

}
