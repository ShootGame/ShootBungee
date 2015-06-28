/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.motd;

import java.util.ArrayList;
import net.md_5.bungee.api.ChatColor;
import pl.shg.shootbungee.motd.xml.XMLLog;

/**
 *
 * @author Aleksander
 */
public class DefaultMOTD extends StaticMOTD {
    public static final String ID = "--default--";
    
    public DefaultMOTD() {
        super(ID, new ArrayList<XMLLog>());
        
        this.setValue(line(
                "          &4&lShoot&7&lGame &5- &6Nowe gry MiniGames!" + MOTDHandler.NEW_LINE +
                "&5&m-----&r &aHardcore &5&m----&r &cArcade &5&m----&r &cSkyBlock &5&m------"
        ));
    }
    
    public static String line(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
