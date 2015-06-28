/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.motd;

import java.util.List;
import pl.shg.shootbungee.editor.Editor;
import pl.shg.shootbungee.motd.xml.XMLLog;

/**
 *
 * @author Aleksander
 */
public class EditableMOTD extends AbstractMOTD {
    public EditableMOTD(String id, List<XMLLog> logs) {
        super(id, logs, "Editable");
    }
    
    @Override
    public boolean edit(Editor editor, String edit) {
        if (edit == null || edit.equals("%rm")) {
            this.setValue("");
        } else {
            this.setValue("");
        }
        return true;
    }
    
    @Override
    public boolean isEditable() {
        return true;
    }
}
