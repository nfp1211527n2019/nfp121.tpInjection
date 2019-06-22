package question2.patron_strategie;

public class OperationPower implements Strategy{
   @Override
   public int doOperation(int num1, int num2) {
      return (int)Math.pow(num1,num2);
   }
}