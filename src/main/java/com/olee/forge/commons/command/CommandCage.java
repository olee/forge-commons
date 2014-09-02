/**
 * 
 */
package com.olee.forge.commons.command;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;

import com.olee.forge.commons.utility.WorldUtilities;

/**
 * @author Björn Zeutzheim
 *
 */
public class CommandCage extends PlayerCommand {

	class Cage {
		String playerName;
		int x;
		int y;
		int z;
		Block[][][] blocks;
	}

	private static final int MIN_CAGE_HEIGHT = 72;
	private static final int MAX_CAGE_HEIGHT = 240;

	private Map<String, Cage> cages = new HashMap<String, Cage>();

	@Override
	public String getCommandName() {
		return "cage";
	}

	@Override
	public String getCommandUsage(ICommandSender commandSender) {
		return "Cage a player - usage: /" + this.getCommandName() + " [playerName]";
	}

	@Override
	public int getArgumentCount() {
		return 0;
	}

	@Override
	public int getOptionalArgumentCount() {
		return 0;
	}

	public Block[][][] generateCage() {
		Block[][][] cage = new Block[4][5][4];

		Block blockCage = Blocks.obsidian;
		Block blockGlass = Blocks.glass;
		Block blockAir = Blocks.air;

		cage[2][0][2] = blockCage;
		cage[1][0][2] = blockCage;
		cage[1][0][1] = blockCage;
		cage[2][0][1] = blockCage;

		cage[2][1][2] = blockAir;
		cage[1][1][2] = blockAir;
		cage[1][1][1] = blockAir;
		cage[2][1][1] = blockAir;

		cage[3][1][2] = blockCage;
		cage[3][1][1] = blockCage;
		cage[0][1][2] = blockCage;
		cage[0][1][1] = blockCage;
		cage[1][1][0] = blockCage;
		cage[2][1][0] = blockCage;
		cage[1][1][3] = blockCage;
		cage[2][1][3] = blockCage;

		cage[2][2][2] = blockAir;
		cage[1][2][2] = blockAir;
		cage[1][2][1] = blockAir;
		cage[2][2][1] = blockAir;

		cage[3][2][2] = blockGlass;
		cage[3][2][1] = blockGlass;
		cage[0][2][2] = blockGlass;
		cage[0][2][1] = blockGlass;
		cage[1][2][0] = blockGlass;
		cage[2][2][0] = blockGlass;
		cage[1][2][3] = blockGlass;
		cage[2][2][3] = blockGlass;

		cage[2][3][2] = blockAir;
		cage[1][3][2] = blockAir;
		cage[1][3][1] = blockAir;
		cage[2][3][1] = blockAir;

		cage[3][3][2] = blockCage;
		cage[3][3][1] = blockCage;
		cage[0][3][2] = blockCage;
		cage[0][3][1] = blockCage;
		cage[1][3][0] = blockCage;
		cage[2][3][0] = blockCage;
		cage[1][3][3] = blockCage;
		cage[2][3][3] = blockCage;

		cage[2][4][2] = blockCage;
		cage[1][4][2] = blockCage;
		cage[1][4][1] = blockCage;
		cage[2][4][1] = blockCage;

		return cage;
	}

	@Override
	public void processPlayerCommand(EntityPlayerMP player, String[] args) {
		Cage cage = cages.get(player.getCommandSenderName());
		if (cage == null) {
			cage = new Cage();
			cage.x = (int) player.posX - 2;
			cage.y = Math.max(MIN_CAGE_HEIGHT, Math.min(MAX_CAGE_HEIGHT, (int) player.posY + 28));
			cage.z = (int) player.posZ - 2;
			cage.blocks = generateCage();
			WorldUtilities.replaceBlocks(cage.x, cage.y, cage.z, cage.blocks, player.worldObj);
			cages.put(player.getCommandSenderName(), cage);

			player.setPositionAndUpdate(cage.x + 2, cage.y + 1, cage.z + 2);
			player.addChatMessage(new ChatComponentText("You have been caged!"));
		} else {
			WorldUtilities.replaceBlocks(cage.x, cage.y, cage.z, cage.blocks, player.worldObj);
			cages.remove(player.getCommandSenderName());

			player.addChatMessage(new ChatComponentText("You have been uncaged!"));
		}
	}

}
