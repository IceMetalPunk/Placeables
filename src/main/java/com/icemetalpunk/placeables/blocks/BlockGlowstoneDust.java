package com.icemetalpunk.placeables.blocks;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockGlowstoneDust extends BlockDustBase {

	public BlockGlowstoneDust(String mod, String name) {
		super(mod, name);
		this.setNoItem().setLightLevel(0.666667f);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.GLOWSTONE_DUST;
	}

}
