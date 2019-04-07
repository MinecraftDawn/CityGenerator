package main.cityGenerator.generator;

import main.cityGenerator.CityGenerator;

import java.io.File;

public class buildingInfo {
    private File schematicFile;

    public buildingInfo(String fileName){
        schematicFile = new File(CityGenerator.plugin.getDataFolder() + File.separator + fileName);
    }
}
