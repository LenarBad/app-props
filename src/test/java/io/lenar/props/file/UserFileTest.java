package io.lenar.props.file;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserFileTest {

    @Test
    public void ioExceptionOnConstructorShouldProduceyEmptyPropertiesTest() {
        UserFile propFile = new UserFile("non-existing-file.properties");
        assertNotNull(propFile.properties());
        assertTrue(propFile.properties().isEmpty());
    }

    @Test
    public void youShouldntBeAbleToChangePropertiesFieldTest() {
        UserFile propFile = new UserFile("non-existing-file.properties");
        propFile.properties().setProperty("newProp", "newPropValue");
        assertTrue(propFile.properties().isEmpty());
    }
}
