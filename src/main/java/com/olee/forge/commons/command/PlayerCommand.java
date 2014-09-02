/**
 * 
 */
package com.olee.forge.commons.command;

import java.util.Arrays;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

/**
 * @author Björn
 *
 */
public abstract class PlayerCommand extends CommandBase {

	@Override
	public void processCommand(ICommandSender commandSender, String[] args) {
		if (commandSender instanceof EntityPlayerMP) {
			if (args.length < getArgumentCount()) {
				commandSender.addChatMessage(new ChatComponentText("Too few arguments!"));
			} else if (args.length <= getArgumentCount()) {
				processPlayerCommand((EntityPlayerMP) commandSender, args);
			} else if (args.length <= getArgumentCount() + getOptionalArgumentCount()) {
				EntityPlayerMP player = PlayerSelector.matchOnePlayer(commandSender, args[0]);
				if (player != null) {
					processPlayerCommand(player, Arrays.copyOfRange(args, 1, args.length));
				} else {
					processPlayerCommand((EntityPlayerMP) commandSender, args);
				}
			} else if (args.length <= 1 + getArgumentCount() + getOptionalArgumentCount()) {
				EntityPlayerMP player = PlayerSelector.matchOnePlayer(commandSender, args[0]);
				if (player != null) {
					processPlayerCommand(player, Arrays.copyOfRange(args, 1, args.length));
				} else {
					commandSender.addChatMessage(new ChatComponentText("Player not found!"));
				}
			} else {
				commandSender.addChatMessage(new ChatComponentText("Too many arguments!"));
			}
		} else {
			commandSender.addChatMessage(new ChatComponentText("Server-console command handling not implemented yet!"));
		}
		/*
		 * Example:
		 * argCount = 1
		 * argOpt = 1
		 * 
		 * /tp TARGET
		 * 
		 * /tp TARGET FAST
		 * /tp PLAYERNAME TARGET
		 * 
		 * /tp PLAYERNAME TARGET FAST
		 */
	}

	public abstract void processPlayerCommand(EntityPlayerMP player, String[] args);

	public int getArgumentCount() {
		return 0;
	}

	public int getOptionalArgumentCount() {
		return 0;
	}

}
