/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.auth;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.shg.commons.bungee.event.CommandEvent;

/**
 *
 * @author Aleksander
 */
public class CommandListener implements Listener {
    @EventHandler
    public void onCommand(CommandEvent e) {
        if (e.getSender() instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) e.getSender();
            
            if (player.getServer().getInfo().getName().equals(AuthServer.ID)) {
                e.setCancelled(true);
                e.getSender().sendMessage(ChatColor.RED + "error");
            }
        }
    }
}
