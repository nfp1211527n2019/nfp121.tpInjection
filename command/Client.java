package command;

import container.*;
public class Client{

     public static void test2() throws Exception{
        Light lampe = new Light();
        CommandLight cmd = new CommandLight();
        cmd.setLampe(lampe);
        
        Invoker invoker = new Invoker();
        invoker.setCommande(cmd);

        invoker.on();
        invoker.off();     
        invoker.on();
        invoker.off(); 
        invoker.on();
        invoker.off(); 
    }
    
    public static void test() throws Exception{

        ApplicationContext ctx = Factory.createApplicationContext("./command/README.TXT");
        Invoker invoker = (Invoker) ctx.getBean("invoker");

        invoker.on();
        invoker.off();     
        invoker.on();
        invoker.off(); 
        invoker.on();
        invoker.off(); 
    }

}
