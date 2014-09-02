package com.olee.forge.commons.utility;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class WorldUtilities {

	public static void replaceBlocks(int x, int y, int z, Block[][][] blocks, World world) {
		for (int ix = 0; ix < blocks.length; ix++) {
			Block[][] xblocks = blocks[ix];
			for (int iy = 0; iy < xblocks.length; iy++) {
				Block[] yblocks = xblocks[iy];
				for (int iz = 0; iz < yblocks.length; iz++) {
					Block block = world.getBlock(x + ix, y + iy, z + iz);
					if (yblocks[iz] != null)
						world.setBlock(x + ix, y + iy, z + iz, yblocks[iz]);
					yblocks[iz] = block;
				}
			}
		}
	}

	public static int dropOnWorldTop(int x, int z, World world) {
		int y = world.getHeight() + 1;
		while (y > 0 && world.isAirBlock(x, y - 1, z))
			y--;
		if (y == 0)
			y = world.getHeight() + 1;
		return y;
	}

	public static int placeInWorld(int x, int y, int z, World world) {
		if (world.isAirBlock(x, y, z) && world.isAirBlock(x, y + 1, z))
			while (world.isAirBlock(x, y - 1, z) && y > 0)
				y--;
		else
			while (y < world.getHeight() + 2 && (!world.isAirBlock(x, y, z) || !world.isAirBlock(x, y + 1, z)))
				y++;
		if (y == 0)
			y = world.getHeight() + 1;
		return y;
	}

}
