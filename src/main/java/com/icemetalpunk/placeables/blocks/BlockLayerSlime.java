package com.icemetalpunk.placeables.blocks;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLayerSlime extends BlockLayerBase {

	public BlockLayerSlime(String mod, String name) {
		super(mod, name);
		this.setNoItem().setHardness(0.0f);
		this.slipperiness = 0.8f;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.SLIME_BALL;
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (Math.abs(entityIn.motionY) < 0.1D && !entityIn.isSneaking()) {
			double d0 = 0.4D + Math.abs(entityIn.motionY) * 0.2D;
			entityIn.motionX *= d0;
			entityIn.motionZ *= d0;
		}

		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Items.SLIME_BALL);
	}

}
