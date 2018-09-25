package fr.wonyu.mizug.client.gui.container;

import baubles.api.IBauble;
import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.IBaublesItemHandler;
import baubles.common.container.SlotBauble;
import fr.wonyu.mizug.client.utils.inventory.MizuInventoryCosArmor;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MizuContainerCosArmor extends Container {

	public IBaublesItemHandler baubles;
	public final InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 2);
	public final InventoryCraftResult craftResult = new InventoryCraftResult();

	/**
	 * Determines if inventory manipulation should be handled.
	 */
	public boolean isLocalWorld;
	private final EntityPlayer thePlayer;

	private static final EntityEquipmentSlot[] VALID_EQUIPMENT_SLOTS = { EntityEquipmentSlot.HEAD,
			EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET };

	public MizuContainerCosArmor(InventoryPlayer invPlayer, boolean par2, MizuInventoryCosArmor invCosArmor,
			EntityPlayer player) {

		this.isLocalWorld = par2;
		this.thePlayer = player;
		baubles = player.getCapability(BaublesCapabilities.CAPABILITY_BAUBLES, null);

		this.addSlotToContainer(new SlotCrafting(invPlayer.player, this.craftMatrix, this.craftResult, 0, 145, 49));

		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 2; ++j) {
				this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 2, 136 + j * 18, 8 + i * 18));
			}
		}

		for (int k = 0; k < 4; k++) {
			final EntityEquipmentSlot slot = VALID_EQUIPMENT_SLOTS[k];
			this.addSlotToContainer(new Slot(invPlayer, 36 + (3 - k), 8, 8 + k * 18) {
				@Override
				public int getSlotStackLimit() {
					return 1;
				}

				@Override
				public boolean isItemValid(ItemStack stack) {
					return stack.getItem().isValidArmor(stack, slot, player);
				}

				@Override
				public boolean canTakeStack(EntityPlayer playerIn) {
					ItemStack itemstack = this.getStack();
					return !itemstack.isEmpty() && !playerIn.isCreative()
							&& EnchantmentHelper.hasBindingCurse(itemstack) ? false : super.canTakeStack(playerIn);
				}

				@Override
				public String getSlotTexture() {
					return ItemArmor.EMPTY_SLOT_NAMES[slot.getIndex()];
				}
			});
		}

		this.addSlotToContainer(new SlotBauble(player, baubles, 0, 77, 8));
		this.addSlotToContainer(new SlotBauble(player, baubles, 1, 77, 8 + 1 * 18));
		this.addSlotToContainer(new SlotBauble(player, baubles, 2, 77, 8 + 2 * 18));
		this.addSlotToContainer(new SlotBauble(player, baubles, 3, 77, 8 + 3 * 18));
		this.addSlotToContainer(new SlotBauble(player, baubles, 4, 96, 8));
		this.addSlotToContainer(new SlotBauble(player, baubles, 5, 96, 8 + 1 * 18));
		this.addSlotToContainer(new SlotBauble(player, baubles, 6, 96, 8 + 2 * 18));

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(invPlayer, j + (i + 1) * 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
		}

		this.addSlotToContainer(new Slot(invPlayer, 40, 96, 62) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return super.isItemValid(stack);
			}

			@Override
			public String getSlotTexture() {
				return "minecraft:items/empty_armor_slot_shield";
			}
		});

		for (int i = 0; i < 4; i++) {
			final int j = i;
			final EntityPlayer k = player;
			addSlotToContainer(new Slot(invCosArmor, 3 - i, 115 + i * 0, 8 + i * 18) {

				@Override
				public int getSlotStackLimit() {
					return 1;
				}

				@SideOnly(Side.CLIENT)
				@Override
				public String getSlotTexture() {
					return ItemArmor.EMPTY_SLOT_NAMES[VALID_EQUIPMENT_SLOTS[j].getIndex()];
				}

				@Override
				public boolean isItemValid(ItemStack stack) {
					if (stack == null || stack.isEmpty())
						return false;

					return stack.getItem().isValidArmor(stack, VALID_EQUIPMENT_SLOTS[j], k);
				}

			});
		}
		this.onCraftMatrixChanged(this.craftMatrix);

	}

	/**
	 * Callback for when the crafting matrix is changed.
	 */
	@Override
	public void onCraftMatrixChanged(IInventory par1IInventory) {
		this.slotChangedCraftingGrid(this.thePlayer.getEntityWorld(), this.thePlayer, this.craftMatrix,
				this.craftResult);
	}

	/**
	 * Called when the container is closed.
	 */
	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		this.craftResult.clear();

		if (!player.world.isRemote) {
			this.clearContainer(player, player.world, this.craftMatrix);
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot) inventorySlots.get(slotNumber);

		if ((slot != null) && (slot.getHasStack())) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			EntityEquipmentSlot desiredSlot = EntityLiving.getSlotForItemStack(stack);
			int slotShift = baubles.getSlots();

			if (slotNumber == 0) {
				if (!this.mergeItemStack(stack1, 9 + slotShift, 45 + slotShift, true)) {
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(stack1, stack);
			} else if (slotNumber >= 1 && slotNumber < 5) {
				if (!this.mergeItemStack(stack1, 9 + slotShift, 45 + slotShift, false)) {
					return ItemStack.EMPTY;
				}
			} else if (slotNumber >= 5 && slotNumber < 9) {
				if (!this.mergeItemStack(stack1, 9 + slotShift, 45 + slotShift, false)) {
					return ItemStack.EMPTY;
				}
			}

			// baubles -> inv
			else if (slotNumber >= 9 && slotNumber < 9 + slotShift) {
				if (!this.mergeItemStack(stack1, 9 + slotShift, 45 + slotShift, false)) {
					return ItemStack.EMPTY;
				}
			}

			// inv -> armor
			else if (desiredSlot.getSlotType() == EntityEquipmentSlot.Type.ARMOR
					&& !((Slot) this.inventorySlots.get(8 - desiredSlot.getIndex())).getHasStack()) {
				int i = 8 - desiredSlot.getIndex();

				if (!this.mergeItemStack(stack1, i, i + 1, false)) {
					return ItemStack.EMPTY;
				}
			}

			// inv -> offhand
			else if (desiredSlot == EntityEquipmentSlot.OFFHAND
					&& !((Slot) this.inventorySlots.get(45 + slotShift)).getHasStack()) {
				if (!this.mergeItemStack(stack1, 45 + slotShift, 46 + slotShift, false)) {
					return ItemStack.EMPTY;
				}
			}
			// inv -> bauble
			else if (stack.hasCapability(BaublesCapabilities.CAPABILITY_ITEM_BAUBLE, null)) {
				IBauble bauble = stack.getCapability(BaublesCapabilities.CAPABILITY_ITEM_BAUBLE, null);
				for (int baubleSlot : bauble.getBaubleType(stack).getValidSlots()) {
					if (bauble.canEquip(stack1, thePlayer)
							&& !((Slot) this.inventorySlots.get(baubleSlot + 9)).getHasStack()
							&& !this.mergeItemStack(stack1, baubleSlot + 9, baubleSlot + 10, false)) {
						return ItemStack.EMPTY;
					}
					if (stack1.getCount() == 0)
						break;
				}
			} else if (slotNumber >= 9 + slotShift && slotNumber < 36 + slotShift) {
				if (!this.mergeItemStack(stack1, 36 + slotShift, 45 + slotShift, false)) {
					return ItemStack.EMPTY;
				}
			} else if (slotNumber >= 36 + slotShift && slotNumber < 45 + slotShift) {
				if (!this.mergeItemStack(stack1, 9 + slotShift, 36 + slotShift, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(stack1, 9 + slotShift, 45 + slotShift, false)) {
				return ItemStack.EMPTY;
			}

			if (stack1.isEmpty())
				slot.putStack(ItemStack.EMPTY);
			else
				slot.onSlotChanged();

			if (stack1.getCount() == stack.getCount()) {
				return stack.EMPTY;
			}

			if (stack1.isEmpty() && !baubles.isEventBlocked() && slot instanceof SlotBauble
					&& stack.hasCapability(BaublesCapabilities.CAPABILITY_ITEM_BAUBLE, null)) {
				stack.getCapability(BaublesCapabilities.CAPABILITY_ITEM_BAUBLE, null).onUnequipped(stack, player);
			}

			ItemStack stack2 = slot.onTake(player, stack1);

			if (slotNumber == 0)
				player.dropItem(stack2, false);
		}

		return stack;
	}

	@Override
	public boolean canMergeSlot(ItemStack stack, Slot slot) {
		return slot.inventory != this.craftResult && super.canMergeSlot(stack, slot);
	}

}
