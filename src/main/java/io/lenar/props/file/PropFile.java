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
package io.lenar.props.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;


public class PropFile {

    private static final Logger log = LoggerFactory.getLogger(PropFile.class);
    private static final String USER_HOME_DIR = System.getProperty("user.home");

    private final File file;

    private Properties properties;

    public PropFile(String fileName) {
        this.file = getFile(fileName);
        load();
    }

    public Properties properties() {
        Properties newProps = new Properties();
        newProps.putAll(this.properties);
        return newProps;
    }

    private void load() {
        Properties newProps = new Properties();
        if (this.file != null) {
            try (FileInputStream inputUserFileStream = new FileInputStream(this.file)) {
                newProps.load(inputUserFileStream);
            } catch (IOException ex) {
                log.warn("Failed to load property file: {}}", this.file.toURI());
            }
        }
        this.properties = newProps;
    }

    private File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(fileName);
        if (url != null) {
            return new File(url.getFile());
        }
        File file = new File(USER_HOME_DIR, fileName).getAbsoluteFile();
        if (!file.exists()) {
            log.warn("Couldn't find property file {}", fileName);
        }
        return file.exists() ? file : null;
    }

}
