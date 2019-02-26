
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import java.util.Properties;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.Properties;
public class AllTests extends junit.framework.TestCase{

    static{
        Properties propsSystem = System.getProperties();
        //propsSystem.setProperty("verbose","true");
        propsSystem.setProperty("verbose","false");
    }

    
    public void testAll() throws Exception{
        String res = new String();
        
        res=execution(martin_fowler.MartinFowlerExampleTests.class);
        res+=execution(container.FileSystemPropsApplicationContextTest.class);
        res+=execution(syntaxe_exemples.ExemplesTests.class);
        res+=execution(decorator.DecoratorTests.class);
        res+=execution(command.CommandTests.class);
        res+=execution(strategy.StrategyTests.class);
        res+=execution(proxy.ProxyTests.class);
        
        res+=execution(service_locator.ServiceLocatorTests.class);
        
        
        // etc...
        System.out.println(res);
    }

    private static String execution(Class<?> classeDeTests){
        Result result = JUnitCore.runClasses(classeDeTests);
        String res = new String("");
        for (Failure failure : result.getFailures()) {
            System.err.println(failure.toString());
            res = res + failure.toString() + "\n";
        }
        //assertFalse(res, res.length()>0);
        return res;
    }
}