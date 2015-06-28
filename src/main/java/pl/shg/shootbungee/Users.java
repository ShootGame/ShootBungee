/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee;

import com.sk89q.minecraft.util.commands.CommandException;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import pl.shg.commons.bungee.UserUtils;
import pl.shg.commons.users.BungeeConsoleUser;
import pl.shg.commons.users.LocalUser;

/**
 *
 * @author Aleksander
 */
public class Users {
    public static BungeeConsoleUser CONSOLE = new BungeeConsoleUser();
    
    public static LocalUser fromSender(CommandSender sender) {
        if (sender instanceof ProxiedPlayer) {
            return UserUtils.getUser((ProxiedPlayer) sender);
        } else {
            return CONSOLE;
        }
    }
    
    public static LocalUser fromPlayer(ProxiedPlayer player) {
        return fromSender(player);
    }
    
    public static ProxiedPlayer getCommandPlayer(CommandSender sender) throws CommandException {
        if (sender instanceof ProxiedPlayer) {
            return (ProxiedPlayer) sender;
        } else {
            throw new CommandException();
        }
    }
    
    public static LocalUser getCommandUser(CommandSender sender) throws CommandException {
        if (sender instanceof LocalUser) {
            return (LocalUser) sender;
        } else {
            throw new CommandException();
        }
    }
}
