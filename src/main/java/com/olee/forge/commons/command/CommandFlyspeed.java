/**
 * 
 */
package com.olee.forge.commons.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * @author Björn Zeutzheim
 */
public class CommandFlyspeed extends PlayerCommand {

	@Override
	public String getCommandName() {
		return "flyspeed";
	}

	@Override
	public String getCommandUsage(ICommandSender commandSender) {
		return "Set flyspeed";
	}

	@Override
	public int getArgumentCount() {
		return 1;
	}

	@Override
	public void processPlayerCommand(EntityPlayerMP player, String[] args) {
		if (player.capabilities.getFlySpeed() >= 0.5f)
			player.capabilities.setFlySpeed(0.5f);
		else
			player.capabilities.setFlySpeed(0.05f);
	}

}
