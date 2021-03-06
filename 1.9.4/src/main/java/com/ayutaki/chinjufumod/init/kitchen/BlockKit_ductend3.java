package com.ayutaki.chinjufumod.init.kitchen;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.init.ASDecoModKitchen;
import com.ayutaki.chinjufumod.init.blocks.BlockFacingSapo;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockKit_ductend3 extends BlockFacingSapo {

	public BlockKit_ductend3() {
		super(Material.WOOD);

		/* 金属 */
		this.setSoundType(SoundType.METAL);
		this.setHardness(1.0F);
		this.setResistance(10.0F);

	}

	@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand,
    		@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

		if (playerIn.isSneaking()) {

			return worldIn.setBlockState(pos,ASDecoModKitchen.KIT_DUCTEND_1
					.getDefaultState().withProperty(FACING, state.getValue(FACING)));
		}

		return false;
    }

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	/*ドロップ指定、ピックアップ指定*/
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {

	    return false;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {

		return new ItemStack(ASDecoModKitchen.KIT_DUCTEND_1).getItem();
	}

	@Override
	public int quantityDropped(Random random) {
        return 1;
    }

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {

		return new ItemStack(ASDecoModKitchen.KIT_DUCTEND_1);
	}

}
