package fr.wonyu.mizug.client.utils.inventory;

import net.minecraft.item.ItemStack;

public interface ISimpleInventory
{
    int getSize();

    ItemStack getItem(int i);

    void clear();
}