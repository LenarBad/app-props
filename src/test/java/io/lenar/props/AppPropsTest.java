package io.lenar.props;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class AppPropsTest {

    @Test
    public void noExceptionsOnNonExistingFilesTest() {
        new AppProps("non-existing-file.properties");
    }

    @Test
    public void shouldReturnNullForNonExistingPropertyTest() {
        AppProps appProps = new AppProps();
        assertNull(appProps.value("nonexistingprop"));
    }

    @Test
    public void shouldApplyDefaultIfUsedForNonExistingPropertyTest() {
        AppProps appProps = new AppProps();
        assertEquals("default value", appProps.value("nonexistingprop", "default value"));
    }

    @Test
    public void envVariablesTest() {
        AppProps appProp = new AppProps();
        assertEquals(System.getProperty("user.home"), appProp.value("user.home"));
    }

}
