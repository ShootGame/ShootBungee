/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.hub;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import pl.shg.commons.bungee.UserUtils;
import pl.shg.commons.users.BungeeUser;
import pl.shg.shootbungee.Language;

/**
 *
 * @author Aleksander
 */
public class HubCommand {
    @Command(
            aliases = {"hub", "lobby"},
            desc = "Teleportuj do serwera Lobby",
            flags = "s",
            min = 0, max = 0
    )
    public static void hub(CommandContext context, CommandSender sender) throws CommandException {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            boolean current = player.getServer().getInfo().getName().equals(HubServer.ID);
            
            sendMessage(player, context.hasFlag('s'), current);
            if (!current) {
                player.resetTabHeader();
                player.connect(HubServer.getServerInfo());
            }
        }
    }
    
    private static TextComponent getAlready(BungeeUser user) {
        return new TextComponent(ChatColor.RED + Language.HUB_ALREADY.get(user, HubServer.getDisplayName(user)));
    }
    
    private static TextComponent getConnecting(BungeeUser user) {
        return new TextComponent(ChatColor.RED + Language.HUB_CONNECTING.get(user, HubServer.getDisplayName(user)));
    }
    
    private static void sendMessage(ProxiedPlayer player, boolean silence, boolean current) {
        if (silence) {
            return;
        }
        TextComponent message;
        BungeeUser user = UserUtils.getUser(player);
        
        if (current) {
            message = getAlready(user);
        } else {
            message = getConnecting(user);
        }
        
        player.sendMessage(message);
    }
}
