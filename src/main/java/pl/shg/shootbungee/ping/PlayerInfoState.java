/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.ping;

import java.util.List;
import java.util.UUID;
import net.md_5.bungee.api.ServerPing.PlayerInfo;

/**
 *
 * @author Aleksander
 */
public class PlayerInfoState {
    public static PlayerInfo[] toArray() {
        List<String> infoState = SimpleInfoState.getCustomState();
        PlayerInfo[] array = new PlayerInfo[infoState.size()];
        
        for (int i = 0; i < infoState.size(); i++) {
            array[i] = new PlayerInfo(infoState.get(i), UUID.randomUUID());
        }
        
        return array;
    }
    
    public static PlayerInfo[] createFor() {
        return toArray();
    }
}
