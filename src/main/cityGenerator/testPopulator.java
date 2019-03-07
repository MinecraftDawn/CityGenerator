package main.cityGenerator;

import org.bukkit.*;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class testPopulator extends BlockPopulator {

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        if (random.nextBoolean()) {
            int amount = random.nextInt(4) + 1;  // Amount of trees
            for (int i = 1; i < amount; i++) {
                int X = random.nextInt(15);
                int Z = random.nextInt(15);
                int Y = 1;
                for (int j = world.getMaxHeight() - 1; chunk.getBlock(X, j, Z).getType() == Material.AIR; j--,Y=j);

                world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), TreeType.TREE);
            }
        }
    }
}
