package com.icemetalpunk.placeables.blocks;

import com.icemetalpunk.placeables.interfaces.IModeledObject;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class BasicBlock extends Block implements IModeledObject {

	private ItemBlock itemBlock = new ItemBlock(this);

	public BasicBlock(String mod, String name, Material materialIn) {
		super(materialIn);
		this.setRegistryName(new ResourceLocation(mod, name)).setUnlocalizedName(name);
	}

	public BasicBlock(String mod, String name, Material materialIn, CreativeTabs tab) {
		this(mod, name, materialIn);
		this.itemBlock.setCreativeTab(tab);
	}

	public ItemBlock getItemBlock() {
		return this.itemBlock;
	}

	@Override
	public void registerModel() {
		ModelResourceLocation model = new ModelResourceLocation(this.getRegistryName(), "inventory");
		ModelLoader.registerItemVariants(this.itemBlock, model);
		ModelLoader.setCustomModelResourceLocation(this.itemBlock, 0, model);
	}

}
