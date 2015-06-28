/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.motd.xml;

import pl.shg.shootbungee.motd.AbstractMOTD;

/**
 *
 * @author Aleksander
 */
public class XMLProperties {
    private AbstractMOTD current;
    
    public XMLProperties() {
        
    }
    
    public XMLProperties(AbstractMOTD current) {
        this.current = current;
    }
    
    public AbstractMOTD getCurrent() {
        return this.current;
    }
    
    public void setCurrent(AbstractMOTD current) {
        this.current = current;
    }
}
