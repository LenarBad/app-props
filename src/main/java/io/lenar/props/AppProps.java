package io.lenar.props;

import io.lenar.props.file.PropFile;

import java.util.Properties;

public class AppProps {

    private static final String DEFAULT_FILE_NAME = "app-props.properties";

    private PropFile file;

    private Properties properties;

    public AppProps() {
        this(DEFAULT_FILE_NAME);
    }

    public AppProps(String fileName) {
        this.file = new PropFile(fileName);
        this.properties = this.file.properties();
        properties.putAll(System.getProperties());
    }

    public String value(String key) {
        return properties.getProperty(key);
    }

    public String value(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

}
