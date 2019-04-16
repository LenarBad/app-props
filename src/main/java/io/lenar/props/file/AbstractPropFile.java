package io.lenar.props.file;

import java.util.Properties;

public abstract class AbstractPropFile {

    protected String fileName;
    protected boolean refreshEnabled;

    protected String absolutePath;

    public AbstractPropFile(String fileName, boolean refreshEnabled) {
        this.fileName = fileName;
        this.refreshEnabled = refreshEnabled;
    }

    public AbstractPropFile(String fileName) {
        this(fileName, false);
    }

    public abstract Properties properties();

    public boolean equals(AbstractPropFile propFile) {
        return this.absolutePath == propFile.absolutePath;
    }

}
