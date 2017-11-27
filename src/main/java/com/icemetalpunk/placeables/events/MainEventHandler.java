package com.icemetalpunk.placeables.events;

import com.icemetalpunk.placeables.Placeables;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MainEventHandler {
	public MainEventHandler() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void placeDust(PlayerInteractEvent.RightClickBlock ev) {
		ItemStack stack = ev.getItemStack();
		EnumFacing face = ev.getFace();
		EntityPlayer player = ev.getEntityPlayer();
		World world = ev.getWorld();
		BlockPos pos = ev.getPos();
		if (!world.getBlockState(pos).getBlock().isReplaceable(world, pos)) {
			pos = pos.offset(face);
		}

		// Place glowstone dust
		if (stack.getItem() == Items.GLOWSTONE_DUST) {
			Block dustBlock = Placeables.proxy.blocks.get("block_glowstone_dust");
			if (dustBlock.canPlaceBlockAt(world, pos)) {
				if (!player.isCreative()) {
					stack.shrink(1);
				}
				world.setBlockState(pos, dustBlock.getDefaultState());
				// dustBlock.onBlockAdded(world, pos,
				// dustBlock.getDefaultState());
			}
		}

	}
}
