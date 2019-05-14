package main.cityGenerator;

import main.cityGenerator.fileProcess.SchematicManager;
import main.cityGenerator.fileProcess.SchematicReader;
import main.cityGenerator.generator.IGeneratorInfo;

public class BuildDecide implements IGeneratorInfo {

    public static SchematicReader getBuildType(int x, int y, int z) {
        //Test Code
//        if (x > 0 && z > 0)
//            return SchematicManager.buildings2.get(Math.abs(x / (streetWidth + buildingWidth) + z / (streetWidth + buildingWidth)) % SchematicManager.buildings2.size());

//舊的回傳值，用來決定要用哪個Index的Schematic File
//        int customX = x / (streetWidth + buildingWidth);
//        int customZ = z / (streetWidth + buildingWidth);
//
//        Integer index = Math.abs(
//                customX + customZ) % SchematicManager.buildings.size();
//
//        Test Code 新的回傳值，用來決定要用哪個Index的Schematic File
        int customX = x / (streetWidth + buildingWidth) + 500000;
        int customZ = z / (streetWidth + buildingWidth) + 500000;
        int customXm = x % (streetWidth + buildingWidth);
        int customZm = z % (streetWidth + buildingWidth);

        Integer index = Math.abs(
                String.valueOf(customX + 5000000).hashCode() - customZ + customXm) % SchematicManager.buildings.size();

        return SchematicManager.buildings.get(index);

    }

}
