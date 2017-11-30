package com.icemetalpunk.placeables.blocks;

import java.util.Random;

import com.icemetalpunk.placeables.interfaces.ITintedBlock;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSugarDust extends BlockDustBase implements ITintedBlock {

	public BlockSugarDust(String mod, String name) {
		super(mod, name);
		this.setNoItem();
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.SUGAR;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Items.SUGAR);
	}

	@SideOnly(value = Side.CLIENT)
	@Override
	public void registerTintHandler(BlockColors colors) {
		colors.registerBlockColorHandler(new IBlockColor() {

			@Override
			public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
				int r = 255;
				int g = 255;
				int b = 255;
				return -16777216 | r << 16 | g << 8 | b;
			}

		}, this);
	}

}
