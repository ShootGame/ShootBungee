/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.ping;

import pl.shg.shootbungee.component.Component;

/**
 *
 * @author Aleksander
 */
public class PingComponent extends Component {
    protected PingComponent() {
        this.getProxy().getPluginManager().registerListener(this.getPlugin(), new PingListener());
    }
    
    public static Component create() {
        return new PingComponent();
    }
}
