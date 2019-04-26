package main.cityGenerator;

import main.cityGenerator.fileProcess.SchematicManager;
import main.cityGenerator.fileProcess.SchematicReader;
import main.cityGenerator.generator.IGeneratorInfo;

public class BuildDecide implements IGeneratorInfo {

    public static SchematicReader getBuildType(int x, int y, int z) {
        //Test Code
        if (x > 0 && z > 0)
            return SchematicManager.buildings2.get(Math.abs(x / (streetWidth + buildingWidth) + z / (streetWidth + buildingWidth)) % SchematicManager.buildings2.size());

        return SchematicManager.buildings.get(Math.abs(x / (streetWidth + buildingWidth) + z / (streetWidth + buildingWidth)) % SchematicManager.buildings.size());
    }

}
