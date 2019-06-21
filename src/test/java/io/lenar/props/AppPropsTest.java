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

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AppPropsTest {

    @Test
    public void noExceptionsOnNonExistingResourceFileTest() {
        new AppProps().resourcePropFile("non-existing-file.properties");
    }

    @Test
    public void noExceptionsOnNonExistingFileSystemFileTest() {
        new AppProps().fileSystemPropFile("non-existing-file.properties");
    }

    @Test
    public void noExceptionsOnNonExistingHomeDirFileTest() {
        AppProps appProps = new AppProps();
        appProps.homeDirPropFile("non-existing-file.properties");
        assertNotNull(appProps.value("user.home"));
    }

    @Test
    public void shouldReturnNullForNonExistingPropertyTest() {
        AppProps appProps = new AppProps();
        assertNull(appProps.value("nonexistingprop"));
    }

    @Test
    public void shouldApplyDefaultIfUsedForNonExistingPropertyTest() {
        AppProps appProps = new AppProps();
        assertEquals(appProps.value("nonexistingprop", "default value"), "default value");
    }

    @Test
    public void envVariablesTest() {
        AppProps appProp = new AppProps();
        assertEquals(appProp.value("user.home"), System.getProperty("user.home"));
    }

    @Test
    public void changeEnvPropertyWithoutReloadTest() {
        System.setProperty("testProperty", "Test Value Value");
        AppProps appProps = new AppProps();
        System.setProperty("testProperty", "New Test Value Value");
        assertEquals(appProps.value("testProperty"), "Test Value Value");
    }

    @Test
    public void changeEnvPropertyAndReloadTest() {
        System.setProperty("testProperty", "Test Value Value");
        AppProps appProps = new AppProps();
        System.setProperty("testProperty", "New Test Value Value");
        appProps.reload();
        assertEquals(appProps.value("testProperty"), "New Test Value Value");
    }

    @Test
    public void resourcesPropFileTest() {
        AppProps appProps = new AppProps();
        appProps.resourcePropFile("test-resources-prop-file.properties");
        assertEquals(appProps.value("some-test-prop-in-test-resources-prop-file"), "testValue");
    }

    @Test
    public void resourcesPropFileWithoutAutoReloadTest() {
        AppProps appProps = new AppProps(false);
        appProps.resourcePropFile("test-resources-prop-file.properties");
        assertNull(appProps.value("some-test-prop-in-test-resources-prop-file"));
    }
}
