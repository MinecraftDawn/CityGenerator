package main.cityGenerator.fileProcess;

import main.cityGenerator.CityGenerator;
import org.bukkit.Bukkit;

import java.io.File;
import java.util.LinkedList;

public abstract class SchematicManager {

    public static LinkedList<SchematicReader> buildings = new LinkedList<>();
    private static SchematicReader air = null;

    public static void loadAllSchematic() {
        air = new SchematicFileLoader(
                new File(CityGenerator.plugin.getDataFolder().toString()
                        + File.separator + "Air.schematic")).getSchematicInfo().get();

        buildings = new LinkedList<>();

        File fileList = new File(CityGenerator.plugin.getDataFolder().toString()
                + File.separator + "Building");

        for (File f : fileList.listFiles()) {
            if (f.isFile()) {
                SchematicFileLoader scheFileLoader = new SchematicFileLoader(f);

                SchematicReader scheInfo = scheFileLoader.getSchematicInfo().orElse(air);

                if (scheInfo != null) {
                    buildings.add(scheInfo);
                    Bukkit.getServer().getLogger().info("Schematic檔案讀取成功： " + f.getName());
                }
            }
        }
    }
}
