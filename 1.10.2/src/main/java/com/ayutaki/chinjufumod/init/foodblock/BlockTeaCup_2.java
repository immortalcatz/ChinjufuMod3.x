package com.ayutaki.chinjufumod.init.foodblock;

import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.init.ChinjufuModFoodBlocks;
import com.ayutaki.chinjufumod.init.TTimeItems;
import com.ayutaki.chinjufumod.init.blocks.BlockFacingSapo;
import com.ayutaki.chinjufumod.util.CollisionHelper;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTeaCup_2 extends BlockFacingSapo {

	private static final AxisAlignedBB BOUNDING_BOX_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.375, 0.0, 0.375, 0.625, 0.125, 0.625);
	private static final AxisAlignedBB BOUNDING_BOX_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.375, 0.0, 0.375, 0.625, 0.125, 0.625);
	private static final AxisAlignedBB BOUNDING_BOX_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.375, 0.0, 0.375, 0.625, 0.125, 0.625);
	private static final AxisAlignedBB BOUNDING_BOX_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.375, 0.0, 0.375, 0.625, 0.125, 0.625);
	private static final AxisAlignedBB[] BOUNDING_BOX = { BOUNDING_BOX_SOUTH, BOUNDING_BOX_WEST, BOUNDING_BOX_NORTH, BOUNDING_BOX_EAST };

	/*満タン*/
	public BlockTeaCup_2()  {
		super(Material.WOOD);

		/*湯呑み*/
		this.setSoundType(SoundType.STONE);
		this.setHardness(1.0F);
		this.setResistance(1.0F);

	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state,
			EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

		if (!playerIn.capabilities.allowEdit) {
			return false;

		}

		else if (heldItem == null) {
			/*採掘速度60秒 1秒＝20*/
			((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.HASTE, 1200, 0));

			return worldIn.setBlockState(pos, ChinjufuModFoodBlocks.TEACUP_kara.getDefaultState()
					.withProperty(FACING, state.getValue(FACING)));
		}
		return true;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

		EnumFacing facing = state.getValue(FACING);
		return BOUNDING_BOX[facing.getHorizontalIndex()];
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB axisAligned, List<AxisAlignedBB> axisAlignedList, Entity collidingEntity) {

		EnumFacing facing = state.getValue(FACING);
		super.addCollisionBoxToList(pos, axisAligned, axisAlignedList, BOUNDING_BOX[facing.getHorizontalIndex()]);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
	    return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
	    return false;
	}

	/*ドロップ指定、ピックアップ指定*/
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {

	    return false;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {

		return new ItemStack(TTimeItems.Item_TCUP).getItem();
	}

	@Override
	public int quantityDropped(Random random) {
        return 1;
    }

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {

		return new ItemStack(TTimeItems.Item_TCUP);
	}

}
