package question2.patron_strategie;

public class StrategyPatternDemo {
    
   public static void main(String[] args) {
       
      Context context = new Context(new OperationAdd());		
      System.out.println("50 + 15 = " + context.executeStrategy(50, 15));

      context = new Context(new OperationSubstract());		
      System.out.println("50 - 15 = " + context.executeStrategy(50, 15));

      context = new Context(new OperationMultiply());		
      System.out.println("50 * 15 = " + context.executeStrategy(50, 15));

      context = new Context(new OperationPower());	
      System.out.println("50 ^ 3 = " + context.executeStrategy(50, 3));
   }
   
}