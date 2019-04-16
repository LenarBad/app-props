package io.lenar.props;

import io.lenar.props.file.AbstractPropFile;
import io.lenar.props.file.UserPropFile;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class NewAppProps {

    private static final String DEFAULT_FILE_NAME = "app-props.properties";

    private Set<AbstractPropFile> files;

    private Properties properties;

    private boolean keepPropsFresh;

    public NewAppProps() {
        this(false);
        reload();
    }

    public NewAppProps(boolean keepPropsFresh) {
        this.keepPropsFresh = keepPropsFresh;
        this.files = new HashSet<>();
        userPropFile(DEFAULT_FILE_NAME);
        reload();
    }

    public NewAppProps userPropFile(String fileName) {
        files.add(new UserPropFile(fileName, keepPropsFresh));
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
        for (AbstractPropFile file : files) {
            newProps.putAll(file.properties());
        }
        newProps.putAll(System.getProperties());
        this.properties = newProps;
    }
}
