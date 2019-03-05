package config_editor;


/**
 * A terminer
 */


public class JsonFormatter implements Formatter{
    private StringBuffer sb;

    public JsonFormatter(){
        this.sb = new StringBuffer("{");

    }

    public void newLine(){
        sb.append("\r\n");
    }

    public void newComment(String comment){
    }

    public void newBeanId(int beanNumber, String completeBeanName){
    }

    public void newBeanClass(String completeBeanName, String beanClassName){
    }

    public void newBeanPropertyKey(int number, String completeBeanName, String propertyName){
    }

    public void newBeanPropertyValue(int number, String completeBeanName, String propertyName, String propertyValue){
     }

     
    public String get(){
       return sb.append("}").toString();
    }
}

