package fr.wonyu.mizug.client.gui;

import fr.wonyu.mizug.client.entity.tiles.MizuForgeTile;
import fr.wonyu.mizug.client.entity.tiles.MizuTileBigCratesBlock;
import fr.wonyu.mizug.client.entity.tiles.MizuTileCratesBlock;
import fr.wonyu.mizug.client.entity.tiles.MizuTileTirroirs;
import fr.wonyu.mizug.client.gui.container.MizuContainerBigCratesBlock;
import fr.wonyu.mizug.client.gui.container.MizuContainerCompactor;
import fr.wonyu.mizug.client.gui.container.MizuContainerCratesBlock;
import fr.wonyu.mizug.client.gui.container.MizuContainerForge;
import fr.wonyu.mizug.client.gui.container.MizuContainerTirroirs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class MizuGuiHandler implements IGuiHandler {
	public static int forgeGuiID = 1;
	public static final int anvilGuiID = 2;
	public static final int cratesGuiID = 3;
	public static final int bigCratesGuiID = 4;
	public static final int tirroisGuiID = 5;
	public static final int compactorGuiID = 6;
	public static final int inventoryCos = 7;

	// Gets the server side element for the given gui id this should return a container
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);

		if (ID == forgeGuiID) {
			System.err.println("Invalid ID: expected " + forgeGuiID + ", received " + ID);
		}
		
		if (tileEntity instanceof MizuForgeTile) {
			MizuForgeTile tileInventoryFurnace = (MizuForgeTile) tileEntity;
			return new MizuContainerForge(player.inventory, tileInventoryFurnace);
		}
		
		if (ID == cratesGuiID) {
			System.err.println("Invalid ID: expected " + cratesGuiID + ", received " + ID);
		}

		if (tileEntity instanceof MizuTileCratesBlock) {
			MizuTileCratesBlock tileInventoryFurnace = (MizuTileCratesBlock) tileEntity;
			return new MizuContainerCratesBlock(player.inventory, tileInventoryFurnace);
		}
		
		if (ID == bigCratesGuiID) {
			System.err.println("Invalid ID: expected " + bigCratesGuiID + ", received " + ID);
		}

		if (tileEntity instanceof MizuTileBigCratesBlock) {
			MizuTileBigCratesBlock tileInventoryFurnace = (MizuTileBigCratesBlock) tileEntity;
			return new MizuContainerBigCratesBlock(player.inventory, tileInventoryFurnace);
		}
		
		if (ID == tirroisGuiID) {
			System.err.println("Invalid ID: expected " + tirroisGuiID + ", received " + ID);
		}

		if (tileEntity instanceof MizuTileTirroirs) {
			MizuTileTirroirs tileInventoryFurnace = (MizuTileTirroirs) tileEntity;
			return new MizuContainerTirroirs(player.inventory, tileInventoryFurnace);
		}
		
	    if(ID == compactorGuiID)
	    {
	    return new MizuContainerCompactor(player.inventory, world, xyz);
	    }
	    
		return null;
	}

	// Gets the client side element for the given gui id this should return a gui
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		
		if (ID == forgeGuiID) {
			System.err.println("Invalid ID: expected " + forgeGuiID + ", received " + ID);
		}

		if (tileEntity instanceof MizuForgeTile) {
			MizuForgeTile tileInventoryFurnace = (MizuForgeTile) tileEntity;
			return new MizuGuiFurnace(player.inventory, tileInventoryFurnace);
		}
		
		if (ID == cratesGuiID) {
			System.err.println("Invalid ID: expected " + cratesGuiID + ", received " + ID);
		}

		if (tileEntity instanceof MizuTileCratesBlock) {
			MizuTileCratesBlock tileInventoryFurnace = (MizuTileCratesBlock) tileEntity;
			return new MizuGuiCratesBlock(player.inventory, tileInventoryFurnace);
		}
		
		if (ID == bigCratesGuiID) {
			System.err.println("Invalid ID: expected " + bigCratesGuiID + ", received " + ID);
		}

		if (tileEntity instanceof MizuTileBigCratesBlock) {
			MizuTileBigCratesBlock tileInventoryFurnace = (MizuTileBigCratesBlock) tileEntity;
			return new MizuGuiBigCratesBlock(player.inventory, tileInventoryFurnace);
		}
		
		if (ID == tirroisGuiID) {
			System.err.println("Invalid ID: expected " + tirroisGuiID + ", received " + ID);
		}

		if (tileEntity instanceof MizuTileTirroirs) {
			MizuTileTirroirs tileInventoryFurnace = (MizuTileTirroirs) tileEntity;
			return new MizuGuiTirroirs(player.inventory, tileInventoryFurnace);
		}
		
	    if(ID == compactorGuiID)
	    {
	    return new MizuGuiCompactor(player.inventory, world, xyz);
	    }
	    
		return null;
	}

}