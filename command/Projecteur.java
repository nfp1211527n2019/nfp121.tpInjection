package command;



public class Projecteur{
    private String status = "projecteur éteint";
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public void turnOn(){
      this.status = "projecteur allumé";
      System.out.println(status);
    }
    
   public void turnOff(){
       this.status = "projecteur éteint";
       System.out.println(status);
    }
    
    public String getStatus(){
        return status;
    }
}
