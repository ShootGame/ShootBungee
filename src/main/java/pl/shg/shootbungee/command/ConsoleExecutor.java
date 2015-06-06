/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.command;

import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 *
 * @author Aleksander
 */
public interface ConsoleExecutor {
    void execute(ProxiedPlayer player, String[] args);
}
