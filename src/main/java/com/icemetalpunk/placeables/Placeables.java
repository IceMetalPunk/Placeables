/* TODO: Next
 * 1. Placeable sugar: attract tameable animals
 * 2. Placeable leaf: decraft 1 leaves block into 9 leafs, placeable in 8 rotations on floor as decoration
 * 3. Placeable stone: decraft 1 cobblestone into 9 stones, placeable on floor as decoration, throwable for mild damage
 * 4. Dyes directly placeable onto blocks with color properties (handle bone meal appropriately!)
 * 5. Placeable ghast tears (trails)?: Maybe makes things slippery and "guides/pushes" entities along, like a conveyor?
 * 6. Placeable blaze powder: Acts like magma blocks with "hot floor" damage? How can we make this more unique?
 * 7. Placeable Nether star?: Floats above ground like item without bob; cures negative potions in radius?
 */

package com.icemetalpunk.placeables;

import com.icemetalpunk.placeables.events.MainEventHandler;
import com.icemetalpunk.placeables.events.SecondaryEventHandler;
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
	public static SecondaryEventHandler secondaryEventHandler = new SecondaryEventHandler();

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
