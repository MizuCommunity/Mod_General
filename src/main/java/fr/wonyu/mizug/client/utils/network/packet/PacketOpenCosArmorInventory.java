package fr.wonyu.mizug.client.utils.network.packet;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.utils.network.NetworkPacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketOpenCosArmorInventory extends NetworkPacket
{

    @Override
    public void handlePacketClient()
    {
    }

    @Override
    public void handlePacketServer(EntityPlayerMP player)
    {
        player.openGui(MizuG.instance, 1, player.world, (int) player.posX, (int) player.posY, (int) player.posZ);
    }

    @Override
    public void readFromBuffer(ByteBuf buf)
    {
    }

    @Override
    public void writeToBuffer(ByteBuf buf)
    {
    }

}
