package syntaxe_exemples;

import config_editor.IConfigGenerator;
import config_editor.ConfigGenerator;
import config_editor.Formatter;
import config_editor.PropertiesFormatter;

public class MainConfigGeneratorTests{
  
    public static void main(String[] args) throws Exception{
        IConfigGenerator  configGenerator = new ConfigGenerator();
        configGenerator.setBeanNumber(1);

        Formatter formatter = new PropertiesFormatter();
        configGenerator.setFormatter(formatter);
        PropertiesFormatter propertiesFormatter = (PropertiesFormatter)configGenerator.getFormatter();
        //System.out.println(configGenerator.analyze("syntaxe_exemples/").getFormatter().get());

        configGenerator.setBeanName("a");
        configGenerator.setBeanClassFileName("syntaxe_exemples.A");
        System.out.println(configGenerator.analyze().getFormatter().get());
        
        configGenerator.setBeanName("table");
        configGenerator.setBeanClassFileName("syntaxe_exemples.Table");
        System.out.println(configGenerator.analyze().getFormatter().get());
        
        // configGenerator.setBeanName("listeDeTables");
        // configGenerator.setBeanClassName("syntaxe_exemples.ListeDeTables");
        // System.out.println(configGenerator.analyze().getFormatter().get());
    }
}
