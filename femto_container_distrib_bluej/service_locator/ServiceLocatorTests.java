package service_locator;

import container.*;


public class ServiceLocatorTests extends junit.framework.TestCase{

    public void testServiceLocator() throws Exception{
        ApplicationContext ctx = Factory.createApplicationContext("./service_locator/README.TXT");
 
        ServiceLocatorI serviceLocator = ctx.getBean("serviceLocator");

        System.out.println("Liste des services accessibles: ");
        for(String service : ctx){
            System.out.println("\t" + service);
        }
        System.out.println("------------------------------------------------");
        for(String service : serviceLocator){
            System.out.println("\t" + service);
        }
        
        syntaxe_exemples.Table table = serviceLocator.lookup("uneTable");
        table.setInt(33);table.setInt(33);
        assertEquals(3,table.taille());
    }
}
