package main.cityGenerator.fileProcess;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

import java.io.File;
import java.io.IOException;

public class SchematicFileLoader {

    private File schematic;
    private SchematicFormat format;
    private CuboidClipboard cc;

    public SchematicFileLoader(File file) {
        schematic = file;
        format = SchematicFormat.getFormat(schematic);

        try {
            cc = format.load(schematic);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataException e) {
            e.printStackTrace();
        }
    }

    private CuboidClipboard getCuboidClipboard() {
        return cc;
    }

    public SchematicReader getSchematicInfo() {
        SchematicReader schematicInfo = new SchematicReader(getCuboidClipboard());
        return schematicInfo;
    }
}
