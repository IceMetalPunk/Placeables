/* TODO: Next
 * 1. Placeable slime balls: slow-down on any block with slime layer
 * 2. Placeable sugar: attract tameable animals
 * 3. Placeable leaf: decraft 1 leaves block into 9 leafs, placeable in 8 rotations on floor as decoration
 * 4. Placeable stone: decraft 1 cobblestone into 9 stones, placeable on floor as decoration, throwable for mild damage
 * 5. Dyes directly placeable onto blocks with color properties (handle bone meal appropriately!)
 * 6. Placeable ghast tears (trails)?: Maybe makes things slippery and "guides/pushes" entities along, like a conveyor?
 * 7. Placeable blaze powder: Acts like magma blocks with "hot floor" damage? How can we make this more unique?
 * 8. Placeable magma cream?: Layers like slime balls, instant fire extinguishing. Placeable in Nether!
 * 9. Placeable Nether star?: Floats above ground like item without bob; cures negative potions in radius?
 */

package com.icemetalpunk.placeables;

import com.icemetalpunk.placeables.events.MainEventHandler;
import com.icemetalpunk.placeables.proxies.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Placeables.MODID, version = Placeables.VERSION)
public class Placeables {
	public static final String MODID = "placeables";
	public static final String VERSION = "1.0";

	@SidedProxy(clientSide = "com.icemetalpunk.placeables.proxies.ClientProxy", serverSide = "com.icemetalpunk.placeables.proxies.ServerProxy")
	public static CommonProxy proxy;

	public static MainEventHandler eventHandler = new MainEventHandler();

	public static CreativeTabs tab = new CreativeTabs("placeables") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Items.GLOWSTONE_DUST);
		}

	};

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		proxy.preinit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		proxy.postinit(event);
	}
}
