package fr.wonyu.mizug.api;

import java.util.UUID;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.api.inventory.MizuStacksBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MizuArmorAPI
{

    /**
     * @param uuid
     *            the UniqueID of a player (See also {@link net.minecraft.entity.player.EntityPlayer#getUniqueID() EntityPlayer.getUniqueID()})
     * @return associated {@link CAStacksBase CAStacks} for the input uuid
     */
    public static MizuStacksBase getCAStacks(UUID uuid)
    {
        return MizuG.proxy.getCosArmorInventory(uuid).getStacks();
    }

    /**
     * @param uuid
     *            the UniqueID of a player (See also {@link net.minecraft.entity.player.EntityPlayer#getUniqueID() EntityPlayer.getUniqueID()})
     * @return associated {@link CAStacksBase CAStacks} for the input uuid on the Client
     */
    @SideOnly(Side.CLIENT)
    public static MizuStacksBase getCAStacksClient(UUID uuid)
    {
        return MizuG.proxy.getCosArmorInventoryClient(uuid).getStacks();
    }

}
