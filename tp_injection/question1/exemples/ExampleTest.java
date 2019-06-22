package question1.exemples;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import container.*;

/**
 * The test class 
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ExampleTest
{
    /**
     * 
     */
    public ExampleTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    public void test_Example(){
        ApplicationContext ctx = Factory.createApplicationContext("./question1/exemples/README.TXT");
        Example test = ctx.getBean("calcul");
        assertEquals("52+72 = 124", test.toString());
    }
}
