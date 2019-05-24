/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Lenar Badretdinov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.lenar.props;

import io.lenar.files.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public class AppProps {

    private static final Logger log = LoggerFactory.getLogger(AppProps.class);

    private boolean autoReload;

    private List<Resource> propertyFiles;

    private Properties properties;

    public AppProps() {
        this(true);
    }

    public AppProps(boolean autoReload) {
        this.propertyFiles = new ArrayList<>();
        this.properties = new Properties();
        this.autoReload = autoReload;
        if (this.autoReload) {
            reload();
        }
    }

    public AppProps homeDirPropFile(String fileName) {
        propertyFiles.add(new UserHomeFile(fileName));
        if (this.autoReload) {
            reload();
        }
        return this;
    }

    public AppProps resourcePropFile(String fileName) {
        propertyFiles.add(new ResourceFile(fileName));
        if (this.autoReload) {
            reload();
        }
        return this;
    }

    public AppProps fileSystemPropFile(String fileName) {
        propertyFiles.add(new UserFile(fileName));
        if (this.autoReload) {
            reload();
        }
        return this;
    }

    public AppProps networkPropResource(String url) {
        propertyFiles.add(new NetworkResource(url));
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
        for (Resource file : propertyFiles) {
            try {
                newProps.putAll(file.properties());
            } catch (IOException e) {
                log.warn("Couldn't find properties file... {}");
                e.printStackTrace();
            }
        }
        newProps.putAll(System.getProperties());
        this.properties = newProps;
    }
}
