package syntaxe_exemples;



public class Factory{
    private Object instance;

    public  <T> T getInstance(){ return (T)instance;}
    
    public void setInstance(Class<?> cl) throws Exception{ 
      this.instance = cl.newInstance();
    }
    
}
