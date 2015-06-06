/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.motd;

import net.md_5.bungee.api.ChatColor;

/**
 *
 * @author Aleksander
 */
public class DefaultMOTD extends StaticMOTD {
    public DefaultMOTD() {
        super(
                motd("          &4&lShoot&7&lGame &5- &6Nowe gry MiniGames!"),
                motd("&5&m-----&r &aHardcore &5&m----&r &cArcade &5&m----&r &cSkyBlock &5&m------")
        );
    }
    
    public static String motd(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
