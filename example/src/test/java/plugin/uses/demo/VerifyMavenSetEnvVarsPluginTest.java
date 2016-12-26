package plugin.uses.demo;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 12/26/2016
 */
public class VerifyMavenSetEnvVarsPluginTest {

    @Test
    public void testSystemEnvironmentVarsSetByPluginUsingMap() {

        assertEquals("Hello", System.getProperty("a"));
    }

    @Test
    public void testSystemEnvironmentVarsSetByPluginUsingPropertiesFile() {
        assertEquals("World", System.getProperty("b"));
    }

}
