package com.icemetalpunk.placeables.registries;

import java.util.HashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.icemetalpunk.placeables.blocks.BasicBlock;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockRegistry {
	private HashMap<String, BasicBlock> registry = new HashMap<>();

	public BlockRegistry() {
		// TODO: "Register" blocks here.
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
			reg.register(block.getItemBlock());
		}
	}

	public void registerModels() {
		for (BasicBlock block : this.registry.values()) {
			block.registerModel();
		}
	}

}
