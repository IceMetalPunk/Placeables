package com.icemetalpunk.placeables.ai;

import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;

import com.icemetalpunk.placeables.Placeables;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityAIFindSugar extends EntityAIBase {

	private EntityCreature entity;
	private World world;
	private double speed = 0.5D;
	private BlockPos sugarPos;

	public EntityAIFindSugar(EntityCreature ent, double spd) {
		this.entity = ent;
		this.speed = spd;
		this.world = ent.getEntityWorld();
	}

	@Override
	public boolean shouldExecute() {
		BlockPos origin = this.entity.getPosition();
		Block block;
		HashSet<BlockPos> visited = new HashSet<>();
		ArrayBlockingQueue<BlockPos> nextUp = new ArrayBlockingQueue<>(121);
		nextUp.offer(origin);
		visited.add(origin);

		while (!nextUp.isEmpty() && visited.size() < 250) {
			BlockPos current = nextUp.poll();
			if (current == null) {
				break;
			}

			if (this.world.isBlockLoaded(current) && current.getY() >= 0 && current.getY() < this.world.getHeight()) {
				block = this.world.getBlockState(current).getBlock();
				if (block == Placeables.proxy.blocks.get("block_sugar_dust")) {
					this.sugarPos = current;
					return true;
				}

				BlockPos plusX = current.add(1, 0, 0);
				BlockPos plusZ = current.add(0, 0, 1);
				BlockPos minusX = current.add(-1, 0, 0);
				BlockPos minusZ = current.add(0, 0, -1);

				if (visited.add(plusX)) {
					nextUp.offer(plusX);
				}

				if (visited.add(plusZ)) {
					nextUp.offer(plusZ);
				}
				if (visited.add(minusX)) {
					nextUp.offer(minusX);
				}
				if (visited.add(minusZ)) {
					nextUp.offer(minusZ);
				}

				if (current.getY() >= origin.getY()) {
					BlockPos minusY = current.add(0, -1, 0);
					if (visited.add(minusY)) {
						nextUp.offer(minusY);
					}
				}

				if (current.getY() <= origin.getY()) {
					BlockPos plusY = current.add(0, 1, 0);
					if (visited.add(plusY)) {
						nextUp.offer(plusY);
					}
				}

			}

		}
		return false;
	}

	@Override
	public void startExecuting() {
		this.entity.getNavigator().tryMoveToXYZ(this.sugarPos.getX() + 0.5, this.sugarPos.getY(),
				this.sugarPos.getZ() + 0.5, this.speed);
	}

	@Override
	public boolean shouldContinueExecuting() {
		Vec3d eyePos = this.entity.getPositionEyes(1.0f);
		BlockPos origin = this.entity.getPosition();
		BlockPos pos = new BlockPos(eyePos.x, origin.getY(), eyePos.z);
		Block block = this.world.getBlockState(pos).getBlock();
		if (block == Placeables.proxy.blocks.get("block_sugar_dust")) {
			this.entity.getLookHelper().setLookPositionWithEntity(this.entity, 0.0f, 40.0f);
			this.world.setBlockToAir(pos);
			return false;
		}
		return !this.entity.getNavigator().noPath();
	}

}
