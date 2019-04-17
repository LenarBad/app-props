package io.lenar.props.file;

import java.util.Properties;

public abstract class File {

    protected final String fileName;

    protected String absolutePath;

    public File(String fileName) {
        this.fileName = fileName;
    }

    public abstract Properties properties();

    public boolean equals(File propFile) {
        return this.absolutePath == propFile.absolutePath;
    }

}
