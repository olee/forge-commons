package com.olee.forge.commons;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;

@Mod(modid = CommonMod.MODID, version = CommonMod.VERSION)
public class CommonMod {

	// The instance of your mod that Forge uses.
	@Instance(value = CommonMod.MODID)
	public static CommonMod instance;

	public static final String MODID = "olee-commons";
	public static final String VERSION = "0.1";

	@SidedProxy(clientSide = "com.olee.forge.commons.client.ClientProxy", serverSide = "com.olee.forge.commons.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void initializationEvent(FMLInitializationEvent event) {
		proxy.initializationEvent(event);
	}

	@EventHandler
	public void postInitializationEvent(FMLPostInitializationEvent event) {
		proxy.postInitializationEvent(event);
	}

	@EventHandler
	public void loadCompleteEvent(FMLLoadCompleteEvent event) {
		proxy.loadCompleteEvent(event);
	}

	@EventHandler
	public void serverStartEvent(FMLServerStartingEvent event) {
		proxy.serverStartEvent(event);
	}

	@EventHandler
	public void serverStoppedEvent(FMLServerStoppedEvent event) {
		proxy.serverStoppedEvent(event);
	}

}
