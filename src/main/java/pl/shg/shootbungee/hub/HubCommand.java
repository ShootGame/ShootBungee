/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.hub;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import pl.shg.commons.bungee.UserUtils;
import pl.shg.commons.users.BungeeUser;
import pl.shg.shootbungee.Language;
import pl.shg.shootbungee.command.BungeeCommand;

/**
 *
 * @author Aleksander
 */
public class HubCommand extends BungeeCommand {
    public HubCommand() {
        super("hub", "lobby");
    }
    
    @Override
    public void execute(ProxiedPlayer player, String[] args) {
        if (player.getServer().getInfo().getName().equals(HubServer.ID)) {
            player.sendMessage(new TextComponent(this.getAlready(UserUtils.getUser(player))));
        } else {
            player.sendMessage(new TextComponent(this.getConnecting(UserUtils.getUser(player))));
            player.resetTabHeader();
            player.connect(HubServer.getServerInfo());
        }
    }
    
    private String getAlready(BungeeUser user) {
        return ChatColor.RED + Language.HUB_ALREADY.get(user, HubServer.getDisplayName(user));
    }
    
    private String getConnecting(BungeeUser user) {
        return ChatColor.GREEN + Language.HUB_CONNECTING.get(user, HubServer.getDisplayName(user));
    }
}
