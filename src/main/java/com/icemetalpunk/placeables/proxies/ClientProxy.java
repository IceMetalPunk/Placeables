package com.icemetalpunk.placeables.proxies;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent ev) {
		this.blocks.registerModels();
		this.items.registerModels();
	}

	@Override
	public void init(FMLInitializationEvent ev) {
		super.init(ev);
		this.blocks.registerTints(Minecraft.getMinecraft().getBlockColors());
	}
}
