/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.server;

import java.util.logging.Level;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.config.Configuration;
import pl.shg.commons.server.ArcadeTarget;
import pl.shg.commons.server.MinecraftTarget;
import pl.shg.commons.server.Servers;
import pl.shg.commons.server.TargetServer;

/**
 *
 * @author Aleksander
 */
public class BungeeServerLoader {
    private final Configuration file;
    
    public BungeeServerLoader(Configuration file) {
        this.file = file;
    }
    
    public Configuration getFile() {
        return this.file;
    }
    
    public void initialize() {
        this.loadArcade();
        this.loadMinecraft();
    }
    
    private void loadArcade() {
        this.sendLoadingInformation("Arcade", 1);
        if (this.getFile().get("arcade") == null) {
            return;
        }
        
        for (String id : this.getFile().getSection("arcade").getKeys()) {
            Servers.addServer(new ArcadeTarget(
                    this.getFile().getString("arcade." + id + ".address", "localhost:" + TargetServer.PORT),
                    id,
                    this.getFile().getString("arcade." + id + ".name", "Serwer Arcade"),
                    this.getFile().getBoolean("arcade." + id + ".public", false)
            ));
        }
    }
    
    private void loadMinecraft() {
        this.sendLoadingInformation("Minecraft", 2);
        if (this.file.get("minecraft") == null) {
            return;
        }
        
        for (String id : this.getFile().getSection("minecraft").getKeys()) {
            Servers.addServer(new MinecraftTarget(
                    this.getFile().getString("minecraft." + id + ".address", "localhost:" + TargetServer.PORT),
                    null, // we can't load the icon
                    id,
                    this.getFile().getString("minecraft." + id + ".name", "Serwer Minecraft"),
                    this.getFile().getBoolean("minecraft." + id + ".public", false)
            ));
        }
    }
    
    private void sendLoadingInformation(String category, int part) {
        ProxyServer.getInstance().getLogger().log(
                Level.INFO,
                "Ladowanie serwerow {0} ({1} z 2)...",
                new Object[]{category, part}
        );
    }
}
