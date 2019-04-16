package io.lenar.props.file;

import java.util.Properties;

public abstract class File {

    protected String fileName;
    protected boolean refreshEnabled;

    protected String absolutePath;

    public File(String fileName, boolean refreshEnabled) {
        this.fileName = fileName;
        this.refreshEnabled = refreshEnabled;
    }

    public File(String fileName) {
        this(fileName, false);
    }

    public abstract Properties properties();

    public boolean equals(File propFile) {
        return this.absolutePath == propFile.absolutePath;
    }

}
