/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.server;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import pl.shg.commons.server.Servers;
import pl.shg.shootbungee.component.Component;

/**
 *
 * @author Aleksander
 */
public class ServerComponent extends Component {
    protected ServerComponent() {
        try {
            String url = "http://shootgame.net/internal/servers.yml"; // TODO move to the configuration file
            
            ConfigurationProvider provier = ConfigurationProvider.getProvider(YamlConfiguration.class);
            Configuration target = provier.load(new URL(url).openStream());
            
            new BungeeServerLoader(target).initialize();
            Servers.setPingInterval(target.getLong("ping-interval", 5 * 20L));
            
            ServerPingTask.setRunning(true);
            this.getProxy().getScheduler().runAsync(this.getPlugin(), new ServerPingTask());
        } catch (IOException ex) {
            Logger.getLogger(ServerComponent.class.getName()).log(Level.INFO, null, ex);
        }
    }
    
    public static Component create() {
        return new ServerComponent();
    }
}
