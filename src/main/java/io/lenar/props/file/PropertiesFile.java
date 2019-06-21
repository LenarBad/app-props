package io.lenar.props.file;

import io.lenar.files.Resource;

import static io.lenar.props.file.FileTypes.PROPERTIES;

public class PropertiesFile {

    private final Resource file;

    private final FileTypes type;

    public PropertiesFile(Resource file) {
        this(file, PROPERTIES);
    }

    public PropertiesFile(Resource file, FileTypes type) {
        this.file = file;
        this.type = type;
    }

    public FileTypes type() {
        return this.type;
    }

    public Resource file() {
        return this.file;
    }
}
