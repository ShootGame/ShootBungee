/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.ping;

import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.ServerPing.Players;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.shg.shootbungee.motd.AbstractMOTD;
import pl.shg.shootbungee.motd.MOTDHandler;

/**
 *
 * @author Aleksander
 */
public class PingListener implements Listener {
    private static int online = 0;
    
    @EventHandler
    public void onProxyPing(ProxyPingEvent e) {
        e.setResponse(this.getResponse(e.getResponse()));
    }
    
    private ServerPing getResponse(ServerPing response) {
        return new ServerPing(response.getVersion(), this.getPlayers(), this.getMOTD(), this.getFavicon());
    }
    
    private Players getPlayers() {
        int slots = ProxyServer.getInstance().getConfig().getPlayerLimit();
        return new Players(slots, online, PlayerInfoState.createFor());
    }
    
    private String getMOTD() {
        AbstractMOTD motd = MOTDHandler.getMOTD();
        if (motd == null) {
            motd = MOTDHandler.DEFAULT;
        }
        
        return MOTDHandler.asString(motd);
    }
    
    private Favicon getFavicon() {
        return ProxyServer.getInstance().getConfig().getFaviconObject();
    }
    
    public static void setOnlineCount(int count) {
        PingListener.online = count;
    }
}
