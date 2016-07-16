package exnihilo3.items.crooks;

import java.util.Set;

import com.google.common.collect.Sets;

import exnihilo3.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

public class ItemCrook extends Item {
	public static final double pullingForce = 1.5d;
	public static final double pushingForce = 1.5d;
	
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] {Blocks.LEAVES});
	
	private final Item.ToolMaterial ToolMaterial;
	
	public ItemCrook(ToolMaterial material, String type) {
		this.ToolMaterial = material;
		setMaxStackSize(1);
		
		if (type == "wood") {
			setUnlocalizedName(Reference.EN3Items.CROOKWOOD.getUnlocalizedName());
			setRegistryName(Reference.EN3Items.CROOKWOOD.getRegistryName());
			setMaxDamage(118);
		} else if (type == "bone") {
			setUnlocalizedName(Reference.EN3Items.CROOKBONE.getUnlocalizedName());
			setRegistryName(Reference.EN3Items.CROOKBONE.getRegistryName());
			setMaxDamage(407);
		}
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack item, EntityPlayer playerIn, Entity entity) {
		if (!playerIn.worldObj.isRemote) {
			double distance = Math.sqrt(Math.pow(playerIn.posX - entity.posX, 2) + Math.pow(playerIn.posZ - entity.posZ, 2));
			
			double scalarX = (playerIn.posX - entity.posX) / distance;
			double scalarZ = (playerIn.posZ - entity.posZ) / distance;
			
			double velX = 0 - scalarX * pushingForce;
			double velY = 0;
			double velZ = 0 - scalarZ * pushingForce;
			
			if (playerIn.posY < entity.posY) {
				velY = 0.5d;
			}
			
			entity.addVelocity(velX, velY, velZ);
		}
		
		System.out.println("Left Click Entity: " + entity.getDisplayName());
		
		//Disable Damage
		item.damageItem(1, playerIn);
		return true;
	}
	
	public boolean itemInteractionForEntity(ItemStack item, EntityPlayer playerIn, EntityLivingBase entity) {
		if (!playerIn.worldObj.isRemote) {
			double distance = Math.sqrt(Math.pow(playerIn.posX - entity.posX, 2) + Math.pow(playerIn.posZ - entity.posZ, 2));
			
			double scalarX = (playerIn.posX - entity.posX) / distance;
			double scalarZ = (playerIn.posZ - entity.posZ) / distance;

			double velX = scalarX * pullingForce;
			double velY = 0;
			double velZ = scalarZ * pullingForce;

			if (playerIn.posY > entity.posY)
				velY = 0.5d;

			entity.addVelocity(velX, velY, velZ);
		}
		
		System.out.println("Interact with entity: " + entity.getDisplayName());
		
		item.damageItem(1, playerIn);
		return true;
	}
	
	//Check whether this Item can harvest the given Block
    public boolean canHarvestBlock(IBlockState blockIn)
    {
        Block block = blockIn.getBlock();
        return block == Blocks.LEAVES;
    }
	
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
        return state.getMaterial() != Material.LEAVES ? super.getStrVsBlock(stack, state) : 5.0F;
    }
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, net.minecraft.entity.player.EntityPlayer player) {
		if (player.worldObj.isRemote || player.capabilities.isCreativeMode) {
            return false;
        }
		
		Block block = player.worldObj.getBlockState(pos).getBlock();
		IBlockState state = player.worldObj.getBlockState(pos);
		World worldIn = player.worldObj;
		
		if (block.getBlockState().getBaseState().getMaterial() == Material.LEAVES || block instanceof BlockTallGrass) {
			block.dropBlockAsItem(worldIn, pos, state, 1);
			System.out.println(block.getLocalizedName() + " is a leaf or grass!");
		} else {
			System.out.println(block.getLocalizedName() + " is not a leaf!");
		}
		
		itemstack.damageItem(1, player);
		return false;
	}
	
	@Override
	public boolean getIsRepairable (ItemStack toRepair, ItemStack repair) {
		ItemStack mat = this.ToolMaterial.getRepairItemStack();
		if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false)) {
			return true;
		}
		return super.getIsRepairable(toRepair, repair);
	}
	
}
