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
        int customX = x / (streetWidth + buildingWidth) - 50000;
        int customZ = z / (streetWidth + buildingWidth) + 50000;
        int customXm = x % (streetWidth + buildingWidth);
        int customZm = z % (streetWidth + buildingWidth);

        Integer index = Math.abs(reverseString(String.valueOf(customX)).hashCode() + new Double(customZ * 1.5).intValue()
        ) % SchematicManager.buildings.size();

        return SchematicManager.buildings.get(index);

    }

    private static String reverseString(String string) {
        String reverseString = "";
        for (int i = string.length() - 1; i >= 0; i--) {
            reverseString += string.charAt(i);
        }
        return reverseString;
    }

}
