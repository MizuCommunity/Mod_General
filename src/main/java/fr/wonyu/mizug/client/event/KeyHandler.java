package fr.wonyu.mizug.client.event;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.utils.network.packet.PacketOpenCosArmorInventory;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class KeyHandler
{

    public KeyBinding keyOpenCosArmorInventory = new KeyBinding("cos.key.openCosArmorInventory", 0, "key.categories.inventory");

    public KeyHandler()
    {
        ClientRegistry.registerKeyBinding(keyOpenCosArmorInventory);
    }

    @SubscribeEvent
    public void handleEvent(ClientTickEvent event)
    {
        if (event.phase == Phase.START)
        {
            if (keyOpenCosArmorInventory.isPressed() && FMLClientHandler.instance().getClient().inGameHasFocus)
                MizuG.network.sendToServer(new PacketOpenCosArmorInventory());
        }
    }

}
