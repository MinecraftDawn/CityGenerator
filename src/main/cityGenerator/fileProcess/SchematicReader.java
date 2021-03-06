package main.cityGenerator.fileProcess;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.blocks.BaseBlock;
import main.cityGenerator.generator.IGeneratorInfo;

public class SchematicReader implements IGeneratorInfo {
    private int[][][] blockID;
    private byte[][][] blockData;
    private Vector size;
    private Vector BlockSize;

    public SchematicReader(CuboidClipboard cc) {
        int sizeX = cc.getSize().getBlockX();
        int sizeY = cc.getSize().getBlockY();
        int sizeZ = cc.getSize().getBlockZ();

        blockID = new int[sizeX][sizeY][sizeZ];
        blockData = new byte[sizeX][sizeY][sizeZ];
        size = new Vector(sizeX, sizeY, sizeZ);
        BlockSize = new Vector(sizeX / cubeSize + 1, sizeY / cubeSize + 1, sizeZ / cubeSize + 1);

        for (int x = 0; x < sizeX; x++)
            for (int y = 0; y < sizeY; y++)
                for (int z = 0; z < sizeZ; z++) {
                    BaseBlock baseBlock = cc.getBlock(new Vector(x, y, z));
                    blockID[x][y][z] = baseBlock.getId();
                    blockData[x][y][z] = (byte) baseBlock.getData();
                }
    }

    public int getBlockID(int x, int y, int z) {
        return blockID[x][y][z];
    }

    public byte getBlockData(int x, int y, int z) {
        return blockData[x][y][z];
    }

    public Vector getSize() {
        return size;
    }

    public Vector getXZ() { return BlockSize; }

}
