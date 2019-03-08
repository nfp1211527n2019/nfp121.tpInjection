package command;


public class CommandeProjecteur implements Command{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private Projecteur projecteur;

    public void setProjecteur(Projecteur projecteur){
        this.projecteur = projecteur;
    }


   public void execute(){
       projecteur.turnOn();
    }
   public void undo(){
       projecteur.turnOff();
    }
       
}