package question1.exemples;

public class Example{ 
    private int op1;
    private int op2;
    private String oper;
    
    public void setOperande1(int op){this.op1 = op;}
    public void setOperande2(int op){this.op2 = op;}
    public void setOperateur(String op){this.oper = op;}
    
    public int calcul(){
        int resultat = 0;
        switch(this.oper){
            case "+": resultat = this.op1 + this.op2;break;
            case "-": resultat = this.op1 - this.op2;break;
            case "*": resultat = this.op1 * this.op2;break;
            case "/": resultat = this.op1 / this.op2;break;
            default:break;
        }
        return resultat;
    }
    
    public String toString(){
        return this.op1 + this.oper + this.op2 + " = " + calcul();
    }
}
