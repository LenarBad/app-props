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

import com.google.gson.Gson;
import io.lenar.files.*;
import io.lenar.props.file.JsonFile;
import io.lenar.props.file.PropertiesFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static io.lenar.props.file.FileTypes.*;

public class AppPropsNew {

    private static final Logger log = LoggerFactory.getLogger(AppPropsNew.class);

    private Map<String, Object> propMap;

    public AppPropsNew() {
        this(true);
    }

    public AppPropsNew(boolean autoReload) {
        this.propMap = new HashMap<>();
        reloadEnvironment();
    }

     public AppPropsNew addProperties(Resource file) throws IOException {
        file.properties().entrySet().stream().forEach(entry -> {
            propMap.put(entry.getKey().toString(), entry.getValue().toString());
        });
        return this;
    }

    public AppPropsNew addJsonProperty(Resource file, String name, Class clazz) throws IOException {
        propMap.put(name, file.fromJson(clazz));
        return this;
    }

    public Object valueObject(String key) {
        return propMap.get(key);
    }

    public String value(String key) {
        return propMap.get(key).toString();
    }

    public String value(String key, String defaultValue) {
        return propMap.getOrDefault(key, defaultValue).toString();
    }

    private void reloadEnvironment() {
        propMap.putAll(System.getenv());
        propMap.putAll(propertiesToMap(System.getProperties()));
    }

    private Map<String, String> propertiesToMap(Properties properties) {
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            map.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return map;
    }
}
