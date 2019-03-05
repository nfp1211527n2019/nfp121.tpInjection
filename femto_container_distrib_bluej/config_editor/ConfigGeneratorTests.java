package config_editor;

import container.ApplicationContext;
import container.Factory;
import java.util.Properties;
import java.util.*;

public class ConfigGeneratorTests extends junit.framework.TestCase{
    public void testConfigGenerator() throws Exception{
        ApplicationContext ctx = Factory.createApplicationContext("./config_editor/README.TXT");

        IConfigGenerator configGenerator = ctx.getBean("configGenerator1");
        configGenerator.analyze();
        System.out.println(configGenerator.getFormatter().get());
        assertTrue(configGenerator.getFormatter() instanceof PropertiesFormatter);
        PropertiesFormatter formatter = (PropertiesFormatter)configGenerator.getFormatter();
        Properties props = formatter.getProperties();
        // bean.id.1=test_generator
        // test_generator.class=config_editor.ConfigGenerator
        // test_generator.property.1=beanClassFileName
        // test_generator.property.1.param.1=a_string
        // test_generator.property.2=beanPrefixName
        // test_generator.property.2.param.1=a_string
        // test_generator.property.3=beanName
        // test_generator.property.3.param.1=a_string
        // test_generator.property.4=formatter
        // test_generator.property.4.param.1=null
        // test_generator.property.5=beanNumber
        // test_generator.property.5.param.1=0
        assertTrue(props.containsKey("bean.id.1"));
        assertTrue(props.containsValue("test_generator"));
        assertEquals("test_generator", props.getProperty("bean.id.1"));

        IConfigGenerator configGeneratorDir = ctx.getBean("configGeneratorDir");
        assertTrue(configGeneratorDir.getFormatter() instanceof PropertiesFormatter);

        configGeneratorDir.analyze();
        System.out.println(configGeneratorDir.getFormatter().get());
        formatter = (PropertiesFormatter)configGeneratorDir.getFormatter();
        props = formatter.getProperties();

        assertFalse(props.containsKey("bean.id.1"));
        assertFalse(props.containsKey("bean.id.2"));
        assertFalse(props.containsKey("bean.id.3"));
        assertFalse(props.containsKey("bean.id.4"));
        assertTrue(props.containsKey("bean.id.5"));
        assertTrue(props.containsKey("bean.id.10")); // au moins 10 beans

    }
}
