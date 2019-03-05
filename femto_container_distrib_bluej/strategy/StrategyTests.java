package strategy;

import java.util.*;
import container.*;

public class StrategyTests extends junit.framework.TestCase{

    
    
   public void testAvecInjectionSansModicficationDuSource() throws Exception{

     ApplicationContext ctx = Factory.createApplicationContext("./strategy/README.TXT");
     Invocateur invocateur = ctx.getBean("invocateur");
     System.out.println("resultat: " + invocateur.executeStrategy(10, 5));
    
 
    
    }

    public void testAvecInjection() throws Exception{

     ApplicationContext ctx = Factory.createApplicationContext("./strategy/README.TXT");
     Context context = ctx.getBean("context1");
     assertEquals("10 + 5 != 15 ??? ",15,context.executeStrategy(10, 5));

     context = ctx.getBean("context2");
     assertEquals("10 - 5 != 5 ??? ",5,context.executeStrategy(10, 5));
 	
     context = ctx.getBean("context3");
     assertEquals("10 * 5 != 50 ??? ", 50, context.executeStrategy(10, 5));
 
     context = ctx.getBean("context4");
     assertEquals("10 / 5 != 2 ??? ", 2, context.executeStrategy(10, 5));
 
    
    }

   
   public void testSansInjection() throws Exception{

     Context context = new Context();
     context.setStrategy(new OperationAdd());
     assertEquals("10 + 5 != 15 ??? ",15,context.executeStrategy(10, 5));
//System.out.println("10 + 5 = ??? " + context.executeStrategy(10, 5));

     context.setStrategy(new OperationSubstract());
     assertEquals("10 - 5 != 5 ??? ",5,context.executeStrategy(10, 5));
 	
     context.setStrategy(new OperationMultiply());
     assertEquals("10 * 5 != 50 ??? ", 50, context.executeStrategy(10, 5));
   }

 
}

