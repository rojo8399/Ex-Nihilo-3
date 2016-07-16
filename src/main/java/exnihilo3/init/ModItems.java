package exnihilo3.init;

import exnihilo3.items.crooks.ItemCrook;
import exnihilo3.items.materials.EN3ToolMaterials;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static Item crookWood;
	public static Item crookBone;
	
	public static void init() {
		crookWood = new ItemCrook(EN3ToolMaterials.Stick, "wood");
		crookBone = new ItemCrook(EN3ToolMaterials.Bone, "bone");
	}
	
	public static void register() {
		GameRegistry.register(crookWood);
		GameRegistry.register(crookBone);
	}
	
	public static void registerRenders () {
		registerRender(crookWood);
		registerRender(crookBone);
	}
	
	private static void registerRender(Item item) {
		System.out.println(item.getRegistryName() + " has been registered!");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
}
