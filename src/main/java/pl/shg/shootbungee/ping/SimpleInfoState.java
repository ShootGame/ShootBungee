/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.ping;

import java.util.ArrayList;
import java.util.List;
import net.md_5.bungee.api.ChatColor;
import pl.shg.commons.server.ArcadeTarget;
import pl.shg.commons.server.MinecraftTarget;
import pl.shg.commons.server.Servers;
import pl.shg.commons.server.TargetServer;

/**
 *
 * @author Aleksander
 */
public class SimpleInfoState {
    private static List<String> staticState;
    private final List<String> list = new ArrayList<>();
    
    public SimpleInfoState() {
        this.header();
        for (TargetServer target : Servers.getServers()) {
            if (target.isPublic() && (target instanceof ArcadeTarget || target instanceof MinecraftTarget)) {
                this.list.add(this.getServer(target));
            }
        }
    }
    
    private void header() {
        String line = ChatColor.DARK_PURPLE + this.getLine(10);
        this.list.add(line + ChatColor.DARK_RED + ChatColor.BOLD + " Shoot" + ChatColor.GRAY + ChatColor.BOLD + "Game" + line);
    }
    
    private String getLine(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append("-");
        }
        
        return ChatColor.DARK_PURPLE + " " + ChatColor.STRIKETHROUGH + builder.toString() + ChatColor.RESET;
    }
    
    private String getServer(TargetServer target) {
        String message = ChatColor.BOLD.toString() + target.getName() + ChatColor.GRAY + " - ";
        if (target.isOnline()) {
            return ChatColor.GREEN + message + this.getServerExtra(target);
        } else {
            return ChatColor.DARK_RED + message + ChatColor.ITALIC + "offline";
        }
    }
    
    private String getServerExtra(TargetServer target) {
        String message = ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "%s" + ChatColor.GRAY + "/%s";
        
        if (target instanceof ArcadeTarget) {
            ArcadeTarget arcade = (ArcadeTarget) target;
            String extra = String.format(message, arcade.getArcadePlayers(), arcade.getArcadeSlots()) +
                    ", mapa " + ChatColor.GOLD + arcade.getMap();
            return extra;
        } else {
            return String.format(message, target.getPlayers(), target.getSlots());
        }
    }
    
    public static List<String> getCustomState() {
        if (staticState == null) {
            updateCustomState();
        }
        
        return staticState;
    }
    
    public static void updateCustomState() {
        staticState = new SimpleInfoState().list;
    }
}
