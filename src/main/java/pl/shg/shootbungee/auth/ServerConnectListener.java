/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.auth;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 *
 * @author Aleksander
 */
public class ServerConnectListener implements Listener {
    @EventHandler
    public void onServerConnect(ServerConnectEvent e) {
        Server server = e.getPlayer().getServer();
        
        if (server != null && server.getInfo().getName().equals(AuthServer.ID) && !this.isAllowed(e.getPlayer(), e.getTarget())) {
            e.setCancelled(true);
        }
    }
    
    private boolean isAllowed(ProxiedPlayer player, ServerInfo target) {
        return false;
    }
}
