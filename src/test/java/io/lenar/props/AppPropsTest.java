package io.lenar.props;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class AppPropsTest {

    @Test
    public void noExceptionsOnNonExistingFilesTest() {
        new AppProps().userPropFile("non-existing-file.properties");
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
    public void refreshEnvPropertyWhenKeepFreshEnabled() {
        System.setProperty("testProperty", "Test Property Value");
        AppProps appProps = new AppProps(true);
        System.setProperty("testProperty", "New Test Property Value");
        assertEquals(appProps.value("testProperty"), "New Test Property Value");
    }

    @Test
    public void refreshEnvPropertyWhenKeepFreshDisabled() {
        System.setProperty("testProperty", "Test Property Value");
        AppProps appProps = new AppProps(false);
        System.setProperty("testProperty", "New Test Property Value");
        assertEquals(appProps.value("testProperty"), "Test Property Value");
    }

}
