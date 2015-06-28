/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.motd;

/**
 *
 * @author Aleksander
 */
public class MOTDHandler {
    public static final StaticMOTD DEFAULT = new DefaultMOTD();
    public static final String NEW_LINE = "%nl";
    
    private static MOTDBase base;
    private static AbstractMOTD motd;
    
    public static String asString() {
        return asString(getMOTD());
    }
    
    public static String asString(AbstractMOTD of) {
        return of.getValue().replace(NEW_LINE, "\n");
    }
    
    public static MOTDBase getBase() {
        return base;
    }
    
    public static String[] getLines(String string) {
        return string.split(NEW_LINE, 2);
    }
    
    public static AbstractMOTD getMOTD() {
        if (motd != null) {
            return motd;
        }
        
        return DEFAULT;
    }
    
    public static void setBase(MOTDBase base) {
        MOTDHandler.base = base;
    }
    
    public static void setMOTD(AbstractMOTD motd) {
        MOTDHandler.motd = motd;
    }
}
