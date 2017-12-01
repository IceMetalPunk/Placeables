package com.icemetalpunk.placeables.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockLeaf extends BasicBlock {

	public BlockLeaf(String mod, String name) {
		super(mod, name, Material.LEAVES);
		this.setHardness(0.1f);
	}

	public BlockLeaf(String mod, String name, CreativeTabs tab) {
		this(mod, name);
		this.setCreativeTab(tab);
		this.getItemBlock().setCreativeTab(tab);
	}

}
