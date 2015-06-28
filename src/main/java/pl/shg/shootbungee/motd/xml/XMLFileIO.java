/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.motd.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import pl.shg.shootbungee.motd.MOTDHandler;

/**
 *
 * @author Aleksander
 */
public class XMLFileIO {
    private final File file;
    private final Document document;
    private XMLProperties properties;
    
    public XMLFileIO(File file) throws IOException, JDOMException {
        this.file = file;
        if (!file.exists()) {
            Files.createFile(file.toPath());
        }
        
        this.document = (Document) new SAXBuilder().build(file);
    }
    
    public File getFile() {
        return this.file;
    }
    
    public Document getDocument() {
        return this.document;
    }
    
    public XMLProperties getProperties() {
        return this.properties;
    }
    
    public List<XMLObject> load() {
        List<XMLObject> list = new ArrayList<>();
        
        for (Element motd : this.getDocument().getRootElement().getChild("list").getChildren()) {
            list.add(XMLObject.create(motd));
        }
        
        return list;
    }
    
    public XMLProperties loadProperties() {
        Element element = this.getDocument().getRootElement().getChild("properties");
        XMLProperties xmlProperties = new XMLPropertiesBuilder()
                .current(MOTDHandler.getBase().getMOTD(element.getAttributeValue("current")))
                .build();
        
        this.setProperties(xmlProperties);
        return xmlProperties;
    }
    
    public void save() throws IOException {
        XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
        out.output(this.getDocument(), new FileOutputStream(this.getFile()));
    }
    
    public void setProperties(XMLProperties properties) {
        this.properties = properties;
    }
}
