/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.component;

import pl.shg.shootbungee.auth.AuthComponent;
import pl.shg.shootbungee.hub.HubComponent;
import pl.shg.shootbungee.motd.MOTDComponent;
import pl.shg.shootbungee.ping.PingComponent;
import pl.shg.shootbungee.server.ServerComponent;

/**
 *
 * @author Aleksander
 */
public class ComponentBase {
    public static final Class<? extends Component>[] COMPONENTS = new Class[] {
        AuthComponent.class,
        HubComponent.class,
        MOTDComponent.class,
        PingComponent.class,
        ServerComponent.class
    };
}
