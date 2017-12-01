package com.icemetalpunk.placeables.items;

import com.icemetalpunk.placeables.interfaces.IModeledObject;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class BasicItemBlock extends ItemBlock implements IModeledObject {
	public BasicItemBlock(String mod, String name, Block block) {
		super(block);
		this.setRegistryName(new ResourceLocation(mod, name)).setUnlocalizedName(name);
	}

	public BasicItemBlock(String mod, String name, Block block, CreativeTabs tab) {
		this(mod, name, block);
		this.setCreativeTab(tab);
	}

	@Override
	public void registerModel() {
		ModelResourceLocation model = new ModelResourceLocation(this.getRegistryName(), "inventory");
		ModelLoader.registerItemVariants(this, model);
		ModelLoader.setCustomModelResourceLocation(this, 0, model);
	}
}
