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
            player.sendMessage(new TextComponent(ChatColor.RED + "Juz znajdujesz sie na serwerze " + HubServer.getDisplayName() + "!"));
        } else {
            player.sendMessage(new TextComponent(ChatColor.GREEN + "Przelaczanie na serwer " + HubServer.getDisplayName() + "..."));
            player.resetTabHeader();
            player.connect(HubServer.getServerInfo());
        }
    }
}
