/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.motd;

import pl.shg.shootbungee.Language;
import pl.shg.shootbungee.editor.Editor;

/**
 *
 * @author Aleksander
 */
public class StaticMOTD extends AbstractMOTD {
    public StaticMOTD(String first, String second) {
        this.setFirstLine(first);
        this.setSecondLine(second);
    }
    
    @Override
    public boolean edit(Editor editor, String edit) {
        editor.sendError(Language.MOTD_STATIC_EDIT.get(null));
        return false;
    }
    
    @Override
    public String getFirstLine() {
        return this.first;
    }
    
    @Override
    public String getSecondLine() {
        return this.second;
    }
    
    public final void setFirstLine(String first) {
        this.first = first;
    }
    
    public final void setSecondLine(String second) {
        this.second = second;
    }
}
