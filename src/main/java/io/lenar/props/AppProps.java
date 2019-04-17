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


    public AppProps() {
        this.files = new HashSet<>();
        userPropFile(DEFAULT_FILE_NAME);
        reload();
    }

    public AppProps userPropFile(String fileName) {
        files.add(new UserFile(fileName));
        reload();
        return this;
    }

    public String value(String key) {
        return properties.getProperty(key);
    }

    public String value(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public void reload() {
        Properties newProps = new Properties();
        for (File file : files) {
            newProps.putAll(file.properties());
        }
        newProps.putAll(System.getProperties());
        this.properties = newProps;
    }
}
