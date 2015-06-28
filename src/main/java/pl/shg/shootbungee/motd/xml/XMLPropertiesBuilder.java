/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.motd.xml;

import org.apache.commons.lang3.builder.Builder;
import pl.shg.shootbungee.motd.AbstractMOTD;

/**
 *
 * @author Aleksander
 */
public class XMLPropertiesBuilder implements Builder<XMLProperties> {
    private AbstractMOTD current;
    
    public XMLPropertiesBuilder current(AbstractMOTD current) {
        this.current = current;
        return this;
    }
    
    @Override
    public XMLProperties build() {
        return new XMLProperties(this.current);
    }
}
