package com.icemetalpunk.placeables.items;

import com.icemetalpunk.placeables.Placeables;
import com.icemetalpunk.placeables.blocks.BlockLeaf;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemLeaf extends BasicItem {

	public ItemLeaf(String mod, String name) {
		super(mod, name);
	}

	public ItemLeaf(String mod, String name, Block block, CreativeTabs tab) {
		this(mod, name);
		this.setCreativeTab(tab);
	}

	/*
	 * Mostly copypasta from the sign placement, with some edits. Hopefully
	 * should work?
	 */
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		boolean flag = iblockstate.getBlock().isReplaceable(worldIn, pos);
		Block leafBlock = Placeables.proxy.blocks.get("block_leaf");
		if ((iblockstate.getMaterial().isSolid() || flag) && (!flag || facing == EnumFacing.UP)) {
			pos = pos.offset(facing);
			ItemStack itemstack = player.getHeldItem(hand);

			if (player.canPlayerEdit(pos, facing, itemstack) && leafBlock.canPlaceBlockAt(worldIn, pos)) {
				if (worldIn.isRemote) {
					return EnumActionResult.SUCCESS;
				} else {
					pos = flag ? pos.offset(facing.getOpposite()) : pos;

					int i = MathHelper.floor((double) ((player.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5D) & 15;
					worldIn.setBlockState(pos,
							leafBlock.getDefaultState().withProperty(BlockLeaf.ROTATION, Integer.valueOf(i)), 11);

					if (player instanceof EntityPlayerMP) {
						CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, pos, itemstack);
					}

					itemstack.shrink(1);
					return EnumActionResult.SUCCESS;
				}
			} else {
				return EnumActionResult.FAIL;
			}
		} else {
			return EnumActionResult.FAIL;
		}
	}

}
