package io.lenar.props;

import io.lenar.props.file.PropFile;

import java.util.*;

public class AppProps {

    private List<PropFile> propFiles;

    private Properties properties;

    public AppProps() {
        this.propFiles = new ArrayList<>();
        reload();
    }

    public AppProps propFile(String fileName) {
        propFiles.add(new PropFile(fileName));
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
        for (PropFile propertyFile : propFiles) {
            newProps.putAll(propertyFile.properties());
        }
        newProps.putAll(System.getProperties());
        this.properties = newProps;
    }
}
