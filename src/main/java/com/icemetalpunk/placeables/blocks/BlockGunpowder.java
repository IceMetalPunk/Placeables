package com.icemetalpunk.placeables.blocks;

import java.util.Random;

import com.icemetalpunk.placeables.interfaces.ITintedBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGunpowder extends BlockDustBase implements ITintedBlock {

	public BlockGunpowder(String mod, String name) {
		super(mod, name);
		this.setNoItem();
	}

	@Override
	public int tickRate(World world) {
		return 2;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			outerLoop:
			for (int x = -1; x <= 1; ++x) {
				for (int z = -1; z <= 1; ++z) {
					for (int y = -1; y <= 1; ++y) {
						Block check = worldIn.getBlockState(pos.add(x, y, z)).getBlock();
						if (check instanceof BlockFire || check == Blocks.LAVA || check == Blocks.FLOWING_LAVA) {
							worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState());
							break outerLoop;
						}
					}
				}
			}
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.GUNPOWDER;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Items.GUNPOWDER);
	}

	@SideOnly(value = Side.CLIENT)
	@Override
	public void registerTintHandler(BlockColors colors) {
		colors.registerBlockColorHandler(new IBlockColor() {

			@Override
			public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
				int r = 50;
				int g = 50;
				int b = 50;
				return -16777216 | r << 16 | g << 8 | b;
			}

		}, this);
	}

}
