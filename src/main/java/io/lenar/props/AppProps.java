package io.lenar.props;

import io.lenar.props.file.PropFile;

import java.util.Properties;

public class AppProps {

    private PropFile file;

    private Properties properties;

    public AppProps(String fileName) {
        this.file = new PropFile(fileName);
        this.properties = this.file.properties();
    }

    public AppProps() {
        this.file = new PropFile();
        this.properties = this.file.properties();
    }

    public String value(String key) {
        return properties.getProperty(key);
    }

    public String value(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

}
