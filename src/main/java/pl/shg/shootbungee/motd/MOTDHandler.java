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
    private static AbstractMOTD motd;
    
    public static String asString() {
        return asString(getMOTD());
    }
    
    public static String asString(AbstractMOTD of) {
        return of.getFirstLine() + "\n" + of.getSecondLine();
    }
    
    public static AbstractMOTD getMOTD() {
        return motd;
    }
    
    public static void setMOTD(AbstractMOTD motd) {
        MOTDHandler.motd = motd;
    }
}
