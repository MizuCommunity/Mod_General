package fr.wonyu.mizug.client.gui.container;

import fr.wonyu.mizug.client.entity.tiles.MizuTileTirroirs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class MizuContainerTirroirs extends Container {

	private MizuTileTirroirs tileEntityInventoryBasic;

	private final int HOTBAR_SLOT_COUNT = 9;
	private final int PLAYER_INVENTORY_ROW_COUNT = 3;
	private final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
	private final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
	private final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

	private final int VANILLA_FIRST_SLOT_INDEX = 0;
	private final int TE_INVENTORY_ROW_COUNT = 6;
	private final int TE_INVENTORY_COLUMN_COUNT = 3;
	private final int TE_INVENTORY_SLOT_COUNT = TE_INVENTORY_COLUMN_COUNT * TE_INVENTORY_ROW_COUNT;
	private final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

	public MizuContainerTirroirs(InventoryPlayer invPlayer, MizuTileTirroirs tileEntityInventoryBasic) {
		this.tileEntityInventoryBasic = tileEntityInventoryBasic;

		final int SLOT_X_SPACING = 18;
		final int SLOT_Y_SPACING = 18;
		final int HOTBAR_XPOS = 8;
		final int HOTBAR_YPOS = 197;
		for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
			int slotNumber = x;
			addSlotToContainer(new Slot(invPlayer, slotNumber, HOTBAR_XPOS + SLOT_X_SPACING * x, HOTBAR_YPOS));
		}

		final int PLAYER_INVENTORY_XPOS = 8;
		final int PLAYER_INVENTORY_YPOS = 139;
		for (int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++) {
			for (int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++) {
				int slotNumber = HOTBAR_SLOT_COUNT + y * PLAYER_INVENTORY_COLUMN_COUNT + x;
				int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
				int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
				addSlotToContainer(new Slot(invPlayer, slotNumber, xpos, ypos));
			}
		}

		final int TILE_INVENTORY_XPOS = 62;
		final int TILE_INVENTORY_YPOS = 18;
		for (int y = 0; y < TE_INVENTORY_ROW_COUNT; y++) {
			for (int x = 0; x < TE_INVENTORY_COLUMN_COUNT; x++) {
				int slotNumber = y * TE_INVENTORY_COLUMN_COUNT + x;
				int xpos = TILE_INVENTORY_XPOS + x * SLOT_X_SPACING;
				int ypos = TILE_INVENTORY_YPOS + y * SLOT_Y_SPACING;
				addSlotToContainer(new Slot(tileEntityInventoryBasic, slotNumber, xpos, ypos));
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntityInventoryBasic.isUsableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int sourceSlotIndex) {
		Slot sourceSlot = (Slot) inventorySlots.get(sourceSlotIndex);
		if (sourceSlot == null || !sourceSlot.getHasStack())
			return ItemStack.EMPTY; // EMPTY_ITEM
		ItemStack sourceStack = sourceSlot.getStack();
		ItemStack copyOfSourceStack = sourceStack.copy();

		if (sourceSlotIndex >= VANILLA_FIRST_SLOT_INDEX
				&& sourceSlotIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
			if (!mergeItemStack(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX,
					TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT, false)) {
				return ItemStack.EMPTY; // EMPTY_ITEM
			}
		} else if (sourceSlotIndex >= TE_INVENTORY_FIRST_SLOT_INDEX
				&& sourceSlotIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
			if (!mergeItemStack(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT,
					false)) {
				return ItemStack.EMPTY; // EMPTY_ITEM
			}
		} else {
			System.err.print("Invalid slotIndex:" + sourceSlotIndex);
			return ItemStack.EMPTY; // EMPTY_ITEM
		}

		if (sourceStack.getCount() == 0) { // getStackSize
			sourceSlot.putStack(ItemStack.EMPTY); // EMPTY_ITEM
		} else {
			sourceSlot.onSlotChanged();
		}

		sourceSlot.onTake(player, sourceStack); // onPickupFromSlot()
		return copyOfSourceStack;
	}

	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);
		this.tileEntityInventoryBasic.closeInventory(playerIn);
	}
}