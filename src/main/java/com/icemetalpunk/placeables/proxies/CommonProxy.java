package com.icemetalpunk.placeables.proxies;

import com.icemetalpunk.placeables.registries.BlockRegistry;
import com.icemetalpunk.placeables.registries.ItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonProxy {

	public static BlockRegistry blocks = new BlockRegistry();
	public static ItemRegistry items = new ItemRegistry();

	public CommonProxy() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	public void preinit(FMLPreInitializationEvent ev) {

	}

	public void init(FMLInitializationEvent ev) {

	}

	public void postinit(FMLPostInitializationEvent ev) {

	}

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> ev) {
		this.blocks.register(ev.getRegistry());
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> ev) {
		this.items.register(ev.getRegistry());
		this.blocks.registerItemBlocks(ev.getRegistry());
	}
}
