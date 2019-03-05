package decorator;


public class U extends TexteDecore{
  
  public U(){}
  public U(TexteI unTexte){
    super(unTexte);
  }
  
  public String toHTML(){
    return "<U>" + super.toHTML() + "</U>";
  }
}
