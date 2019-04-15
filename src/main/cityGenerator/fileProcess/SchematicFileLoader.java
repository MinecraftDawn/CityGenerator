package main.cityGenerator.fileProcess;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class SchematicFileLoader {

    private File schematic;
    private SchematicFormat format;
    private CuboidClipboard cc;

    public SchematicFileLoader(File file) {
        schematic = file;
        try {
            format = SchematicFormat.getFormat(schematic);

            cc = format.load(schematic);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Schematic檔案格式錯誤");
            cc = null;
        }
    }

    private CuboidClipboard getCuboidClipboard() {
        return cc;
    }

    public Optional<SchematicReader> getSchematicInfo() {
        SchematicReader schematicInfo = null;
        if(getCuboidClipboard() != null)
            schematicInfo = new SchematicReader(getCuboidClipboard());
        return Optional.ofNullable(schematicInfo);
    }
}
