package decorator;


import java.util.*;
import container.*;

public class DecoratorTests  extends junit.framework.TestCase{

    public void testAvecInjection() throws Exception{
        ApplicationContext ctx = Factory.createApplicationContext("./decorator/README.TXT");

        TexteI texteDecore = ctx.getBean("texteDecore");
        System.out.println("texteDecore: " + texteDecore.toHTML());
        //assertEquals("<U><B><I>Exemple</I></B></U>",texteDecore.toHTML());
        //System.out.println("texte décoré: " + texteDecore.toHTML());
        
    }
    
    public void testSansInjection() throws Exception{
      TexteI texte = new Texte("Exemple");
      TexteI texteDecore = new U(new B(new I( texte)));
      assertEquals("<U><B><I>Exemple</I></B></U>",texteDecore.toHTML());
    }
}

