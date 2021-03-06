/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.motd;

import java.util.List;
import pl.shg.shootbungee.editor.Editor;
import pl.shg.shootbungee.motd.xml.XMLLog;
import pl.shg.shootbungee.motd.xml.XMLObject;

/**
 *
 * @author Aleksander
 */
public abstract class AbstractMOTD extends XMLObject {
    public AbstractMOTD(String id, String type) {
        super(id, type);
    }
    
    public AbstractMOTD(String id, List<XMLLog> logs, String type) {
        super(id, logs, type);
    }
    
    public abstract boolean edit(Editor editor, String edit);
    
    public boolean isEditable() {
        return false;
    }
}
