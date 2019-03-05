package femto_container_distrib_bluej.decorator;

public class B extends TexteDecore{
  
  public B(){}
  public B(TexteI unTexte){
    super(unTexte);
  }
  
  public String toHTML(){
    return "<B>" + super.toHTML() + "</B>";
  }
}

