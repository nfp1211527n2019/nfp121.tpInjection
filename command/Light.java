package command;

public class Light{
    private String status;
    public void turnOn(){
      this.status = "lampe allumée";
      System.out.println(status);
    }
    
   public void turnOff(){
       this.status = "lampe éteinte";
       System.out.println(status);
    }
    
    public String getStatus(){
        return status;
    }
}
