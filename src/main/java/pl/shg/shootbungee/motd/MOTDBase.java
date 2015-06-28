/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.motd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.JDOMException;
import pl.shg.shootbungee.motd.xml.XMLFileIO;
import pl.shg.shootbungee.motd.xml.XMLObject;
import pl.shg.shootbungee.motd.xml.XMLProperties;

/**
 *
 * @author Aleksander
 */
public class MOTDBase {
    private File file;
    private final List<AbstractMOTD> motds = new ArrayList<>();
    
    public MOTDBase(File file) {
        this.file = file;
    }
    
    public boolean addMOTD(AbstractMOTD motd) {
        return this.motds.add(motd);
    }
    
    public void clear() {
        this.motds.clear();
    }
    
    public AbstractMOTD findMOTD(String id) {
        if (id.equals(DefaultMOTD.ID)) {
            return new DefaultMOTD();
        }
        
        for (AbstractMOTD motd : this.getMOTDs()) {
            if (motd.getID().toLowerCase().contains(id.toLowerCase())) {
                return motd;
            }
        }
        return null;
    }
    
    public AbstractMOTD getMOTD(String id) {
        if (id.equals(DefaultMOTD.ID)) {
            return new DefaultMOTD();
        }
        
        for (AbstractMOTD motd : this.getMOTDs()) {
            if (motd.getID().equals(id)) {
                return motd;
            }
        }
        return null;
    }
    
    public List<AbstractMOTD> getMOTDs() {
        return this.motds;
    }
    
    public File getFile() {
        return this.file;
    }
    
    public boolean removeMOTD(AbstractMOTD motd) {
        return this.motds.remove(motd);
    }
    
    public void setFile(File file) {
        this.file = file;
    }
    
    public boolean reload() throws IOException, JDOMException {
        this.clear();
        XMLFileIO io = new XMLFileIO(this.getFile());
        
        for (XMLObject object : io.load()) {
            AbstractMOTD motd = null;
            switch (object.getType().toLowerCase()) {
                case "editable":
                    motd = new EditableMOTD(object.getID(), object.getLogs());
                    break;
                case "static":
                    motd = new StaticMOTD(object.getID(), object.getLogs());
                    break;
            }
            
            if (motd != null) {
                motd.setValue(object.getValue());
                this.addMOTD(motd);
            }
        }
        
        XMLProperties properties = io.loadProperties();
        MOTDHandler.setMOTD(properties.getCurrent());
        
        return true;
    }
}
