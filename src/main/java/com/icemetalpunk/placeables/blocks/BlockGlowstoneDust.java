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

public class BlockGlowstoneDust extends BlockDustBase implements ITintedBlock {

	public BlockGlowstoneDust(String mod, String name) {
		super(mod, name);
		this.setNoItem().setLightLevel(1.0f);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.GLOWSTONE_DUST;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Items.GLOWSTONE_DUST);
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (rand.nextInt(100) <= 25) {
			double d0 = (double) pos.getX() + 0.5D + ((double) rand.nextFloat() - 0.5D) * 0.2D;
			double d1 = (double) ((float) pos.getY() + 0.0625F);
			double d2 = (double) pos.getZ() + 0.5D + ((double) rand.nextFloat() - 0.5D) * 0.2D;
			worldIn.spawnParticle(EnumParticleTypes.REDSTONE, d0, d1, d2, 1.0f, 1.0f, 0.0f);
		}
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
