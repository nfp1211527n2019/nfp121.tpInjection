package syntaxe_exemples;

public class A{
    private I i;
    private float f;
    private I[] ti;
    private int[] tab;
   
    public void setF(float f){ 
        this.f = f;
    }
    public float getF(){return f;}
    
    public void setTab(int[] tab){ 
        this.tab = tab;
    }
    public int[] getTab(){return tab;}

    public A(){
    }

    public void setI(I i){
        // System.out.print("appel du mutateur avec ");
        // System.out.println(i.getClass().getName());
        this.i = i;
    }
    
    public void setTi(I[] ti){ 
        this.ti = ti;
    }
    public I getI(){ return this.i;}
    public I[] getTi(){ return this.ti;}
}
