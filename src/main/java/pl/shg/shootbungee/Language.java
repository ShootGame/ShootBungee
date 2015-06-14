/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee;

import pl.shg.commons.translations.LangMessage;
import pl.shg.commons.translations.Translations;
import pl.shg.commons.users.BungeeUser;

/**
 *
 * @author Aleksander
 */
public enum Language {
    HUB_ALREADY("hub.already"),
    HUB_CONNECTING("hub.connecting"),
    HUB_DISPLAY("hub.display"),
    MOTD_STATIC_EDIT("motd.static.edit"),
    ;
    
    private final LangMessage message;
    
    private Language(String key) {
        this.message = new LangMessage("shootbungee." + key);
    }
    
    public LangMessage get() {
        return this.message;
    }
    
    public String get(BungeeUser user, Object... params) {
        if (user != null) {
            return this.get().getUserMessage(user, params);
        }
        
        return Translations.getMessage(this.getKey()).getDefault();
    }
    
    public String getKey() {
        return this.get().getKey();
    }
}
