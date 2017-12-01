package com.icemetalpunk.placeables.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDustBase extends BasicBlock {
	public static final PropertyEnum<BlockDustBase.EnumAttachPosition> NORTH = PropertyEnum.<BlockDustBase
			.EnumAttachPosition> create("north", BlockDustBase.EnumAttachPosition.class);
	public static final PropertyEnum<BlockDustBase.EnumAttachPosition> EAST = PropertyEnum.<BlockDustBase
			.EnumAttachPosition> create("east", BlockDustBase.EnumAttachPosition.class);
	public static final PropertyEnum<BlockDustBase.EnumAttachPosition> SOUTH = PropertyEnum.<BlockDustBase
			.EnumAttachPosition> create("south", BlockDustBase.EnumAttachPosition.class);
	public static final PropertyEnum<BlockDustBase.EnumAttachPosition> WEST = PropertyEnum.<BlockDustBase
			.EnumAttachPosition> create("west", BlockDustBase.EnumAttachPosition.class);
	protected static final AxisAlignedBB[] BLOCK_DUST_AABB = new AxisAlignedBB[] {
			new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 0.8125D),
			new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 0.8125D),
			new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 1.0D),
			new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 0.8125D, 0.0625D, 0.8125D),
			new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 0.8125D, 0.0625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.8125D, 0.0625D, 0.8125D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.8125D, 0.0625D, 1.0D),
			new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 1.0D, 0.0625D, 0.8125D),
			new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 1.0D, 0.0625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 1.0D, 0.0625D, 0.8125D),
			new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 1.0D, 0.0625D, 1.0D),
			new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 1.0D, 0.0625D, 0.8125D),
			new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 0.8125D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D) };

	private void initStates() {
		this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, BlockDustBase.EnumAttachPosition.NONE)
				.withProperty(EAST, BlockDustBase.EnumAttachPosition.NONE)
				.withProperty(SOUTH, BlockDustBase.EnumAttachPosition.NONE)
				.withProperty(WEST, BlockDustBase.EnumAttachPosition.NONE));
	}

	public BlockDustBase(String mod, String name, Material materialIn) {
		super(mod, name, materialIn);
		this.initStates();
	}

	public BlockDustBase(String mod, String name, Material materialIn, CreativeTabs tab) {
		super(mod, name, materialIn, tab);
		this.initStates();
	}

	public BlockDustBase(String mod, String name) {
		this(mod, name, Material.CIRCUITS);
	}

	public BlockDustBase(String mod, String name, CreativeTabs tab) {
		this(mod, name, Material.CIRCUITS, tab);
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BLOCK_DUST_AABB[getAABBIndex(state.getActualState(source, pos))];
	}

	private static int getAABBIndex(IBlockState state) {
		int i = 0;
		boolean flag = state.getValue(NORTH) != BlockDustBase.EnumAttachPosition.NONE;
		boolean flag1 = state.getValue(EAST) != BlockDustBase.EnumAttachPosition.NONE;
		boolean flag2 = state.getValue(SOUTH) != BlockDustBase.EnumAttachPosition.NONE;
		boolean flag3 = state.getValue(WEST) != BlockDustBase.EnumAttachPosition.NONE;

		if (flag || flag2 && !flag && !flag1 && !flag3) {
			i |= 1 << EnumFacing.NORTH.getHorizontalIndex();
		}

		if (flag1 || flag3 && !flag && !flag1 && !flag2) {
			i |= 1 << EnumFacing.EAST.getHorizontalIndex();
		}

		if (flag2 || flag && !flag1 && !flag2 && !flag3) {
			i |= 1 << EnumFacing.SOUTH.getHorizontalIndex();
		}

		if (flag3 || flag1 && !flag && !flag2 && !flag3) {
			i |= 1 << EnumFacing.WEST.getHorizontalIndex();
		}

		return i;
	}

	/**
	 * Get the actual Block state of this Block at the given position. This
	 * applies properties not visible in the metadata, such as fence
	 * connections.
	 */
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		state = state.withProperty(WEST, this.getAttachPosition(worldIn, pos, EnumFacing.WEST));
		state = state.withProperty(EAST, this.getAttachPosition(worldIn, pos, EnumFacing.EAST));
		state = state.withProperty(NORTH, this.getAttachPosition(worldIn, pos, EnumFacing.NORTH));
		state = state.withProperty(SOUTH, this.getAttachPosition(worldIn, pos, EnumFacing.SOUTH));
		return state;
	}

	private BlockDustBase.EnumAttachPosition getAttachPosition(IBlockAccess worldIn, BlockPos pos,
			EnumFacing direction) {
		BlockPos blockpos = pos.offset(direction);
		IBlockState iblockstate = worldIn.getBlockState(pos.offset(direction));

		if (!canConnectTo(worldIn.getBlockState(blockpos), direction, worldIn, blockpos)
				&& (iblockstate.isNormalCube() || !canConnectUpwardsTo(worldIn, blockpos.down()))) {
			IBlockState iblockstate1 = worldIn.getBlockState(pos.up());

			if (!iblockstate1.isNormalCube()) {
				boolean flag = worldIn.getBlockState(blockpos).isSideSolid(worldIn, blockpos, EnumFacing.UP)
						|| worldIn.getBlockState(blockpos).getBlock() == Blocks.GLOWSTONE;

				if (flag && canConnectUpwardsTo(worldIn, blockpos.up())) {
					if (iblockstate.isBlockNormalCube()) {
						return BlockDustBase.EnumAttachPosition.UP;
					}

					return BlockDustBase.EnumAttachPosition.SIDE;
				}
			}

			return BlockDustBase.EnumAttachPosition.NONE;
		} else {
			return BlockDustBase.EnumAttachPosition.SIDE;
		}
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks
	 * for render
	 */
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	/**
	 * Checks if this block can be placed exactly at the given position.
	 */
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.down()).isTopSolid()
				|| worldIn.getBlockState(pos.down()).getBlock() == Blocks.GLOWSTONE;
	}

	/**
	 * Calls World.notifyNeighborsOfStateChange() for all neighboring blocks,
	 * but only if the given block is a redstone wire.
	 */
	private void notifyWireNeighborsOfStateChange(World worldIn, BlockPos pos) {
		if (worldIn.getBlockState(pos).getBlock() == this) {
			worldIn.notifyNeighborsOfStateChange(pos, this, false);

			for (EnumFacing enumfacing : EnumFacing.values()) {
				worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this, false);
			}
		}
	}

	/**
	 * Called after the block is set in the Chunk data, but before the Tile
	 * Entity is set
	 */
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {

			for (EnumFacing enumfacing : EnumFacing.Plane.VERTICAL) {
				worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this, false);
			}

			for (EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL) {
				this.notifyWireNeighborsOfStateChange(worldIn, pos.offset(enumfacing1));
			}
		}
	}

	/**
	 * Called serverside after this block is replaced with another in Chunk, but
	 * before the Tile Entity is updated
	 */
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		super.breakBlock(worldIn, pos, state);

		if (!worldIn.isRemote) {
			for (EnumFacing enumfacing : EnumFacing.values()) {
				worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this, false);
			}
		}
	}

	/**
	 * Called when a neighboring block was changed and marks that this state
	 * should perform any checks during a neighbor change. Cases may include
	 * when redstone power is updated, cactus blocks popping off due to a
	 * neighboring solid block, etc.
	 */
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!worldIn.isRemote) {
			if (!this.canPlaceBlockAt(worldIn, pos)) {
				this.dropBlockAsItem(worldIn, pos, state, 0);
				worldIn.setBlockToAir(pos);
			}
		}
	}

	/**
	 * Get the Item that this Block should drop when harvested.
	 */
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	protected boolean canConnectUpwardsTo(IBlockAccess worldIn, BlockPos pos) {
		return canConnectTo(worldIn.getBlockState(pos), null, worldIn, pos);
	}

	protected boolean canConnectTo(IBlockState blockState, @Nullable EnumFacing side, IBlockAccess world,
			BlockPos pos) {
		Block block = blockState.getBlock();
		return block == this;
	}

	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(this);
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState();
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	/**
	 * Returns the blockstate with the given rotation from the passed
	 * blockstate. If inapplicable, returns the passed blockstate.
	 */
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		switch (rot) {
		case CLOCKWISE_180:
			return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(EAST, state.getValue(WEST))
					.withProperty(SOUTH, state.getValue(NORTH)).withProperty(WEST, state.getValue(EAST));
		case COUNTERCLOCKWISE_90:
			return state.withProperty(NORTH, state.getValue(EAST)).withProperty(EAST, state.getValue(SOUTH))
					.withProperty(SOUTH, state.getValue(WEST)).withProperty(WEST, state.getValue(NORTH));
		case CLOCKWISE_90:
			return state.withProperty(NORTH, state.getValue(WEST)).withProperty(EAST, state.getValue(NORTH))
					.withProperty(SOUTH, state.getValue(EAST)).withProperty(WEST, state.getValue(SOUTH));
		default:
			return state;
		}
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 */
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		switch (mirrorIn) {
		case LEFT_RIGHT:
			return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(NORTH));
		case FRONT_BACK:
			return state.withProperty(EAST, state.getValue(WEST)).withProperty(WEST, state.getValue(EAST));
		default:
			return super.withMirror(state, mirrorIn);
		}
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { NORTH, EAST, SOUTH, WEST });
	}

	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return true;
	}

	static enum EnumAttachPosition implements IStringSerializable {
		UP("up"), SIDE("side"), NONE("none");

		private final String name;

		private EnumAttachPosition(String name) {
			this.name = name;
		}

		public String toString() {
			return this.getName();
		}

		public String getName() {
			return this.name;
		}
	}
}