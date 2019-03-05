package config_editor;

import java.util.*;
import java.io.*;
import java.net.URI;
import java.awt.Desktop;

public class ConfigGeneratorMainTests{
    
   public static void testConfigGeneratorGUI() throws Exception{
       IConfigGenerator  configGenerator = new ConfigGenerator();
       configGenerator.setBeanName("configGeneratorGUI");
       configGenerator.setBeanClassFileName("config_editor.ConfigGeneratorGUI");
       configGenerator.analyze();
       System.out.println(configGenerator.getFormatter().get());
   }
   
   public static void main(String[] args) throws Exception{
       
        IConfigGenerator  configGenerator = new ConfigGenerator();
        configGenerator.setBeanNumber(1);
        
        Formatter formatter = new PropertiesFormatter();
        configGenerator.setFormatter(formatter);
        configGenerator.setBeanName("configGenerator");
        configGenerator.setBeanClassFileName("config_editor.ConfigGenerator");
        configGenerator.analyze();
        System.out.println(configGenerator.getFormatter().get());
        
        
        configGenerator.setBeanName("configGeneratorGUI");
        configGenerator.setBeanClassFileName("config_editor.ConfigGeneratorGUI");
        configGenerator.analyze();
        System.out.println(configGenerator.getFormatter().get());

        //System.out.println(propertiesFormatter.getProperties());

        configGenerator = new ConfigGenerator();
        configGenerator.setBeanNumber(1);
        formatter = new PropertiesFormatter();
        configGenerator.setFormatter(formatter);
        configGenerator.analyze("syntaxe_exemples/");
        System.out.println(configGenerator.getFormatter().get());

        
        //System.out.println(propertiesFormatter.getProperties());
        configGenerator = new ConfigGenerator();
        configGenerator.setBeanNumber(1);
        HtmlFormatter htmlFormatter = new HtmlFormatter();
        // un exemple possible d'appel de service web, 
        // qui pourrait affecter les valeurs des attributs et engendre le fichier de configuration
        htmlFormatter.setAction("http://localhost:8086/femto_services/config.html"); 
        configGenerator.setFormatter(htmlFormatter);
        
        PrintWriter pw = new PrintWriter(new FileOutputStream("syntaxe_exemples/configHtml.html", false));
        pw.println("<HTML>");
        pw.println(configGenerator.analyze("syntaxe_exemples/").getFormatter().get());
        pw.println("</HTML>");
        pw.close();
        
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            File file = new File("syntaxe_exemples/configHtml.html");
            Desktop.getDesktop().browse(new URI("file:///"+file.getCanonicalPath().replace('\\','/')));
        }else{
            System.out.println("à l'aide d'un navigateur, ouvrez le fichier syntaxe_exemples/configHtml.html");
        }

        htmlFormatter = new HtmlFormatter();
        htmlFormatter.setAction("http://localhost:8086/femto_services/config.html"); 
        configGenerator.setFormatter(htmlFormatter);
        configGenerator.setBeanNumber(1);
        pw = new PrintWriter(new FileOutputStream("martin_fowler/configHtml.html", false));
        pw.println("<HTML>");
        pw.println(configGenerator.analyze("martin_fowler/").getFormatter().get());
        pw.println("</HTML>");
        pw.close();
        
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            File file = new File("martin_fowler/configHtml.html");
            Desktop.getDesktop().browse(new URI("file:///"+file.getCanonicalPath().replace('\\','/')));
        }else{
            System.out.println("à l'aide d'un navigateur, ouvrez le fichier martin_fowler/configHtml.html");
        }
    }
}
