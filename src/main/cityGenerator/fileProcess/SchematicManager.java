package main.cityGenerator.fileProcess;

import main.cityGenerator.CityGenerator;
import main.cityGenerator.generator.IGeneratorInfo;
import org.bukkit.Bukkit;

import java.io.File;
import java.util.LinkedList;

public abstract class SchematicManager implements IGeneratorInfo {

    public static LinkedList<SchematicReader> buildings = new LinkedList<>();
    public static LinkedList<SchematicReader> buildings2 = new LinkedList<>();
    private static SchematicReader air = null;

    public static void loadAllSchematic() {
        air = new SchematicFileLoader(
                new File(CityGenerator.plugin.getDataFolder().toString()
                        + File.separator + "Air.schematic")).getSchematicInfo().get();

        File fileList = new File(CityGenerator.plugin.getDataFolder().toString()
                + File.separator + "Building");

        for (File f : fileList.listFiles()) {
            if (f.isFile()) {
                SchematicFileLoader scheFileLoader = new SchematicFileLoader(f);

                SchematicReader scheInfo = scheFileLoader.getSchematicInfo().orElse(air);

                if (scheInfo != null) {

                    if (scheInfo.getSize().getBlockX() < buildingWidth / 4 &&
                            scheInfo.getSize().getBlockZ() < buildingWidth / 4) {
                        buildings2.add(scheInfo);

                    } else {
                        buildings.add(scheInfo);
                    }

                    Bukkit.getServer().getLogger().info("Schematic檔案讀取成功： " + f.getName());
                }
            }
        }
    }
}
