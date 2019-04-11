package main.cityGenerator.fileProcess;

import main.cityGenerator.CityGenerator;
import org.bukkit.Bukkit;

import java.io.File;
import java.util.LinkedList;

public abstract class SchematicManager {

    public static LinkedList<SchematicReader> buildings = new LinkedList<>();

    public static void loadAllSchematic() {
        buildings = new LinkedList<>();

        File fileList = new File(CityGenerator.plugin.getDataFolder().toString()
                + File.separator + "Building");

        for (File f : fileList.listFiles()) {
            if (f.isFile()) {
                SchematicFileLoader scheFileLoader = new SchematicFileLoader(f);

                SchematicReader scheInfo = scheFileLoader.getSchematicInfo().get();

                if (scheInfo != null) {
                    buildings.add(scheInfo);
                    Bukkit.getServer().getLogger().info("Schematic檔案讀取成功： " + f.getName());
                }

            }
        }
    }
}
