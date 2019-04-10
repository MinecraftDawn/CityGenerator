package main.cityGenerator;

import main.cityGenerator.fileProcess.SchematicManager;
import main.cityGenerator.generator.IGeneratorInfo;

public class BuildDecide implements IGeneratorInfo {

    public static int getBuildType(int x, int y, int z) {
        return Math.abs(x / (streetWidth + buildingWidth) + z / (streetWidth + buildingWidth)) % SchematicManager.buildings.size();
    }

}
