/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.hub;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import pl.shg.commons.users.BungeeUser;
import pl.shg.shootbungee.Language;

/**
 *
 * @author Aleksander
 */
public class HubServer {
    public static final String ID = "hub";
    
    public static String getDisplayName(BungeeUser user) {
        return Language.HUB_DISPLAY.get(user);
    }
    
    public static ServerInfo getServerInfo() {
        return ProxyServer.getInstance().getServerInfo(ID);
    }
}
