package femto_container_distrib_bluej.decorator;

public class I extends TexteDecore{
  
  public I(){}
  public I(TexteI unTexte){
    super(unTexte);
  }
  
  public String toHTML(){
    return "<I>" + super.toHTML() + "</I>";
  }
}