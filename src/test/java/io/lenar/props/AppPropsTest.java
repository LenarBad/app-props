package io.lenar.props;

import io.lenar.props.AppProps;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class AppPropsTest {

    @Test
    public void noExceptionsOnNonExistingFilesTest() {
        new AppProps("non-existing-file.properties");
    }

    @Test
    public void shouldReturnNullForNonExistingProperty() {
        AppProps appProps = new AppProps();
        assertNull(appProps.value("nonexistingprop"));
    }

    @Test
    public void shouldApplyDefaultIfUsedForNonExistingProperty() {
        AppProps appProps = new AppProps();
        assertEquals("default value", appProps.value("nonexistingprop", "default value"));
    }

}
