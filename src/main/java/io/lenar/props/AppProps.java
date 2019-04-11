package io.lenar.props;

import io.lenar.props.file.PropFile;

import java.util.Properties;

public class AppProps {

    private static final String DEFAULT_FILE_NAME = "app-props.properties";

    private boolean keepPropsFresh = false;

    private PropFile file;

    private Properties properties;

    public AppProps() {
        this(DEFAULT_FILE_NAME);
    }

    public AppProps(boolean keepPropsFresh) {
        this(DEFAULT_FILE_NAME);
        this.keepPropsFresh = keepPropsFresh;
    }

    public AppProps(String fileName) {
        this.file = new PropFile(fileName);
        this.properties = this.file.properties();
        properties.putAll(System.getProperties());
    }

    public AppProps(String fileName, boolean keepPropsFresh) {
        this(fileName);
        this.keepPropsFresh = keepPropsFresh;
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
        this.file.reload();
        Properties newProps = this.file.properties();
        newProps.putAll(System.getProperties());
        this.properties = newProps;
    }
}
