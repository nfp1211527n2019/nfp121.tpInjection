package chainofresponsability;

import container.*;

public class Main{
   
    public static void main(String[] args){
        // Handler chain = new HandlerNeutre();
        // Handler a = new HandlerA();
        // Handler b = new HandlerB();
        // a.setSuccessor(b);
        // chain.setSuccessor(a);
        
        ApplicationContext ctx = Factory.createApplicationContext("./chainofresponsability/README.TXT");
        Handler chain = ctx.getBean("chain");

        System.out.println(" pour 2 : " + chain.handleRequest(2));
        System.out.println(" pour 3 : " + chain.handleRequest(3));
        System.out.println(" pour 4 : " + chain.handleRequest(4));
        
    }
}
