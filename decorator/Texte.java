package decorator;

public class Texte implements TexteI{
  private String texte;
  public Texte(){}
  public void setTexte(String texte){
      this.texte = texte;
  }
  public Texte(String texte){this.texte = texte;}
  public String toHTML(){return this.texte;}
}
