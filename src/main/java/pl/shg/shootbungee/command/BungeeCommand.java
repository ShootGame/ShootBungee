/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author Aleksander
 */
public class BungeeCommand extends Command implements ConsoleExecutor {
    public BungeeCommand(String name, String... aliases) {
        super(name, null, aliases);
    }
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            this.execute((ProxiedPlayer) sender, args);
        } else {
            sender.sendMessage(new TextComponent(""));
        }
    }
    
    @Override
    public void execute(ProxiedPlayer player, String[] args) {
        throw new UnsupportedOperationException("Executor is not set.");
    }
}
