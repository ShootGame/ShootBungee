/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.editor;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

/**
 *
 * @author Aleksander
 */
public class CommandSenderEditor extends Editor {
    private final CommandSender source;
    
    public CommandSenderEditor(CommandSender source) {
        this.source = source;
    }
    
    @Override
    public String getEditorName() {
        return this.getSource().getName();
    }
    
    @Override
    public void sendMessage(String message) {
        this.getSource().sendMessage(new TextComponent(message));
    }
    
    public CommandSender getSource() {
        return this.source;
    }
}
