package me.william278.huskchat.commands;

import me.william278.huskchat.HuskChat;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Command;

public class ChannelShortcutCommand extends Command {

    private final String channelId;

    public ChannelShortcutCommand(String command, String channelId) {
        super(command);
        this.channelId = channelId;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if ((sender instanceof ProxiedPlayer player)) {
            if (args.length == 0) {
                ChannelCommand.switchChannels(player, channelId);
            } else {
                StringBuilder message = new StringBuilder();
                for (String arg : args) {
                    message.append(arg).append(" ");
                }
                final String messageToSend = message.toString();
                ChatEvent event = new ChatEvent(player, player.getServer(), messageToSend);
                final String oldChannelId = HuskChat.getPlayerChannel(player.getUniqueId());
                HuskChat.setPlayerChannel(player.getUniqueId(), channelId);
                ProxyServer.getInstance().getPluginManager().callEvent(event);
                HuskChat.setPlayerChannel(player.getUniqueId(), oldChannelId);
            }
        }
    }
}
