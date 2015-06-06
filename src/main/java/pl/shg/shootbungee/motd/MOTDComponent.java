/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.motd;

import pl.shg.shootbungee.component.Component;

/**
 *
 * @author Aleksander
 */
public class MOTDComponent extends Component {
    protected MOTDComponent() {
        // TODO load custom MOTD?
    }
    
    public static Component create() {
        return new MOTDComponent();
    }
}
