package fr.wonyu.mizug.proxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.api.event.MizuArmorDeathDrops;
import fr.wonyu.mizug.client.blocks.plants.tree.leaf.MizuAppleLeaf;
import fr.wonyu.mizug.client.utils.ModConfigs;
import fr.wonyu.mizug.client.utils.inventory.MizuInventoryCosArmor;
import fr.wonyu.mizug.client.utils.network.packet.PacketSyncCosArmor;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class MizuCommon {
	
	public void preInit(File configFile) {
		System.out.println("Pre Init Commun");
	}
	
	public void init() {
	}
	
	public void registerModel(Item item, int metadata) {}
	
	public void registerModelObj(Item item, int metadata) {}
	
	public void registerMcxModel(Item item, int metadata) {}

	public void setAppleGraphicsLevel(MizuAppleLeaf parBlock, boolean parFancyEnabled) {
	}
	
	public Map<Item, ModelBiped> getArmorModel() {return null;}
	
    LoadingCache<UUID, MizuInventoryCosArmor> cache = CacheBuilder.newBuilder().build(new CacheLoader<UUID, MizuInventoryCosArmor>()
    {

        @Override
        public MizuInventoryCosArmor load(UUID owner) throws Exception
        {
            MizuInventoryCosArmor inv = new MizuInventoryCosArmor();

            try
            {
                forceLoad(owner, inv);
            }
            catch (IOException e)
            {
                System.err.println("Error loading CosmeticArmor data file: " + e.getMessage());
                e.printStackTrace();
                inv = new MizuInventoryCosArmor();
            }

            return inv;
        }

    });

    void forceLoad(UUID uuid, MizuInventoryCosArmor inv) throws IOException
    {
        try
        {
            inv.readFromNBT(CompressedStreamTools.readCompressed(new FileInputStream(getDataFile(uuid))));
        }
        catch (FileNotFoundException ignored)
        {
        }
    }

    void forceSave(UUID uuid, MizuInventoryCosArmor inv) throws IOException
    {
        NBTTagCompound compound = new NBTTagCompound();
        inv.writeToNBT(compound);
        CompressedStreamTools.writeCompressed(compound, new FileOutputStream(getDataFile(uuid)));
    }

    public MizuInventoryCosArmor getCosArmorInventory(UUID uuid)
    {
        return cache.getUnchecked(uuid);
    }

    public MizuInventoryCosArmor getCosArmorInventoryClient(UUID uuid)
    {
        throw new UnsupportedOperationException();
    }

    File getDataFile(UUID uuid)
    {
        return new File(new File(getSavesDirectory(), "playerdata"), uuid + ".cosarmor");
    }

    File getSavesDirectory()
    {
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        if (FMLCommonHandler.instance().getSide().isClient())
            return server.getWorld(0).getSaveHandler().getWorldDirectory();
        return server.getFile(server.getFolderName());
    }

    @SubscribeEvent
    public void handleEvent(PlayerDropsEvent event)
    {
        if (event.getEntityPlayer() instanceof EntityPlayerMP && !event.getEntityPlayer().world.isRemote && !event.getEntityPlayer().world.getGameRules().getBoolean("keepInventory"))
        {
            if (ModConfigs.CosArmorKeepThroughDeath)
                return;
            MizuInventoryCosArmor inv = getCosArmorInventory(event.getEntityPlayer().getUniqueID());
            if (MinecraftForge.EVENT_BUS.post(new MizuArmorDeathDrops(event.getEntityPlayer(), inv.getStacks())))
                return;
            for (int i = 0; i < inv.getSizeInventory(); i++)
            {
                ItemStack stack = inv.getStackInSlot(i);
                if (!stack.isEmpty())
                {
                    EntityItem ent = new EntityItem(event.getEntityPlayer().world, event.getEntityPlayer().posX, event.getEntityPlayer().posY + event.getEntityPlayer().getEyeHeight(), event.getEntityPlayer().posZ, stack.copy());
                    ent.setPickupDelay(40);
                    float f1 = event.getEntityPlayer().world.rand.nextFloat() * 0.5F;
                    float f2 = event.getEntityPlayer().world.rand.nextFloat() * (float) Math.PI * 2.0F;
                    ent.motionX = (double) (-MathHelper.sin(f2) * f1);
                    ent.motionZ = (double) (MathHelper.cos(f2) * f1);
                    ent.motionY = 0.20000000298023224D;
                    event.getDrops().add(ent);
                    inv.setInventorySlotContents(i, ItemStack.EMPTY);
                }
            }
        }
    }

    @SubscribeEvent
    public void handleEvent(PlayerEvent.LoadFromFile event)
    {
        UUID uuid = UUID.fromString(event.getPlayerUUID());
        MizuInventoryCosArmor inv = getCosArmorInventory(uuid);

        try
        {
            inv.readFromNBT(CompressedStreamTools.readCompressed(new FileInputStream(getDataFile(uuid))));
        }
        catch (FileNotFoundException ignored)
        {
        }
        catch (IOException e)
        {
            System.err.println("Error loading CosmeticArmor data file: " + e.getMessage());
            e.printStackTrace();
            cache.refresh(uuid);
            inv = getCosArmorInventory(uuid);
        }
    }

    @SubscribeEvent
    public void handleEvent(PlayerEvent.SaveToFile event)
    {
        UUID uuid = UUID.fromString(event.getPlayerUUID());
        MizuInventoryCosArmor inv = getCosArmorInventory(uuid);
        NBTTagCompound compound = new NBTTagCompound();
        inv.writeToNBT(compound);
        try
        {
            CompressedStreamTools.writeCompressed(compound, new FileOutputStream(getDataFile(uuid)));
        }
        catch (IOException e)
        {
            System.err.println("Error saving CosmeticArmor data file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void handleEvent(PlayerLoggedInEvent event)
    {
        if (event.player instanceof EntityPlayerMP)
        {
            MizuInventoryCosArmor inv = getCosArmorInventory(event.player.getUniqueID());
            for (int i = 0; i < inv.getSizeInventory(); i++)
                MizuG.network.sendToAll(new PacketSyncCosArmor(event.player, i));
            inv.markClean();

            for (EntityPlayerMP other : FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers())
            {
                if (other == event.player)
                    continue;
                inv = getCosArmorInventory(other.getUniqueID());
                for (int i = 0; i < inv.getSizeInventory(); i++)
                	MizuG.network.sendTo(new PacketSyncCosArmor(other, i), (EntityPlayerMP) event.player);
            }
        }
    }

    @SubscribeEvent
    public void handleEvent(PlayerLoggedOutEvent event)
    {
        if (event.player instanceof EntityPlayerMP)
        {
            UUID uuid = event.player.getUniqueID();
            try
            {
                forceSave(uuid, getCosArmorInventory(uuid));
            }
            catch (IOException e)
            {
                System.err.println("Error saving CosmeticArmor data file: " + e.getMessage());
                e.printStackTrace();
            }
            cache.invalidate(uuid);
        }
    }

    @SubscribeEvent
    public void handleEvent(PlayerTickEvent event)
    {
        if (event.phase == Phase.START)
        {
            if (event.player instanceof EntityPlayerMP)
            {
                MizuInventoryCosArmor inv = getCosArmorInventory(event.player.getUniqueID());
                if (inv.isDirty())
                {
                    for (int i = 0; i < inv.getSizeInventory(); i++)
                    	MizuG.network.sendToAll(new PacketSyncCosArmor(event.player, i));
                    inv.markClean();
                }
            }
        }
    }

    public void onServerStarting()
    {
        cache.invalidateAll();
    }

    public void onServerStopping()
    {
        System.out.println("Server is stopping... force saving all loaded CosmeticArmor data.");
        for (UUID uuid : cache.asMap().keySet())
        {
            System.out.println(uuid);
            try
            {
                forceSave(uuid, getCosArmorInventory(uuid));
            }
            catch (IOException e)
            {
                System.err.println("Error saving CosmeticArmor data file: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

	    
}
