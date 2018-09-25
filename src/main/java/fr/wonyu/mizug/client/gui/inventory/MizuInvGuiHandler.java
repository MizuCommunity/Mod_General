package fr.wonyu.mizug.client.gui.inventory;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.gui.container.MizuContainerCosArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class MizuInvGuiHandler implements IGuiHandler
{

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (id)
        {
            case 1:
                return new MizuGuiCosArmorInventory(new MizuContainerCosArmor(player.inventory, !world.isRemote, MizuG.proxy.getCosArmorInventoryClient(player.getUniqueID()), player));
        }
        return null;
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (id)
        {
            case 1:
                return new MizuContainerCosArmor(player.inventory, !world.isRemote,MizuG.proxy.getCosArmorInventory(player.getUniqueID()), player);
        }
        return null;
    }

}
