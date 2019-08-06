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

import io.lenar.files.ResourceFile;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

public class AppPropsTest {

    @Test
    public void simpleJsonPropFileValueObjectTest() throws IOException {
        AppProps props = new AppProps()
                .addJsonProperty(new ResourceFile("json-props.json"), "testObject", TestObject.class);
        TestObject testObject = (TestObject) props.valueObject("testObject");
        assertEquals(testObject.getValue(), "value 1");
    }

    @Test
    public void simpleYamlPropFileValueObjectTest() throws IOException {
        AppProps props = new AppProps()
                .addYamlProperty(new ResourceFile("yaml-props.yml"), "testObject", TestObject.class);
        TestObject testObject = (TestObject) props.valueObject("testObject");
        assertEquals(testObject.getValue(), "value 1");
    }

    @Test
    public void simpleJsonPropFileTestValueAsTest() throws IOException {
        AppProps props = new AppProps()
                .addJsonProperty(new ResourceFile("json-props.json"), "testObject", TestObject.class);
        TestObject testObject = props.valueAs("testObject", TestObject.class);
        assertEquals(testObject.getValue(), "value 1");
    }

    @Test
    public void simpleYamlPropFileTestValueAsTest() throws IOException {
        AppProps props = new AppProps()
                .addYamlProperty(new ResourceFile("yaml-props.yml"), "testObject", TestObject.class);
        TestObject testObject = props.valueAs("testObject", TestObject.class);
        assertEquals(testObject.getValue(), "value 1");
    }

    @Test
    public void jsonPropFileTestvalueAsListOfTest() throws IOException {
        AppProps props = new AppProps()
                .addJsonPropertyAsList(new ResourceFile("json-props-as-list.json"), "testObjects", TestObject[].class);
        List<TestObject> testObjects = props.valueAsListOf("testObjects", TestObject[].class);
        assertNotNull(testObjects);
        assertEquals(testObjects.size(), 2);
        assertEquals(testObjects.get(0).getValue(), "value 1");
        assertEquals(testObjects.get(1).getValue(), "value 2");
    }

    @Test
    public void yamlPropFileTestvalueAsListOfTest() throws IOException {
        AppProps props = new AppProps()
                .addYamlPropertyAsList(new ResourceFile("yaml-props-as-list.yml"), "testObjects", TestObject.class);
        List<TestObject> testObjects = props.valueAsListOf("testObjects", TestObject[].class);
        assertNotNull(testObjects);
        assertEquals(testObjects.size(), 2);
        assertEquals(testObjects.get(0).getValue(), "value 1");
        assertEquals(testObjects.get(1).getValue(), "value 2");
    }

    @Test
    public void nonExistingFileAddPropertiesOptionalTest() {
        new AppProps().addPropertiesOptional(new ResourceFile("non-existing-file.properties"));
    }

    @Test(expectedExceptions = {IOException.class})
    public void nonExistingFilePropertiesTest() throws IOException {
        new AppProps().addProperties(new ResourceFile("non-existing-file.properties"));
    }

    @Test
    public void shouldReturnNullForNonExistingPropertyTest() {
        AppProps appProps = new AppProps();
        assertNull(appProps.value("nonexistingprop"));
    }

    @Test
    public void shouldApplyDefaultValueIfUsedForNonExistingPropertyTest() {
        AppProps appProps = new AppProps();
        assertEquals(appProps.value("nonexistingprop", "default value"), "default value");
    }

    @Test
    public void resourcesPropFileTest() throws IOException {
        AppProps appProps = new AppProps();
        appProps.addProperties(new ResourceFile("test-resources-prop-file.properties"));
        assertEquals(appProps.value("some-test-prop-in-test-resources-prop-file"), "testValue");
    }

    @Test
    public void envVariablesTest() {
        AppProps appProp = new AppProps();
        assertEquals(appProp.value("user.home"), System.getProperty("user.home"));
    }

    @Test
    public void reloadEnvironmentTest() {
        System.setProperty("testProperty", "Test Value Value");
        AppProps appProps = new AppProps();
        System.setProperty("testProperty", "New Test Value Value");
        appProps.reloadEnvironment();
        assertEquals(appProps.value("testProperty"), "New Test Value Value");
    }

    @Test
    public void filesShouldntOvewriteEnvPropsTest() throws IOException {
        System.setProperty("try-to-override-env", "Env Prop Value");
        AppProps appProp = new AppProps();
        appProp.addProperties(new ResourceFile("test-resources-prop-file.properties"));
        assertEquals(appProp.value("try-to-override-env"), "Env Prop Value");
    }

}
