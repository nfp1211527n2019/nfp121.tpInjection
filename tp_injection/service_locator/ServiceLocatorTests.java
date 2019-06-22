package service_locator;

import container.*;


public class ServiceLocatorTests extends junit.framework.TestCase{

    public void testServiceLocator() throws Exception{
        ApplicationContext ctx = Factory.createApplicationContext("./service_locator/README.TXT");
        System.out.println(ctx);
        ServiceLocatorI serviceLocator = ctx.getBean("serviceLocator");

        System.out.println("Liste des services accessibles: ");
        for(String service : ctx){
            System.out.println("\t" + service);
        }
        System.out.println("------------------------------------------------");
        for(String service : serviceLocator){
            System.out.println("\t" + service);
        }
        
        question1.exemples.Table table = serviceLocator.lookup("table");
        table.ajouter(33);table.ajouter(33);
        assertEquals(2,table.taille());
        
        question2.patron_strategie.Context strategy = serviceLocator.lookup("contextPow");
        assertTrue("Error in strategy",strategy.executeStrategy(2, 2)==4);
        
        strategy = serviceLocator.lookup("contextAdd");
        assertTrue("Error in strategy",strategy.executeStrategy(50, 50)==100);
       
    }
}
