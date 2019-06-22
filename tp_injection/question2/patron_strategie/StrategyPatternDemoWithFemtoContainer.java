package question2.patron_strategie;

import container.Factory;
import container.ApplicationContext;

public class StrategyPatternDemoWithFemtoContainer {
   public static void main(String[] args) throws Exception{
      ApplicationContext ctx = Factory.createApplicationContext("./question2/patron_strategie/README.TXT");
      Context context = ctx.getBean("contextAdd");
      System.out.println("22 + 14 = " + context.executeStrategy(22, 14));

      context = ctx.getBean("contextSub");
      System.out.println("22 - 14 = " + context.executeStrategy(22, 14));
 	
      context =  ctx.getBean("contextMul");
      System.out.println("22 * 14 = " + context.executeStrategy(22, 14));

      context =  ctx.getBean("contextPow");
      System.out.println("22 ^ 14 = " + context.executeStrategy(22, 14));
   }
}
