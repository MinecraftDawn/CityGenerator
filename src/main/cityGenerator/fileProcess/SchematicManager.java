package main.cityGenerator.fileProcess;

import main.cityGenerator.CityGenerator;

import java.io.File;

public class SchematicManager {

    public SchematicReader scheInfo;

    public SchematicManager(File schematicFile){
//        File schematicFile = new File(CityGenerator.plugin.getDataFolder()
//                + File.separator + "Building" + File.separator  +fileName);
        SchematicFileLoader scheFileLoader = new SchematicFileLoader(schematicFile);

        scheInfo = scheFileLoader.getSchematicInfo();
    }
}
