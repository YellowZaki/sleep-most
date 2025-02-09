package me.mrgeneralq.sleepmost.commands.subcommands;

import me.mrgeneralq.sleepmost.interfaces.IFlagService;
import me.mrgeneralq.sleepmost.interfaces.IMessageService;
import me.mrgeneralq.sleepmost.interfaces.ISubCommand;
import me.mrgeneralq.sleepmost.messages.MessageTemplate;
import me.mrgeneralq.sleepmost.statics.CommandSenderUtils;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ResetSubCommand implements ISubCommand
{
    private final IMessageService messageService;
    private final IFlagService flagService;

    public ResetSubCommand(IMessageService messageService, IFlagService flagService) {
        this.messageService = messageService;
        this.flagService = flagService;
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if(!CommandSenderUtils.hasWorld(sender)){
            this.messageService.sendMessage(sender, messageService.fromTemplate(MessageTemplate.NO_CONSOLE_COMMAND));
            return true;
        }
        World world = CommandSenderUtils.getWorldOf(sender);
        this.flagService.resetFlagsAt(world);

        this.messageService.sendMessage(sender, this.messageService.newBuilder(MessageTemplate.FLAGS_RESET_SUCCESS)
                .usePrefix(true)
                .setWorld(world)
                .build());
        return true;
    }
}
