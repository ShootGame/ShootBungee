/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.auth;

import pl.shg.shootbungee.component.Component;

/**
 *
 * @author Aleksander
 */
public class AuthComponent extends Component {
    protected AuthComponent() {
        this.getProxy().getPluginManager().registerListener(this.getPlugin(), new CommandListener());
    }
    
    public static Component create() {
        return new AuthComponent();
    }
}
