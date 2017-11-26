package com.icemetalpunk.placeables;

import com.icemetalpunk.placeables.proxies.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Placeables.MODID, version = Placeables.VERSION)
public class Placeables {
	public static final String MODID = "placeables";
	public static final String VERSION = "1.0";

	@SidedProxy(clientSide = "com.icemetalpunk.placeables.proxies.ClientProxy", serverSide = "com.icemetalpunk.placeables.proxies.ServerProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void init(FMLInitializationEvent event) {
	}
}
