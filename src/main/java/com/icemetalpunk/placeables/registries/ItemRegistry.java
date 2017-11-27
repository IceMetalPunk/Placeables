package com.icemetalpunk.placeables.registries;

import java.util.HashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.icemetalpunk.placeables.items.BasicItem;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemRegistry {
	private HashMap<String, BasicItem> registry = new HashMap<>();

	public ItemRegistry() {
		// TODO: "Register" items here.
	}

	@Nullable
	public BasicItem put(@Nonnull String name, @Nonnull BasicItem item) {
		return this.registry.put(name, item);
	}

	@Nullable
	public BasicItem get(String name) {
		return registry.get(name);
	}

	public void register(IForgeRegistry<Item> reg) {
		for (BasicItem item : this.registry.values()) {
			reg.register(item);
		}
	}

	public void registerModels() {
		for (BasicItem item : this.registry.values()) {
			item.registerModel();
		}
	}

}
