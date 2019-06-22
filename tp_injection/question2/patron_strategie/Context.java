package question2.patron_strategie;

public class Context {
    
   private Strategy str;
   

   public Context(Strategy strategy){
      this.str = strategy;
   }
   public Context(){}
   public void setStrategy(Strategy strategy){
      this.str = strategy;
   }

   public int executeStrategy(int num1, int num2){
      return str.doOperation(num1, num2);
   }
}