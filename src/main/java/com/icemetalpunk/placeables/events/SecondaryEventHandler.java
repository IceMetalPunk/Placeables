package com.icemetalpunk.placeables.events;

import com.icemetalpunk.placeables.ai.EntityAIFindSugar;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SecondaryEventHandler {
	public SecondaryEventHandler() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void setAI(EntityJoinWorldEvent ev) {
		World world = ev.getWorld();
		Entity ent = ev.getEntity();

		if (ent instanceof EntityCreature) {
			EntityCreature creature = (EntityCreature) ent;
			creature.tasks.addTask(1, new EntityAIFindSugar(creature, 1.0d));
		}

	}
}
