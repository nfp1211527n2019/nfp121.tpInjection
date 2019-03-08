package command;

import java.util.*;
import container.*;

public class CommandTests extends junit.framework.TestCase{

    public void testAvecInjection() throws Exception{
        ApplicationContext ctx = Factory.createApplicationContext("./command/README.TXT");
        Invoker invoker = ctx.getBean("invoker");

        invoker.on();
        invoker.off();     
        invoker.on();
        invoker.off(); 
        invoker.on();
        invoker.off();
        
        // Projecteur projecteur = ctx.getBean("projecteurDeLAmphi");
        // Command cmd = ctx.getBean("cmdProjecteur");
        // cmd.execute();
        // assertEquals("projecteur allumé",projecteur.getStatus());
    }
    
    public void testSansInjection() throws Exception{
        Invoker invoker = new Invoker();
        Light lampe = new Light();
        CommandLight cmd = new CommandLight();
        cmd.setLampe(lampe);
        invoker.setCommande(cmd);
        invoker.on();
        invoker.off();     
        invoker.on();
        invoker.off(); 
        invoker.on();
        invoker.off();
        //assertEquals("lampe éteinte",lampe.getStatus());
        System.out.println("lampe.getStatus: " + lampe.getStatus());
    }
       public void testSansInjectionBis() throws Exception{
        Invoker invoker = new Invoker();
        Projecteur projecteur = new Projecteur();
        CommandeProjecteur cmd = new CommandeProjecteur();
        cmd.setProjecteur(projecteur);
        invoker.setCommande(cmd);
        invoker.on();
        invoker.off();     
        invoker.on();
        invoker.off(); 
        invoker.on();
        invoker.off();
        assertEquals("projecteur éteint",projecteur.getStatus());

    }
}

