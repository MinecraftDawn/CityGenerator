package main.cityGenerator.fileProcess;

import java.io.File;

public class SchematicManager {

    public SchematicReader scheInfo;

    public SchematicManager(File schematicFile) {
        SchematicFileLoader scheFileLoader = new SchematicFileLoader(schematicFile);

        scheInfo = scheFileLoader.getSchematicInfo();
    }
}
