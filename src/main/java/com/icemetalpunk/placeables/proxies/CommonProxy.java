package com.icemetalpunk.placeables.proxies;

import com.icemetalpunk.placeables.registries.BlockRegistry;
import com.icemetalpunk.placeables.registries.ItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonProxy {

	public static BlockRegistry blocks = new BlockRegistry();
	public static ItemRegistry items = new ItemRegistry();

	public CommonProxy() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	public void preinit() {

	}

	public void init() {

	}

	public void postinit() {

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
