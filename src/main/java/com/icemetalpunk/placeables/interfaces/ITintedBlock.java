package com.icemetalpunk.placeables.interfaces;

import net.minecraft.client.renderer.color.BlockColors;

public interface ITintedBlock {
	default public void registerTintHandler(BlockColors colors) {
	}
}
