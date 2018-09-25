package fr.wonyu.mizug.client.gui.inventory;

import com.google.common.collect.Lists;

import fr.wonyu.mizug.client.utils.ModConfigs;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class MizuGuiConfigCos extends GuiConfig
{

    public MizuGuiConfigCos(GuiScreen parent)
    {
        super(parent, Lists.newArrayList(new ConfigElement(ModConfigs.getLastConfig().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements()), "mizug", false, false, GuiConfig.getAbridgedConfigPath(ModConfigs.getLastConfig().getConfigFile().toString()));
    }

}
