package org.example.utils;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesManager {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties(){
        try(var stream = PropertiesManager
                .class
                .getClassLoader()
                .getResourceAsStream("application.properties")
        ){
            PROPERTIES.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }

    private PropertiesManager(){

    }
}
