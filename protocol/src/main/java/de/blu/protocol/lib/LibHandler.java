package de.blu.protocol.lib;

import lombok.Getter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;

public class LibHandler {

    @Getter
    private File libDirectory;

    public LibHandler() {
        try {
            File rootDirectory = null;

            try {
                rootDirectory = new File(LibHandler.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile();
                if (!rootDirectory.isDirectory()) {
                    rootDirectory.mkdir();
                }

                System.out.println(rootDirectory.getAbsolutePath());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            this.libDirectory = new File(rootDirectory, "libs");
            if (!this.libDirectory.isDirectory()) {
                this.libDirectory.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addLib(String urlString, String libName) {
        try {
            // Download Lib
            File file = new File(this.libDirectory, libName + ".jar");
            if (!file.exists()) {
                FileUtils.copyURLToFile(new URL(urlString), file);
            }

            // Load Lib
            URL url = file.toURI().toURL();

            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            method.setAccessible(true);
            method.invoke(classLoader, url);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | IOException e) {
            e.printStackTrace();
        }
    }
}
