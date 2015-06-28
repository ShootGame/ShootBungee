/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.motd;

import java.util.List;
import pl.shg.commons.users.BungeeConsoleUser;
import pl.shg.shootbungee.Language;
import pl.shg.shootbungee.editor.Editor;
import pl.shg.shootbungee.motd.xml.XMLLog;

/**
 *
 * @author Aleksander
 */
public class StaticMOTD extends AbstractMOTD {
    public StaticMOTD(String id, List<XMLLog> logs) {
        super(id, logs, "Static");
    }
    
    @Override
    public boolean edit(Editor editor, String edit) {
        editor.sendError(Language.MOTD_STATIC_EDIT.get(new BungeeConsoleUser()));
        return false;
    }
}
