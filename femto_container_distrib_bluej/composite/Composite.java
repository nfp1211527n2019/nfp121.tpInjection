package composite;

import java.util.*;
public class Composite extends Composant{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private List<Composant> liste;

    public Composite(){
        liste = new ArrayList();
    }
    public void setComposant(Composant c){
       liste.add(c);
       System.out.println("appel de add " + c);
    }
}
