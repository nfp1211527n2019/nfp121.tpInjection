package composite;
import container.*;

public class Main{

    public static void main(String[] args){

        
        ApplicationContext ctx = Factory.createApplicationContext("./composite/README.TXT");
        Composant composite = ctx.getBean("composite");

    }
        
    }