package fr.wonyu.mizug.client.utils.network.packet;

import java.io.IOException;
import java.util.UUID;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.utils.inventory.MizuInventoryCosArmor;
import fr.wonyu.mizug.client.utils.network.NetworkPacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class PacketSyncCosArmor extends NetworkPacket
{

    UUID uuid;
    int slot;
    boolean isSkinArmor;
    ItemStack itemCosArmor;

    public PacketSyncCosArmor()
    {
    }

    public PacketSyncCosArmor(EntityPlayer player, int slot)
    {
        this.uuid = player.getUniqueID();
        this.slot = slot;
        this.isSkinArmor = MizuG.proxy.getCosArmorInventory(this.uuid).isSkinArmor(slot);
        this.itemCosArmor = MizuG.proxy.getCosArmorInventory(this.uuid).getStackInSlot(slot);
    }

    @Override
    public void handlePacketClient()
    {
        MizuInventoryCosArmor inv = MizuG.proxy.getCosArmorInventoryClient(uuid);
        inv.setInventorySlotContents(slot, itemCosArmor);
        inv.setSkinArmor(slot, isSkinArmor);
    }

    @Override
    public void handlePacketServer(EntityPlayerMP player)
    {
    }

    @Override
    public void readFromBuffer(ByteBuf buf)
    {
        PacketBuffer pb = new PacketBuffer(buf);

        uuid = new UUID(pb.readLong(), pb.readLong());
        slot = pb.readByte();
        isSkinArmor = pb.readBoolean();
        try
        {
            itemCosArmor = pb.readItemStack();
        }
        catch (IOException ignored)
        {
        }
    }

    @Override
    public void writeToBuffer(ByteBuf buf)
    {
        PacketBuffer pb = new PacketBuffer(buf);

        pb.writeLong(uuid.getMostSignificantBits());
        pb.writeLong(uuid.getLeastSignificantBits());
        pb.writeByte(slot);
        pb.writeBoolean(isSkinArmor);
        pb.writeItemStack(itemCosArmor);
    }

}
