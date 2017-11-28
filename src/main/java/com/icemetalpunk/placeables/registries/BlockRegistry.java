package com.icemetalpunk.placeables.registries;

import java.util.HashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.icemetalpunk.placeables.Placeables;
import com.icemetalpunk.placeables.blocks.BasicBlock;
import com.icemetalpunk.placeables.blocks.BlockGlowstoneDust;
import com.icemetalpunk.placeables.blocks.BlockGunpowder;
import com.icemetalpunk.placeables.blocks.BlockLayerSlime;
import com.icemetalpunk.placeables.interfaces.ITintedBlock;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockRegistry {
	private HashMap<String, BasicBlock> registry = new HashMap<>();

	public BlockRegistry() {
		this.registry.put("block_glowstone_dust", new BlockGlowstoneDust(Placeables.MODID, "block_glowstone_dust"));
		this.registry.put("block_gunpowder", new BlockGunpowder(Placeables.MODID, "block_gunpowder"));
		this.registry.put("block_slime_layer", new BlockLayerSlime(Placeables.MODID, "block_slime_layer"));
	}

	@Nullable
	public BasicBlock put(@Nonnull String name, @Nonnull BasicBlock block) {
		return this.registry.put(name, block);
	}

	@Nullable
	public BasicBlock get(String name) {
		return registry.get(name);
	}

	public void register(IForgeRegistry<Block> reg) {
		for (BasicBlock block : this.registry.values()) {
			reg.register(block);
		}
	}

	public void registerItemBlocks(IForgeRegistry<Item> reg) {
		for (BasicBlock block : this.registry.values()) {
			Item itemBlock = block.getItemBlock();
			if (itemBlock != null) {
				reg.register(itemBlock);
			}
		}
	}

	public void registerModels() {
		for (BasicBlock block : this.registry.values()) {
			block.registerModel();
		}
	}

	public void registerTints(BlockColors colors) {
		for (BasicBlock block : this.registry.values()) {
			if (block instanceof ITintedBlock) {
				ITintedBlock tinted = (ITintedBlock) block;
				tinted.registerTintHandler(colors);
			}
		}
	}

}
