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

import java.io.IOException;
import java.util.*;

public class AppProps {

    private Map<String, Object> propMap;

    public AppProps() {
        this.propMap = new HashMap<>();
        reloadEnvironment();
    }

     public AppProps addProperties(Source source) throws IOException {
        source.properties().entrySet().stream().forEach(entry -> {
            propMap.put(entry.getKey().toString(), entry.getValue().toString());
        });
         reloadEnvironment();
        return this;
    }

    public AppProps addPropertiesOptional(Source source) {
        try {
            source.properties().entrySet().stream().forEach(entry -> {
                propMap.put(entry.getKey().toString(), entry.getValue().toString());
                reloadEnvironment();
            });
        } catch (IOException ex) {
            // Do nothing since it's optional
        }
        return this;
    }

    public AppProps addJsonProperty(Source source, String name, Class clazz) throws IOException {
        propMap.put(name, source.fromJson(clazz));
        reloadEnvironment();
        return this;
    }

    public <T> AppProps addJsonPropertyAsList(Source source, String name,  Class<T[]> clazz) throws IOException {
        propMap.put(name, source.fromJsonAsList(clazz));
        reloadEnvironment();
        return this;
    }

    public AppProps addYamlProperty(Source source, String name, Class clazz) throws IOException {
        propMap.put(name, source.fromYaml(clazz));
        reloadEnvironment();
        return this;
    }

    public <T> AppProps addYamlPropertyAsList(Source source, String name,  Class<T> clazz) throws IOException {
        propMap.put(name, source.fromYamlAsList(clazz));
        reloadEnvironment();
        return this;
    }

    public <T> List<T> valueAsListOf(String key, Class<T[]> clazz) {
        return (List<T>) propMap.get(key) ;
    }

    public Object valueObject(String key) {
        return propMap.get(key);
    }

    public <T> T valueAs(String key, Class<T> clazz) {
        return (T) propMap.get(key);
    }

    public String value(String key) {
        return propMap.containsKey(key) ? propMap.get(key).toString() : null;
    }

    public String value(String key, String defaultValue) {
        return propMap.getOrDefault(key, defaultValue).toString();
    }

    public void reloadEnvironment() {
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
