package com.icemetalpunk.placeables.blocks;

import java.util.Random;

import com.icemetalpunk.placeables.interfaces.ITintedBlock;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlowstoneDust extends BlockDustBase implements ITintedBlock {

	public BlockGlowstoneDust(String mod, String name) {
		super(mod, name);
		this.setNoItem().setLightLevel(1.0f);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.GLOWSTONE_DUST;
	}

	@SideOnly(value = Side.CLIENT)
	@Override
	public void registerTintHandler(BlockColors colors) {
		System.out.println("Registering Block Colors for glowstone");
		colors.registerBlockColorHandler(new IBlockColor() {

			@Override
			public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
				int r = 255;
				int g = 255;
				int b = 0;
				return -16777216 | r << 16 | g << 8 | b;
			}

		}, this);
	}

}
