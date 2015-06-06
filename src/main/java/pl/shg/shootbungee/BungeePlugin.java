/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee;

import net.md_5.bungee.api.plugin.Plugin;
import pl.shg.shootbungee.component.Component;
import pl.shg.shootbungee.server.ServerPingTask;

/**
 *
 * @author Aleksander
 */
public class BungeePlugin extends Plugin {
    private static BungeePlugin plugin;
    
    @Override
    public void onEnable() {
        plugin = this;
        
        Component.createAll();
    }
    
    @Override
    public void onDisable() {
        ServerPingTask.setRunning(false);
    }
    
    public static BungeePlugin getPlugin() {
        return plugin;
    }
}
