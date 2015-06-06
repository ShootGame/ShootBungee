/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.server;

import java.util.List;
import net.md_5.bungee.api.plugin.Event;
import pl.shg.commons.server.Servers;
import pl.shg.commons.server.TargetServer;

/**
 *
 * @author Aleksander
 */
public class ServersPingedEvent extends Event {
    public List<TargetServer> getServers() {
        return Servers.getServers();
    }
}
