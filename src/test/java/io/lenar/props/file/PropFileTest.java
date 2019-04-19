package io.lenar.props.file;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PropFileTest {

    @Test
    public void ioExceptionOnConstructorShouldProduceyEmptyPropertiesTest() {
        PropFile propFile = new PropFile("non-existing-file.properties");
        assertNotNull(propFile.properties());
        assertTrue(propFile.properties().isEmpty());
    }

    @Test
    public void youShouldntBeAbleToChangePropertiesFieldTest() {
        PropFile propFile = new PropFile("non-existing-file.properties");
        propFile.properties().setProperty("newProp", "newPropValue");
        assertTrue(propFile.properties().isEmpty());
    }
}
