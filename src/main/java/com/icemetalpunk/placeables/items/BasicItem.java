package com.icemetalpunk.placeables.items;

import com.icemetalpunk.placeables.interfaces.IModeledObject;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class BasicItem extends Item implements IModeledObject {
	public BasicItem(String mod, String name) {
		super();
		this.setRegistryName(new ResourceLocation(mod, name)).setUnlocalizedName(name);
	}

	public BasicItem(String mod, String name, CreativeTabs tab) {
		this(mod, name);
		this.setCreativeTab(tab);
	}

	@Override
	public void registerModel() {
		ModelResourceLocation model = new ModelResourceLocation(this.getRegistryName(), "inventory");
		ModelLoader.registerItemVariants(this, model);
		ModelLoader.setCustomModelResourceLocation(this, 0, model);
	}
}
