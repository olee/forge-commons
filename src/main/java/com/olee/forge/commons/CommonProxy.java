package com.olee.forge.commons;

import com.olee.forge.commons.command.CommandCage;
import com.olee.forge.fmw.core.MultiworldManager;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;

public class CommonProxy {

	public void initializationEvent(FMLInitializationEvent event) {
	}

	public void serverStartEvent(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandCage());
	}

	public void postInitializationEvent(FMLPostInitializationEvent event) {
	}

	public void loadCompleteEvent(FMLLoadCompleteEvent event) {
	}

	public void serverStoppedEvent(FMLServerStoppedEvent event) {
	}

}
