package fr.wonyu.mizug.client.utils;

import fr.wonyu.mizug.client.blocks.MizuBlocks;
import fr.wonyu.mizug.client.items.MizuItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class MizuTabs {

	
	  public static CreativeTabs mizublock = new CreativeTabs("mizublock")
	  {
	    public ItemStack getTabIconItem()
	    {
	      return new ItemStack(MizuItems.MIZU);
	    }
	  };
	  
	  public static CreativeTabs mizufoods = new CreativeTabs("mizufoods")
	  {
	    public ItemStack getTabIconItem()
	    {
	      return new ItemStack(MizuItems.MAIS);
	    }
	  };
	  
	  public static CreativeTabs mizuoreblock = new CreativeTabs("mizuoreblock")
	  {
	    public ItemStack getTabIconItem()
	    {
	      return new ItemStack(MizuBlocks.BERYLLIUM);
	    }
	  };
	  
	  public static CreativeTabs mizustairs = new CreativeTabs("mizustairs")
	  {
	    public ItemStack getTabIconItem()
	    {
	      return new ItemStack(MizuBlocks.ICE_STAIR);
	    }
	  };
	  
	  public static CreativeTabs mizucontainer = new CreativeTabs("mizucontainer")
	  {
	    public ItemStack getTabIconItem()
	    {
	      return new ItemStack(MizuBlocks.CRATES);
	    }
	  };
	  
	  public static CreativeTabs mizuingots = new CreativeTabs("mizuingots")
	  {
	    public ItemStack getTabIconItem()
	    {
	      return new ItemStack(MizuItems.MIZIUM_INGOT);
	    }
	  };
	  
	  public static CreativeTabs mizuores = new CreativeTabs("mizuores")
	  {
	    public ItemStack getTabIconItem()
	    {
	      return new ItemStack(MizuBlocks.BERYLLIUM_ORE);
	    }
	  };
	  
	  public static CreativeTabs mizu3d = new CreativeTabs("mizu3d")
	  {
	    public ItemStack getTabIconItem()
	    {
	      return new ItemStack(MizuBlocks.CHAISE);
	    }
	  };
	  
	  public static CreativeTabs mizuitems = new CreativeTabs("mizuitems")
	  {
	    public ItemStack getTabIconItem()
	    {
	      return new ItemStack(MizuItems.CHOPPE_VIDE);
	    }
	  };

	  public static CreativeTabs mizuswords = new CreativeTabs("mizuswords")
	  {
	    public ItemStack getTabIconItem()
	    {
	      return new ItemStack(MizuItems.POWER_SWORD);
	    }
	  };
	  
	  public static CreativeTabs mizupick = new CreativeTabs("mizuspick")
	  {
	    public ItemStack getTabIconItem()
	    {
	      return new ItemStack(MizuItems.POWER_PICK);
	    }
	  };
	
}
