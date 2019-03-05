package decorator;


public class PRE extends TexteDecore{
  
  public PRE(){}
  public PRE(TexteI unTexte){
    super(unTexte);
  }
  
  public String toHTML(){
    return "<PRE>" + super.toHTML() + "</PRE>";
  }
}
