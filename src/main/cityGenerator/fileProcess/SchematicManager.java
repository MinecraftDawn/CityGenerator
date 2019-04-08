package main.cityGenerator.fileProcess;

import main.cityGenerator.CityGenerator;

import java.io.File;

public class SchematicManager {

    public SchematicReader scheInfo;

    public SchematicManager(String fileName){
        File schematicFile = new File(CityGenerator.plugin.getDataFolder() + File.separator + fileName);
        SchematicFileLoader scheFileLoader = new SchematicFileLoader(schematicFile);

        scheInfo = scheFileLoader.getSchematicInfo();
    }
}
