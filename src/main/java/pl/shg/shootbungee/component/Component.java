/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootbungee.component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.md_5.bungee.api.ProxyServer;
import pl.shg.shootbungee.BungeePlugin;

/**
 *
 * @author Aleksander
 */
public class Component {
    public BungeePlugin getPlugin() {
        return BungeePlugin.getPlugin();
    }
    
    public ProxyServer getProxy() {
        return ProxyServer.getInstance();
    }
    
    public static Component create(Class<? extends Component> component) {
        try {
            Method method = component.getDeclaredMethod("create", new Class<?>[0]);
            return (Component) method.invoke(null, new Object[0]);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException |
                IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Component.class.getName()).log(Level.SEVERE, "Nie udalo sie uruchomic " + component.getName(), ex);
        }
        return null;
    }
    
    public static void createAll() {
        for (Class<? extends Component> component : ComponentBase.COMPONENTS) {
            Component object = create(component);
            
            ProxyServer.getInstance().getLogger().log(
                    Level.INFO,
                    "Uruchomiono {0}.",
                    object.getClass().getName()
            );
        }
    }
}
