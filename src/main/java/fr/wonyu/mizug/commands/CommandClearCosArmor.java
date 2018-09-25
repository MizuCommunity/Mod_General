package fr.wonyu.mizug.commands;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import fr.wonyu.mizug.MizuG;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandClearCosArmor extends CommandBase
{

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        EntityPlayerMP target = args.length == 0 ? getCommandSenderAsPlayer(sender) : getPlayer(server, sender, args[0]);
        MizuG.proxy.getCosArmorInventory(target.getUniqueID()).clear();
        notifyCommandListener(sender, this, "mizu.command.cossucces", target.getName());
    }

    @Override
    public String getName()
    {
        return "clearcosm";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        if (args.length == 1)
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        return Collections.emptyList();
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "mizu.command.clearcosm";
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index)
    {
        return index == 0;
    }

}
