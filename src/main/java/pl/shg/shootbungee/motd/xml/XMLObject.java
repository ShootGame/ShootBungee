/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.motd.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import pl.shg.shootbungee.motd.AbstractMOTD;
import pl.shg.shootbungee.motd.MOTDHandler;

/**
 *
 * @author Aleksander
 */
public class XMLObject {
    private final String id;
    private final List<XMLLog> logs;
    private final String type;
    private String value;
    
    public XMLObject(String id, String type) {
        this(id, new ArrayList<XMLLog>(), type);
    }
    
    public XMLObject(String id, List<XMLLog> logs, String type) {
        this.id = id;
        this.logs = logs;
        this.type = type;
        
        this.sortLogs();
    }
    
    public void addLog(XMLLog log) {
        this.logs.add(log);
        this.sortLogs();
    }
    
    public String getID() {
        return this.id;
    }
    
    public List<XMLLog> getLogs() {
        return this.logs;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public boolean removeFromXML() throws IOException, JDOMException {
        XMLFileIO io = new XMLFileIO(MOTDHandler.getBase().getFile());
        Element motdList = io.getDocument().getRootElement().getChild("list");
        
        boolean success = false;
        for (Element motd : motdList.getChildren()) {
            if (this.getID().equals(motd.getAttributeValue(id))) {
                success = true;
                
                motd.detach();
                break;
            }
        }
        
        if (success) {
            io.save();
            return success;
        }
        
        return false;
    }
    
    public void saveInXML() throws IOException, JDOMException {
        XMLFileIO io = new XMLFileIO(MOTDHandler.getBase().getFile());
        Element motdList = io.getDocument().getRootElement().getChild("list");
        
        Element motd = new Element("motd");
        motd.setAttribute("id", this.getID());
        motd.setAttribute("class", this.getType());
        motd.setText(this.getValue());
        
        io.save();
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    private void sortLogs() {
        Collections.sort(this.logs, new Comparator<XMLLog>() {
            @Override
            public int compare(XMLLog o1, XMLLog o2) {
                return ((Long) o1.getTime().getTime()).compareTo(o2.getTime().getTime());
            }
        });
    }
    
    public static XMLObject create(Element element) {
        String id = element.getAttributeValue("id", "unknown-" + Math.random() * 10000); // generate 4 random numbers
        String type = element.getAttributeValue("class", "Static");
        String value = element.getText();
        
        if (id == null || type == null || value == null) {
            return null;
        }
        
        List<XMLLog> logs = new ArrayList<>();
        for (Element log : element.getChildren()) {
            logs.add(XMLLog.create(log));
        }
        
        XMLObject object = new XMLObject(id, logs, type);
        object.setValue(value);
        return object;
    }
}
