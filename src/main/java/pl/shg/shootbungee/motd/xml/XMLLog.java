/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.motd.xml;

import java.util.List;
import java.util.UUID;
import org.jdom2.Element;
import pl.shg.commons.time.UnixTime;
import pl.shg.commons.users.Console;
import pl.shg.shootbungee.motd.MOTDHandler;

/**
 *
 * @author Aleksander
 */
public class XMLLog {
    private final UUID author;
    private final UnixTime time;
    private final String server;
    private final String username;
    private final String value;
    
    public XMLLog(UUID author, String server, String username, String value) {
        this(author, UnixTime.now(), server, username, value);
    }
    
    public XMLLog(UUID author, UnixTime time, String server, String username, String value) {
        this.author = author;
        this.time = time;
        this.server = server;
        this.username = username;
        this.value = value;
    }
    
    public UUID getAuthor() {
        return this.author;
    }
    
    public UnixTime getTime() {
        return this.time;
    }
    
    public String getServer() {
        return this.server;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public static XMLLog create(Element element) {
        UUID author = UUID.fromString(element.getAttributeValue("author", Console.ID.toString()));
        UnixTime time = new UnixTime(Long.valueOf(element.getAttributeValue("time", "0")));
        String server = element.getAttributeValue("server");
        // ^ never use Servers.getServer here, because servers can be removed
        String username = element.getAttributeValue("username");
        String value = getValue(element.getChildren("line"));
        
        return new XMLLog(author, time, server, username, value);
    }
    
    private static String getValue(List<Element> element) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < element.size(); i++) {
            builder.append(MOTDHandler.NEW_LINE).append(element.get(i).getText());
        }
        
        return builder.toString().substring(MOTDHandler.NEW_LINE.length());
    }
}
