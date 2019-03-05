package strategy;


public class Invocateur{
   
    private Context context;
    
   public Invocateur(){}

  
   public void setContext(Context context){
       this.context = context;
    }
    
   public int executeStrategy(int num1, int num2){
      return context.executeStrategy(num1, num2);
   }
}
