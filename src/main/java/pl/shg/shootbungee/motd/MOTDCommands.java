/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.motd;

import com.sk89q.minecraft.util.commands.ChatColor;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import com.sk89q.minecraft.util.commands.CommandUsageException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.md_5.bungee.api.CommandSender;
import org.jdom2.JDOMException;
import pl.shg.commons.extended.BooleanUtils;
import pl.shg.commons.time.UnixTime;
import pl.shg.commons.users.BungeeUser;
import pl.shg.commons.users.LocalUser;
import pl.shg.shootbungee.Language;
import pl.shg.shootbungee.Users;
import pl.shg.shootbungee.motd.xml.XMLLog;

/**
 *
 * @author Aleksander
 */
public class MOTDCommands {
    @Command(
            aliases = {"motdadd"},
            desc = "Dodaj nowe MOTD",
            usage = "<id> <type> <motd...>",
            min = 3
    )
    //@CommandPermissions(value = "shootbungee.motd")
    public static void add(CommandContext context, CommandSender sender) throws CommandException {
        
    }
    
    @Command(
            aliases = {"motdclone"},
            desc = "Klonuj MOTD do nowego obiektu",
            flags = "cd",
            usage = "[id] <clone>",
            min = 2, max = 2
    )
    @CommandPermissions(value = "shootbungee.motd")
    public static void clone(CommandContext context, CommandSender sender) throws CommandException {
        LocalUser user = Users.fromSender(sender);
        AbstractMOTD motd;
        
        if (context.hasFlag('c')) {
            motd = MOTDHandler.getMOTD();
        } else if (context.hasFlag('d')) {
            motd = MOTDHandler.DEFAULT;
        } else {
            motd = MOTDHandler.getBase().getMOTD(context.getString(0));
            
            if (motd == null) {
                throw new CommandException("");
            }
        }
        
        List<XMLLog> logs = new ArrayList<>();
        String server = ((BungeeUser) user).getBungee().getServer().getInfo().getName();
        String username = ((BungeeUser) user).getBungee().getName();
        
        logs.add(new XMLLog(user.getID(), UnixTime.now(), server, username, motd.getValue()));
        create(user, new EditableMOTD(context.getString(1), logs));
    }
    
    @Command(
            aliases = {"motdedit"},
            desc = "Edytuj istniejace MOTD",
            flags = "c",
            usage = "[id] <motd...>",
            min = 2
    )
    //@CommandPermissions(value = "shootbungee.motd")
    public static void edit(CommandContext context, CommandSender sender) throws CommandException {
        
    }
    
    @Command(
            aliases = {"motdhelp"},
            desc = "Uzyskaj pomoc komend MOTD",
            min = 0, max = 0
    )
    //@CommandPermissions(value = "shootbungee.motd")
    public static void help(CommandContext context, CommandSender sender) throws CommandException {
        LocalUser user = Users.fromSender(sender);
        user.sendMessage(ChatColor.YELLOW + "========== " + ChatColor.GOLD + "HELP" + ChatColor.YELLOW + " ==========");
        
        sendHelp(user, "motdadd <id> <type> <motd...>", "Dodaj nowe MOTD");
        sendHelp(user, "motdclone [-cd] [clone]", "Klonuj MOTD do nowego obiektu");
        sendHelp(user, "motdedit [-c] [id] <motd...>", "Edytuj istniejace MOTD");
        sendHelp(user, "motdhelp", "Uzyskaj pomoc komend MOTD");
        sendHelp(user, "motdhistory [-c] [id]", "Wyswietl historie zmian MOTD");
        sendHelp(user, "motdinfo [-cd] [id]", "Pokaz informacje o MOTD");
        sendHelp(user, "motdlist", "Pokaz liste dostepnych gotowych MOTD");
        sendHelp(user, "motdreload [-d]", "Przeladuj plik z gotowymi MOTD");
        sendHelp(user, "motdremove <id>", "Usun MOTD z pliku i pamieci");
        sendHelp(user, "motdshow [-c] <id>", "Pokaz MOTD serwera");
        sendHelp(user, "motdtypes", "Pokaz liste dostepnych typow MOTD");
        
        user.sendMessage(ChatColor.DARK_AQUA + "Flaga: " + ChatColor.AQUA + "-c" + ChatColor.DARK_AQUA + " - obecne MOTD");
        user.sendMessage(ChatColor.DARK_AQUA + "Flaga: " + ChatColor.AQUA + "-d" + ChatColor.DARK_AQUA + " - domyslne MOTD");
    }
    
    @Command(
            aliases = {"motdhistory"},
            desc = "Wyswietl historie zmian MOTD",
            flags = "c",
            usage = "[id]",
            min = 0, max = 1
    )
    //@CommandPermissions(value = "shootbungee.motd")
    public static void history(CommandContext context, CommandSender sender) throws CommandException {
        LocalUser user = Users.fromSender(sender);
        AbstractMOTD motd;
        
        if (context.argsLength() == 0 || context.hasFlag('c')) {
            motd = MOTDHandler.getMOTD();
        } else {
            motd = MOTDHandler.getBase().getMOTD(context.getString(0));
            
            if (motd == null) {
                throw new CommandException("");
            }
        }
        
        user.sendMessage(ChatColor.YELLOW + "========== " + ChatColor.GREEN + "HISTORY - " + motd.getID() + ChatColor.YELLOW + " ==========");
        for (int i = 0; i < motd.getLogs().size(); i++) {
            sendLog((i + 1), user, motd.getLogs().get(i));
        }
    }
    
    @Command(
            aliases = {"motdinfo"},
            desc = "Pokaz informacje o MOTD",
            flags = "cd",
            usage = "[id]",
            min = 0, max = 1
    )
    //@CommandPermissions(value = "shootbungee.motd")
    public static void info(CommandContext context, CommandSender sender) throws CommandException {
        LocalUser user = Users.fromSender(sender);
        AbstractMOTD motd;
        
        if (context.hasFlag('c')) {
            motd = MOTDHandler.getMOTD();
        } else if (context.hasFlag('d')) {
            motd = MOTDHandler.DEFAULT;
        } else if (context.argsLength() == 0) {
            throw new CommandUsageException("", null);
        } else {
            motd = MOTDHandler.getBase().getMOTD(context.getString(0));
            
            if (motd == null) {
                throw new CommandException("");
            }
        }
        
        user.sendMessage(ChatColor.YELLOW + "========== " + ChatColor.GREEN + "INFO - " + motd.getID() + ChatColor.YELLOW + " ==========");
        sendInfo(user, Language.MOTD_INFO_TYPE, motd.getType());
        sendInfo(user, Language.MOTD_INFO_CLASS, motd.getClass().getName());
        sendInfo(user, Language.MOTD_INFO_EDITABLE, BooleanUtils.toWordYesNo(motd.isEditable()).bungee(sender));
        sendInfo(user, Language.MOTD_INFO_VALUE, "\n" + MOTDHandler.asString(motd));
    }
    
    @Command(
            aliases = {"motdlist"},
            desc = "Pokaz liste dostepnych gotowych MOTD",
            min = 0, max = 0
    )
    //@CommandPermissions(value = "shootbungee.motd")
    public static void list(CommandContext context, CommandSender sender) throws CommandException {
        LocalUser user = Users.fromSender(sender);
        List<AbstractMOTD> list = MOTDHandler.getBase().getMOTDs();
        
        user.sendMessage(ChatColor.YELLOW + "========== " + ChatColor.GREEN + "LIST" + ChatColor.GRAY +
                " (" + list.size() + ")" + ChatColor.YELLOW + " ==========");
        for (AbstractMOTD motd : list) {
            sendListInfo(user, motd);
        }
    }
    
    @Command(
            aliases = {"motdreload"},
            desc = "Przeladuj plik z gotowymi MOTD",
            flags = "d",
            min = 0, max = 1
    )
    //@CommandPermissions(value = "shootbungee.motd")
    public static void reload(CommandContext context, CommandSender sender) throws CommandException {
        try {
            if (MOTDHandler.getBase().reload()) {

            } else {
                throw new CommandException("");
            }
        } catch (IOException | JDOMException ex) {
            Logger.getLogger(MOTDCommands.class.getName()).log(Level.SEVERE, null, ex);
            throw new CommandException("", ex);
        }
        
        if (context.hasFlag('d')) {
            MOTDHandler.setMOTD(MOTDHandler.DEFAULT);
            
        }
    }
    
    @Command(
            aliases = {"motdremove"},
            desc = "Usun MOTD z pliku i pamieci",
            usage = "<id>",
            min = 0, max = 1
    )
    //@CommandPermissions(value = "shootbungee.motd")
    public static void remove(CommandContext context, CommandSender sender) throws CommandException {
        AbstractMOTD motd;
        if (context.argsLength() == 0) {
            throw new CommandUsageException("", null);
        } else {
            motd = MOTDHandler.getBase().getMOTD(context.getString(0));
        }
        
        if (motd.equals(MOTDHandler.DEFAULT)) {
            throw new CommandException("");
        } else if (motd.equals(MOTDHandler.getMOTD())) {
            throw new CommandException("");
        }
        
        boolean success = false;
        try {
            success = motd.removeFromXML();
        } catch (IOException | JDOMException ex) {
            Logger.getLogger(MOTDCommands.class.getName()).log(Level.SEVERE, null, ex);
            throw new CommandException("", ex);
        }
        
        if (!success) {
            throw new CommandException();
        }
        
        MOTDHandler.getBase().removeMOTD(motd);
        sender.sendMessage(ChatColor.GREEN + "");
    }
    
    @Command(
            aliases = {"motdshow"},
            desc = "Pokaz MOTD serwera",
            flags = "c",
            usage = "[id]",
            min = 0, max = 1
    )
    //@CommandPermissions(value = "shootbungee.motd")
    public static void show(CommandContext context, CommandSender sender) throws CommandException {
        LocalUser user = Users.fromSender(sender);
        AbstractMOTD motd = MOTDHandler.getMOTD();
        
        if (!context.hasFlag('c') && context.argsLength() > 0) {
            motd = MOTDHandler.getBase().getMOTD(context.getString(0));
            
            if (motd == null) {
                throw new CommandException("");
            }
        }
        
        user.sendMessage(ChatColor.YELLOW + Language.MOTD_SHOW.get(user,
                motd.getID(), "\n" + MOTDHandler.asString(motd)));
    }
    
    @Command(
            aliases = {"motdtypes"},
            desc = "Pokaz liste dostepnych typow MOTD",
            min = 0, max = 0
    )
    //@CommandPermissions(value = "shootbungee.motd")
    public static void types(CommandContext context, CommandSender sender) throws CommandException {
        
    }
    
    private static void create(LocalUser user, AbstractMOTD motd) throws CommandException {
        MOTDHandler.getBase().addMOTD(motd);
        
        try {
            motd.saveInXML();
        } catch (IOException | JDOMException ex) {
            Logger.getLogger(MOTDCommands.class.getName()).log(Level.SEVERE, null, ex);
            throw new CommandException("", ex);
        }
        
        // message to user
    }
    
    private static void sendHelp(LocalUser user, String command, String description) {
        user.sendMessage(ChatColor.GREEN + "/" + command + ChatColor.YELLOW + " - " + description);
    }
    
    private static void sendInfo(LocalUser user, Language key, String value) {
        user.sendMessage(ChatColor.YELLOW + key.get(user, ChatColor.GREEN + value));
    }
    
    private static void sendListInfo(LocalUser user, AbstractMOTD motd) {
        user.sendMessage(ChatColor.WHITE + Language.MOTD_LIST.get(user,
                ChatColor.GREEN + motd.getID() + ChatColor.YELLOW,
                motd.getType() + ChatColor.DARK_PURPLE + " |" + ChatColor.GRAY,
                ChatColor.YELLOW.toString() + ChatColor.GREEN + BooleanUtils.toWordYesNo(motd.isEditable()).get(user) + ChatColor.DARK_PURPLE.toString(),
                ChatColor.DARK_PURPLE + "| " + ChatColor.GREEN.toString() + (motd.getLogs().size() - 1) + ChatColor.GRAY));
    }
    
    private static void sendLog(int id, LocalUser user, XMLLog log) {
        user.sendMessage(ChatColor.WHITE + "#" + id + ChatColor.YELLOW + " [01-01-1975] " + ChatColor.GRAY +
                log.getServer() + " - " + ChatColor.GREEN + log.getUsername() + ChatColor.GRAY + ":");
        user.sendMessage(log.getValue());
    }
}
