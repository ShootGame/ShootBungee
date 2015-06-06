/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.editor;

import java.util.logging.Level;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;

/**
 *
 * @author Aleksander
 */
public class Editor {
    public String getEditorName() {
        return ProxyServer.getInstance().getName();
    }
    
    public void sendError(String error) {
        this.sendMessage(ChatColor.RED + error);
    }
    
    public void sendMessage(String message) {
        ProxyServer.getInstance().getLogger().log(Level.INFO, message);
    }
    
    public void sendSuccess(String success) {
        this.sendMessage(ChatColor.GREEN + success);
    }
}
