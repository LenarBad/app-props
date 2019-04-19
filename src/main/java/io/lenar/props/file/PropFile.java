package io.lenar.props.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;


public class PropFile {

    private static final Logger log = LoggerFactory.getLogger(PropFile.class);
    private static final String USER_HOME_DIR = System.getProperty("user.home");

    private final String fileName;
    private final File file;

    private Properties properties;

    public PropFile(String fileName) {
        this.fileName = fileName;
        this.file = getFile(fileName);
        load();
    }

    public Properties properties() {
        Properties newProps = new Properties();
        newProps.putAll(this.properties);
        return newProps;
    }

    private void load() {
        Properties newProps = new Properties();
        if (this.file != null) {
            try (FileInputStream inputUserFileStream = new FileInputStream(this.file)) {
                newProps.load(inputUserFileStream);
            } catch (IOException ex) {
                log.warn("Failed to load property file: {}}", this.file.toURI());
            }
        }
        this.properties = newProps;
    }

    private File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(fileName);
        if (url != null) {
            return new File(url.getFile());
        }
        File file = new File(USER_HOME_DIR, fileName).getAbsoluteFile();
        if (!file.exists()) {
            log.warn("Couldn't find property file {}", fileName);
        }
        return file.exists() ? file : null;
    }

}
