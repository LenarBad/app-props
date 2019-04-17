package io.lenar.props.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.io.File.pathSeparatorChar;

public class UserFile extends File {

    private static final Logger log = LoggerFactory.getLogger(UserFile.class);

    private static final String DEFAULT_DIR = System.getProperty("user.home");

    private Properties properties;

    public UserFile(String fileName) {
        super(fileName);
        this.absolutePath = DEFAULT_DIR + pathSeparatorChar + fileName;
        load();
    }

    @Override
    public Properties properties() {
        Properties newProps = new Properties();
        newProps.putAll(this.properties);
        return newProps;
    }

    private void load() {
        try (FileInputStream inputStream = new FileInputStream(new java.io.File(DEFAULT_DIR, fileName))) {
            Properties newProps = new Properties();
            newProps.load(inputStream);
            this.properties = newProps;
        } catch (IOException ex) {
            log.warn("Failed to load property file: {dir : {}, fileName: {}}", DEFAULT_DIR, this.fileName);
            this.properties = new Properties();
        }
    }

}
