package com.ayutaki.chinjufumod.init.ranma;

import java.util.List;

import com.ayutaki.chinjufumod.init.ASDecoModRanma;
import com.ayutaki.chinjufumod.init.blocks.BlockFacingSapo;
import com.ayutaki.chinjufumod.util.CollisionHelper;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockKanki_doak extends BlockFacingSapo {

	public static final PropertyEnum<Type> TYPE = PropertyEnum.create("type", Type.class);

	private static final AxisAlignedBB BOUNDING_BOX_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.40625, 0.0, 0.0, 0.59375, 1.0, 1.0);
	private static final AxisAlignedBB BOUNDING_BOX_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.40625, 0.0, 0.0, 0.59375, 1.0, 1.0);
	private static final AxisAlignedBB BOUNDING_BOX_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.40625, 0.0, 0.0, 0.59375, 1.0, 1.0);
	private static final AxisAlignedBB BOUNDING_BOX_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.40625, 0.0, 0.0, 0.59375, 1.0, 1.0);
	private static final AxisAlignedBB[] BOUNDING_BOX = { BOUNDING_BOX_SOUTH, BOUNDING_BOX_WEST, BOUNDING_BOX_NORTH, BOUNDING_BOX_EAST };

	public BlockKanki_doak(Material material, String unlocalizedName) {
		super(material);

		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);

		this.setHardness(0.5F);
		this.setResistance(5.0F);
		this.setSoundType(SoundType.WOOD);

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
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

		if (playerIn.isSneaking()) {

			return worldIn.setBlockState(pos, ASDecoModRanma.KANKI_doak_1.getDefaultState().withProperty(FACING, state.getValue(FACING)));
		}
		return false;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

			EnumFacing facing = (EnumFacing) state.getValue(FACING);
			IBlockState left_block = worldIn.getBlockState(pos.offset(facing.rotateYCCW()));
			IBlockState right_block = worldIn.getBlockState(pos.offset(facing.rotateY()));
			boolean left = left_block.getBlock() instanceof BlockKanki_doak && left_block.getValue(FACING).equals(facing);
			boolean right = right_block.getBlock() instanceof BlockKanki_doak && right_block.getValue(FACING).equals(facing);

			 if(right)
			{
				if(left)
				{
					return state.withProperty(TYPE, Type.BOTH);
				}
				else
				{
					return state.withProperty(TYPE, Type.RIGHT);
				}
			}
			else if(left)
			{
				if(right)
				{
					return state.withProperty(TYPE, Type.BOTH);
				}
				else
				{
					return state.withProperty(TYPE, Type.LEFT);
				}
			}
			return state.withProperty(TYPE, Type.DEFAULT);

	}

	@Override
	protected BlockStateContainer createBlockState() {

		return new BlockStateContainer(this, new IProperty[] { FACING, TYPE}) ;
	}


	public static enum Type implements IStringSerializable {

		DEFAULT, LEFT, RIGHT, BOTH ;

		@Override
		public String getName() {

			return this.toString().toLowerCase();
		}
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
	    return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
	    return false;
	}

}