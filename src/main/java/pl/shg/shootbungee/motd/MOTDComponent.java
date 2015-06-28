/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.motd;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.JDOMException;
import pl.shg.commons.command.BungeeCommandBase;
import pl.shg.shootbungee.component.Component;

/**
 *
 * @author Aleksander
 */
public class MOTDComponent extends Component {
    protected MOTDComponent(File file) {
        MOTDHandler.setBase(new MOTDBase(file));
        BungeeCommandBase.register(this.getPlugin(), MOTDCommands.class);
        
        try {
            if (MOTDHandler.getBase().reload()) {
                
            } else {
                
            }
        } catch (IOException | JDOMException ex) {
            Logger.getLogger(MOTDComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Component create() {
        return new MOTDComponent(new File("." + File.separator + "motd.xml"));
    }
}
