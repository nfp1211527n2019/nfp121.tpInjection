package config_editor;


public class HtmlFormatter implements Formatter{
    private StringBuffer sb;
    private String action;
    public HtmlFormatter(){
        this.sb = new StringBuffer("");
        this.action = new String();
    }

    public void newLine(){
        sb.append("\r\n");
    }

    public void newComment(String comment){
        sb.append("<!-- " + comment + " -->");
        //sb.append("<i>" + comment + "</i>");
    }

    public void newBeanId(int beanNumber, String completeBeanName){
        sb.append("bean.id."+beanNumber+"=<b>"+completeBeanName+"</b>");
    }

    public void newBeanClass(String completeBeanName, String beanClassName){
        sb.append("<b>"+completeBeanName+"</b>.class="+beanClassName);
    }

    public void newBeanPropertyKey(int number, String completeBeanName, String propertyName){
        sb.append("<b>"+completeBeanName+"</b>.property."+number+"="+propertyName);
    }

    public void newBeanPropertyValue(int number, String completeBeanName, String propertyName, String propertyValue){
      sb.append("<b>"+completeBeanName+"</b>.property."+number+".param.1=");
      sb.append("<input style='font-size: 20px;' type=\"text\" size=" + Math.max(4,propertyValue.length()) + "name=\"" + completeBeanName+".property."+number+".param.1\"");
      sb.append(" value=\"" + propertyValue + "\"" + ">");
    }

    public void setAction(String action){
        this.action = action;
    }
    
    public String get(){
        sb.insert(0,"<FORM method=\"POST\" action=\""+this.action+"\""+">\r\n<PRE><FONT size=5>");
        sb.append("<br><input type=\"submit\" value=\"Submit\">");
        return sb.append("</FONT></PRE></FORM>\r\n").toString();
    }
}
