package main.cityGenerator;

import main.cityGenerator.fileProcess.SchematicManager;
import main.cityGenerator.fileProcess.SchematicReader;
import main.cityGenerator.generator.IGeneratorInfo;

public class BuildDecide2 implements IGeneratorInfo {

    public static SchematicReader getBuildType(int x, int y, int z) {
        int customX = x / (streetWidth + buildingWidth) - 50000;
        int customZ = z / (streetWidth + buildingWidth) + 50000;

        Integer index = Math.abs(reverseString(String.valueOf(customX)).hashCode() + new Double(customZ * 1.5).intValue()
        ) % SchematicManager.buildings.size();

        return SchematicManager.buildings.get(index);

    }

    public static int getBuildTypeIndex(int x, int y, int z) {
        int customX = x / (streetWidth + buildingWidth) - 50000;
        int customZ = z / (streetWidth + buildingWidth) + 50000;

        Integer index = Math.abs(reverseString(String.valueOf(customX)).hashCode() + new Double(customZ * 1.5).intValue()
        ) % SchematicManager.buildings.size();

        return index;

    }

    private static String reverseString(String string) {
        String reverseString = "";
        for (int i = string.length() - 1; i >= 0; i--) {
            reverseString += string.charAt(i);
        }
        return reverseString;
    }

}
