package com.lzw.Utils;

import java.net.URL;

public class Utils {

    public Utils() {
    }
    
    public static URL getResourceURL(String name) {
        return Utils.class.getClassLoader().getResource(name);
    }

}
