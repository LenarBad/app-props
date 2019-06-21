package io.lenar.props.file;

import io.lenar.files.Resource;

import java.io.IOException;

public class JsonFile extends PropertiesFile {

    private final String propName;
//    private final Class aClass;
    private Object value;

    public <T> JsonFile(Resource file, String propName, Class<T> clazz) throws IOException {
        super(file, FileTypes.SINGLE_PROP_JSON);
        this.propName = propName;
//        this.aClass = clazz;
        this.value = this.file().fromJson(clazz);
    }

    public String propName() {
        return this.propName;
    }

    public Object value() {
        return this.value;
    }
}
