package io.lenar.props.file;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PropFileTest {

    @Test
    public void ioExceptionOnConstructorShouldProduceyEmptyPropertiesTest() {
        PropFile propFile = new PropFile("non-existing-file.properties");
        assertNotNull(propFile.properties());
        assertTrue(propFile.properties().entrySet().isEmpty());
    }

    @Test
    public void ioExceptionOnReloadShouldNotChangePropertiesTest() {
        PropFile propFile = new PropFile("non-existing-file.properties");
        propFile.properties().setProperty("newProp", "newPropValue");

        propFile.reload();

        assertEquals(1, propFile.properties().entrySet().size());
    }

}
