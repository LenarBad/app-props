package io.lenar.props.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserPropFile extends AbstractPropFile {

    private static final Logger log = LoggerFactory.getLogger(UserPropFile.class);

    private static final String DEFAULT_DIR = System.getProperty("user.home");

    private Properties properties;

    public UserPropFile(String fileName) {
        super(fileName);
        this.absolutePath = DEFAULT_DIR + fileName;
        load();
    }

    public UserPropFile(String fileName, boolean refreshEnabled) {
        super(fileName, refreshEnabled);
        this.absolutePath = DEFAULT_DIR + fileName;
        load();
    }

    @Override
    public Properties properties() {
        refreshIfEnabled();
        Properties newProps = new Properties();
        newProps.putAll(this.properties);
        return newProps;
    }

    private void load() {
        try (FileInputStream inputStream = new FileInputStream(new File(DEFAULT_DIR, fileName))) {
            Properties newProps = new Properties();
            newProps.load(inputStream);
            this.properties = newProps;
        } catch (IOException ex) {
            log.warn("Failed to load property file: {dir : {}, fileName: {}}", DEFAULT_DIR, this.fileName);
            this.properties = new Properties();
        }
    }

    private void refreshIfEnabled() {
        if (this.refreshEnabled) {
            load();
        }
    }

}
