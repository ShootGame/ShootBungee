/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.motd;

import pl.shg.shootbungee.editor.Editor;

/**
 *
 * @author Aleksander
 */
public abstract class AbstractMOTD {
    protected String first, second;
    
    public abstract boolean edit(Editor editor, String edit);
    
    public abstract String getFirstLine();
    
    public abstract String getSecondLine();
}
