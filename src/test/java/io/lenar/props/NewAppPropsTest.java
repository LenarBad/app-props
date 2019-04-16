package io.lenar.props;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class NewAppPropsTest {

    @Test
    public void noExceptionsOnNonExistingFilesTest() {
        new NewAppProps().userPropFile("non-existing-file.properties");
    }

    @Test
    public void shouldReturnNullForNonExistingPropertyTest() {
        NewAppProps appProps = new NewAppProps();
        assertNull(appProps.value("nonexistingprop"));
    }

    @Test
    public void shouldApplyDefaultIfUsedForNonExistingPropertyTest() {
        NewAppProps appProps = new NewAppProps();
        assertEquals(appProps.value("nonexistingprop", "default value"), "default value");
    }

    @Test
    public void envVariablesTest() {
        NewAppProps appProp = new NewAppProps();
        assertEquals(appProp.value("user.home"), System.getProperty("user.home"));
    }

    @Test
    public void refreshEnvPropertyWhenKeepFreshEnabled() {
        System.setProperty("testProperty", "Test Property Value");
        NewAppProps appProps = new NewAppProps(true);
        System.setProperty("testProperty", "New Test Property Value");
        assertEquals(appProps.value("testProperty"), "New Test Property Value");
    }

    @Test
    public void refreshEnvPropertyWhenKeepFreshDisabled() {
        System.setProperty("testProperty", "Test Property Value");
        NewAppProps appProps = new NewAppProps(false);
        System.setProperty("testProperty", "New Test Property Value");
        assertEquals(appProps.value("testProperty"), "Test Property Value");
    }

}
