package config_editor;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class ConfigGeneratorGUI extends JFrame{

    private JTextField numberBeanField;
    private JTextField classBeanField;
    private JTextArea  configTextArea;    

    private IConfigGenerator  configGenerator;
    private Formatter         formatter;

    public void setConfigConfigurator(IConfigGenerator  configGenerator){
        this.configGenerator = configGenerator;
    }

    public void setNumberBean(int number){
        this.numberBeanField.setText(Integer.toString(number));
    }

    public void setClassBean(String classBean){
        this.classBeanField.setText(classBean);
    }

    public ConfigGeneratorGUI(){
        super("Interface d'aide à l'édition du fichier de configuration/femtoContainer");

        setLocation(10,10);
        setLayout(new BorderLayout(20,20));
        JPanel panel = new JPanel();
        panel.setFont(new Font("Serif", Font.PLAIN, 16));
        panel.setLayout(new FlowLayout());
        numberBeanField = new JTextField("1",2);
        numberBeanField.setEnabled(true);
        JLabel label = new JLabel(" bean.id.");
        label.setFont(new Font("Serif", Font.PLAIN, 16));
        label.setForeground(Color.RED);
        panel.add(label);panel.add(numberBeanField);

        classBeanField = new JTextField(20);
        classBeanField.setEnabled(true);
        JLabel classBeanFieldLabel = new JLabel(" beanClassName or directory: ");
        classBeanFieldLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        classBeanFieldLabel.setForeground(Color.BLUE);
        panel.add(classBeanFieldLabel);
        classBeanField.setText("syntaxe_exemples/");
        panel.add(classBeanField);

        JButton generateButton = new JButton("generate");
        generateButton.addActionListener(new GenerateConfig());
        panel.add(generateButton);

        JButton clearButton = new JButton("clear");
        clearButton.addActionListener(new ClearAction());
        panel.add(clearButton);

        this.configTextArea = new JTextArea(20,20);
        add(panel, BorderLayout.NORTH);
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panel,BorderLayout.WEST);

        configTextArea.setFont(new Font("Serif", Font.PLAIN, 14));

        //

        JScrollPane scrollPane = new JScrollPane(configTextArea);
        add(scrollPane,BorderLayout.CENTER);
        pack();

        show();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        configTextArea.setText(description);
        pack();
    }

    private static final String description =
        "ConfigGeneratorGui est une interface d'aide à la génération des fichiers de configuration.\n\n" +
        "Chaque mutateur(setter) est détecté et l'outil engendre les items attendus pour le\n"+
        "fichier de configuration, un mutateur est une méthode qui respecte les conventions habituelles.\n" +
        "Un numéro de bean par fichier .class est attribué, les valeurs des attributs doivent ensuite être\n" +
        "renseignées afin de produire un fichier de configuration avec les valeurs des attributs attendues.\n\n" +

        "Sont générées, les valeurs par défaut pour les types primitifs et leur \"wrapper\",\n" +
        "et une description textuelle, synthétique de ce qui est attendu. a_ClassName_bean or an_array ...\n\n"+

        "Ci dessous un exemple de ce qui est généré,\n"+
        "il suffit ensuite de copier ce texte et d'indiquer ce qui est attendu cf. <--\n" +
        "afin d'obtenir un fichier de configuration pour femtoContainer\n\n" +

        "bean.id.7=listeDeTables\n" +
        "listeDeTables.class=syntaxe_exemples.ListeDeTables\n" +
        "listeDeTables.property.1=operand\n" +
        "listeDeTables.property.1.param.1=0    <--\n" +
        "listeDeTables.property.2=table\n" +
        "listeDeTables.property.2.param.1=a_Table_bean  <--\n" +
        "listeDeTables.property.3=tables\n" +
        "listeDeTables.property.3.param.1=an_array_a_Table_bean <--\n";

    private class GenerateConfig implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            try{
                int beanNumber = 1;
                try{
                    beanNumber = Integer.parseInt(numberBeanField.getText());
                }catch(Exception e){};
                assert configGenerator!= null : "configGenerator==null ?, setConfigGenerator must be called...";
                assert configGenerator.getFormatter()!= null : "configGenerator.getFormatter==null ?, see configGenerator initialization...";
                configGenerator.setBeanNumber(beanNumber);
                configGenerator.setBeanName(classBeanField.getText());
                configGenerator.setBeanClassFileName(classBeanField.getText());
                configGenerator.analyze();
                configTextArea.setText(configGenerator.getFormatter().get());
            }catch(Exception e){
                configTextArea.setText("Exception ! "+ e.getMessage() +"\n");
                if(configGenerator==null) configTextArea.setText(configTextArea.getText()+"configGenerator==null ?");
            }
        }
    }

    private class ClearAction implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            configGenerator = new ConfigGenerator();
            numberBeanField.setText("1");
            configTextArea.setText("");
        }
    }

    public static void main(String[] args){
        ConfigGeneratorGUI gui = new ConfigGeneratorGUI();
        gui.setClassBean("config_editor.ConfigGeneratorGUI");
        gui.setNumberBean(1);
        IConfigGenerator configGenerator = new ConfigGenerator();
        configGenerator.setFormatter(new PropertiesFormatter());
        gui.setConfigConfigurator(configGenerator);
    }
}
