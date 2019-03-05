package config_editor;

import java.util.Properties;
import java.io.StringReader;


public class PropertiesFormatter implements Formatter{
    private StringBuffer sb;
    private String name;

    public PropertiesFormatter(){
        this.sb = new StringBuffer("");
    }

    public void setName(String name){
        this.name = name;
    }

    public void newLine(){
        sb.append("\n");
    }

    public void newComment(String comment){
        sb.append("# " + comment);
    }

    public void newBeanId(int beanNumber, String beanName){
        sb.append("bean.id."+beanNumber+"="+beanName);
    }

    public void newBeanClass(String beanName, String beanClassName){
        sb.append(beanName+".class="+beanClassName);
    }

    public void newBeanPropertyKey(int number, String completeBeanName, String propertyName){
        sb.append(completeBeanName+".property."+number+"="+propertyName);
    }

    public void newBeanPropertyValue(int number, String completeBeanName, String propertyName, String propertyValue){
        sb.append(completeBeanName+".property."+number+".param.1=" + propertyValue);   
    }

    public String get(){
        return sb.toString();
    }

    public Properties getProperties() throws Exception{
        Properties props = new Properties();
        props.load(new StringReader(this.get()));
        return props;
    }
}
