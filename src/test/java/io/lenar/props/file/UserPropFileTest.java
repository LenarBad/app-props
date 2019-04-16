package io.lenar.props.file;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserPropFileTest {

    @Test
    public void ioExceptionOnConstructorShouldProduceyEmptyPropertiesTest() {
        UserPropFile propFile = new UserPropFile("non-existing-file.properties");
        assertNotNull(propFile.properties());
        assertTrue(propFile.properties().isEmpty());
    }

    @Test
    public void youShouldntBeAbleToChangePropertiesFieldTest() {
        UserPropFile propFile = new UserPropFile("non-existing-file.properties");
        propFile.properties().setProperty("newProp", "newPropValue");
        assertTrue(propFile.properties().isEmpty());
    }
}
