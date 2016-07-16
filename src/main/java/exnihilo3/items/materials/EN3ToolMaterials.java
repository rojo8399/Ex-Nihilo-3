package exnihilo3.items.materials;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class EN3ToolMaterials {
	public static ToolMaterial Stick;
	public static ToolMaterial Bone;
	
	public static void configure() {
		Stick = EnumHelper.addToolMaterial("Stick", 0, 59, 2.0F, 0.0F, 15);
		Stick.setRepairItem(new ItemStack(Items.STICK, 0));
		
		Bone = EnumHelper.addToolMaterial("Bone", 1, 131, 4.0F, 1.0F, 5);
		Bone.setRepairItem(new ItemStack(Items.DYE, 1, 15)); //bone meal
	}
	
	
}
