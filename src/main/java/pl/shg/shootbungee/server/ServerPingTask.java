/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.server;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.md_5.bungee.api.ProxyServer;
import pl.shg.commons.server.Servers;
import pl.shg.commons.server.TargetServer;
import pl.shg.shootbungee.ping.PingListener;
import pl.shg.shootbungee.ping.SimpleInfoState;

/**
 *
 * @author Aleksander
 */
public class ServerPingTask implements Runnable {
    private static boolean running;
    
    @Override
    public void run() {
        while (isRunning()) {
            try {
                long millis = System.currentTimeMillis();
                this.ping();
                
                Thread.sleep(Math.max(1L, this.ticksToMillis(Servers.getPingInterval()) - (System.currentTimeMillis() - millis)));
            } catch (Throwable ex) {
                Logger.getLogger(ServerPingTask.class.getName()).log(Level.SEVERE, "Nie udalo sie spingowac serwerow", ex);
            }
        }
    }
    
    private void ping() throws Throwable {
        for (TargetServer server : Servers.getServers()) {
            server.ping();
        }
        PingListener.setOnlineCount(Servers.getOnlineCount());
        ProxyServer.getInstance().getPluginManager().callEvent(new ServersPingedEvent());
        
        SimpleInfoState.updateCustomState();
    }
    
    private long ticksToMillis(long ticks) {
        return ticks * 50L;
    }
    
    public static boolean isRunning() {
        return running;
    }
    
    public static void setRunning(boolean running) {
        ServerPingTask.running = running;
    }
}
