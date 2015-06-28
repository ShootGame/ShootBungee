/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.hub;

import pl.shg.commons.command.BungeeCommandBase;
import pl.shg.shootbungee.component.Component;

/**
 *
 * @author Aleksander
 */
public class HubComponent extends Component {
    protected HubComponent() {
        BungeeCommandBase.register(this.getPlugin(), HubCommand.class);
    }
    
    public static Component create() {
        return new HubComponent();
    }
}
