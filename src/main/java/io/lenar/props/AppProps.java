package io.lenar.props;

import io.lenar.props.file.File;
import io.lenar.props.file.UserFile;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class AppProps {

    private static final String DEFAULT_FILE_NAME = "app-props.properties";

    private Set<File> files;

    private Properties properties;

    private boolean keepPropsFresh;

    public AppProps() {
        this(false);
        reload();
    }

    public AppProps(boolean keepPropsFresh) {
        this.keepPropsFresh = keepPropsFresh;
        this.files = new HashSet<>();
        userPropFile(DEFAULT_FILE_NAME);
        reload();
    }

    public AppProps userPropFile(String fileName) {
        files.add(new UserFile(fileName, keepPropsFresh));
        reload();
        return this;
    }

    public String value(String key) {
        if (keepPropsFresh) {
            reload();
        }
        return properties.getProperty(key);
    }

    public String value(String key, String defaultValue) {
        if (keepPropsFresh) {
            reload();
        }
        return properties.getProperty(key, defaultValue);
    }

    private void reload() {
        Properties newProps = new Properties();
        for (File file : files) {
            newProps.putAll(file.properties());
        }
        newProps.putAll(System.getProperties());
        this.properties = newProps;
    }
}
