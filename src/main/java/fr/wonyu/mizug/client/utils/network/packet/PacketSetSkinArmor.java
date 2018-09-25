package fr.wonyu.mizug.client.utils.network.packet;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.utils.inventory.MizuInventoryCosArmor;
import fr.wonyu.mizug.client.utils.network.NetworkPacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;

public class PacketSetSkinArmor extends NetworkPacket
{

    int slot;
    boolean isSkinArmor;

    public PacketSetSkinArmor()
    {
    }

    public PacketSetSkinArmor(EntityPlayer player, int slot)
    {
        this.slot = slot;
        this.isSkinArmor = MizuG.proxy.getCosArmorInventoryClient(player.getUniqueID()).isSkinArmor(slot);
    }

    @Override
    public void handlePacketClient()
    {
    }

    @Override
    public void handlePacketServer(EntityPlayerMP player)
    {
        MizuInventoryCosArmor inv = MizuG.proxy.getCosArmorInventory(player.getUniqueID());
        inv.setSkinArmor(slot, isSkinArmor);
    }

    @Override
    public void readFromBuffer(ByteBuf buf)
    {
        PacketBuffer pb = new PacketBuffer(buf);

        slot = pb.readByte();
        isSkinArmor = pb.readBoolean();
    }

    @Override
    public void writeToBuffer(ByteBuf buf)
    {
        PacketBuffer pb = new PacketBuffer(buf);

        pb.writeByte(slot);
        pb.writeBoolean(isSkinArmor);
    }

}
