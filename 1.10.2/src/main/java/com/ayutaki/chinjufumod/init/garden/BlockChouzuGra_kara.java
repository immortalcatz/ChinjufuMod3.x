package com.ayutaki.chinjufumod.init.garden;

import java.util.List;

import com.ayutaki.chinjufumod.init.ASDecoModGarden;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockChouzuGra_kara extends Block {

	public BlockChouzuGra_kara() {
		super(Material.WOOD);

		this.setSoundType(SoundType.STONE);
		this.setHardness(1.0F);
		this.setResistance(5.0F);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side,
			float hitX, float hitY, float hitZ) {

		if (heldItem == null) {

			return worldIn.setBlockState(pos, ASDecoModGarden.CHOUZU_GRA_kara.getDefaultState());
		}

		else {

			Item item = heldItem.getItem();
			if (item == Items.WATER_BUCKET) {

				if (playerIn instanceof EntityPlayer)
	    			((EntityPlayer) playerIn).inventory.clearMatchingItems(Items.WATER_BUCKET, -1, 1, null);
	    		if (playerIn instanceof EntityPlayer)
					((EntityPlayer) playerIn).inventory
					.addItemStackToInventory(new ItemStack(Items.BUCKET, 1));

				worldIn.setBlockState(pos, ASDecoModGarden.CHOUZU_GRA_1.getDefaultState());
			}

			else if (item == Items.BUCKET) {
			}
		return true;
		}
    }

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.75D, 0.9375D);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		int meta = stack.getMetadata();
		tooltip.add(I18n.format("tips.tile.block_chouzubachi.name", meta));
	}
}
