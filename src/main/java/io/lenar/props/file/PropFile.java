package io.lenar.props.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropFile {

    private static final Logger log = LoggerFactory.getLogger(PropFile.class);


    private static final String DEFAULT_DIR = System.getProperty("user.home");

    private String dir;
    private String fileName;
    private Properties properties;

    public PropFile(String fileName) {
        dir = DEFAULT_DIR;
        this.fileName = fileName;
        this.properties = new Properties();
        reload();
    }

    public Properties properties() {
        return this.properties;
    }

    public void reload() {
        try (FileInputStream inputStream = new FileInputStream(new File(dir, fileName))) {
            this.properties.load(inputStream);
        } catch (IOException ex) {
            log.warn("Failed to load property file: {dir : {}, fileName: {}}", this.dir, this.fileName);
        }
    }

}
