package config_editor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Insets;

import java.util.TimerTask;

import java.util.concurrent.atomic.AtomicBoolean;

/** Interface d'aide à la génération du fichier de configuration.
 * @author jean-michel Douin
 * @version Mars 2019
 * @see config_editor.ConfigGenerator
 */
public class ConfigGeneratorGUI extends JFrame{

    private JTextField numberBeanField;
    private JTextField classBeanField;
    private JTextArea  configTextArea;
    private JCheckBox  commentButton;

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
        super("Interface d'aide à l'édition du fichier de configuration/femtoContainer, Mars 2019");

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
        label.addMouseListener(new DoubleClickActionClear());
        panel.add(label);panel.add(numberBeanField);

        // ajout du champ pour le nom de la classe/bean ou du répertoire/paquetage
        classBeanField = new JTextField(20);
        classBeanField.setEnabled(true);
        JLabel classBeanFieldLabel = new JLabel(" beanClassName or directory: ");
        classBeanFieldLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        classBeanFieldLabel.setForeground(Color.BLUE);
        panel.add(classBeanFieldLabel);
        classBeanField.setText("syntaxe_exemples/");
        panel.add(classBeanField);

        JButton generateButton = new JButton("generate");
        ActionListener generateConfigAction = new GenerateConfigAction();
        generateButton.addActionListener(generateConfigAction); // un click
        classBeanField.addActionListener(generateConfigAction); // la touche Entrée
        panel.add(generateButton);

        // JButton clearButton = new JButton("clear");
        // clearButton.addActionListener(new ClearAction());
        // panel.add(clearButton);

        this.commentButton = new JCheckBox("Comment");
        this.commentButton.setMnemonic(KeyEvent.VK_C); 
        this.commentButton.setSelected(true);
        panel.add(this.commentButton);

        this.configTextArea = new JTextArea(20,20);
        add(panel, BorderLayout.NORTH);
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panel,BorderLayout.WEST);

        configTextArea.setFont(new Font("Serif", Font.PLAIN, 14));
        configTextArea.setMargin( new Insets(10,10,10,10) ); 
        configTextArea.addMouseListener(new DoubleClickActionClear());

        JScrollPane scrollPane = new JScrollPane(configTextArea);
        add(scrollPane,BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configTextArea.setText(description);
        pack();show();
    }

    private static final String description =
        "  ConfigGeneratorGUI est une interface d'aide à la génération des fichiers de configuration.\n\n" +

        "  - En entrée, un nom de classe(bean) ou un répertoire, en sortie un texte de configuration.\n" +
        "  - Chaque mutateur(setter) de chaque bean est détecté et l'outil engendre les items attendus pour le\n"+
        "fichier de configuration, un mutateur est une méthode qui respecte les conventions habituelles.\n" +
        "Un numéro de bean par fichier .class est attribué, les valeurs des attributs doivent ensuite être\n" +
        "renseignées afin de produire un fichier de configuration avec les valeurs des attributs attendues.\n\n" +

        "  - Sont générées, les valeurs par défaut pour les types primitifs et leur \"wrapper\",\n" +
        "et un identifiant de ce qui est attendu comme valeur d'attribut: a_ClassName_bean, an_array, etc. .\n\n"+

        "  - Ci dessous en exemple, la classe analysée est syntaxe_exemples.ListeDeTables\n"+
        "il suffit ensuite de copier ce texte et de remplacer le littéral par ce qui est attendu cf. <--\n" +
        "et ainsi obtenir un fichier de configuration ad'hoc pour femtoContainer.\n\n" +
        "  - Ci-dessous, la configuration issue de la classe syntaxe_exemples.ListeDeTables : \n" +
        "bean.id.7=listeDeTables\n" +
        "listeDeTables.class=syntaxe_exemples.ListeDeTables\n" +
        "listeDeTables.property.1=operand\n" +
        "listeDeTables.property.1.param.1=0    <--\n" +
        "listeDeTables.property.2=table\n" +
        "listeDeTables.property.2.param.1=a_Table_bean  <--\n" +
        "listeDeTables.property.3=tables\n" +
        "listeDeTables.property.3.param.1=an_array_a_Table_bean <--\n\n" +
        "  - Cette configuration est déduite de la Classe: syntaxe_exemples.ListeDeTables, ci-dessous \n" +
        "public class ListeDeTables{\n" +
        "  private List<Table> liste;\n" +
        "  public ListeDeTables(){\n" +
        "    this.liste = new ArrayList<Table>();\n" +
        "  }\n" +
        "  public void setTable(Table t){\n" +
        "    liste.add(t);\n" +
        "  }\n" +
        "  public void setTables(Table[] tables){\n" +
        "    for(Table t : tables)\n" +
        "      setTable(t);\n" +
        "    }\n" +
        "  }\n" +
        "  // ... \n" +
        "}\n\n" +
        "Notes: - Un double click dans cette fenêtre efface son contenu,\n" +
        "si le mode Comment est validé, des commentaires sont ajoutés au texte généré.\n" +
        " - Cette interface est perfectible, envoyez moi remarques, idées, modifications du source Java, etc......";

    private class GenerateConfigAction implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            try{
                int beanNumber = 1;
                try{
                    beanNumber = Integer.parseInt(numberBeanField.getText());
                }catch(Exception e){};
                assert configGenerator!= null : "configGenerator==null ?, setConfigGenerator must be called...";
                assert configGenerator.getFormatter()!= null : "configGenerator.getFormatter==null ?, see configGenerator initialization...";
                configGenerator.setBeanNumber(beanNumber);
                configGenerator.setBeanName(configGenerator.getBeanName());
                configGenerator.setBeanClassFileName(classBeanField.getText());
                configGenerator.setComment(commentButton.isSelected());
                configGenerator.analyze();
                configTextArea.setText(configGenerator.getFormatter().get());
            }catch(Exception e){
                configTextArea.setText("Exception ! "+ e.getClass().getName() + " --> " + e.getMessage() +"\n");
                if(configGenerator==null) configTextArea.setText(configTextArea.getText()+"configGenerator==null ?");
                if(configGenerator.getFormatter()==null) configTextArea.setText(configTextArea.getText()+"configGenerator==null ?");
            }
        }
    }

    // Classe interne permettant d'effacer le contenu de la zone de texte,
    // les paramètres courant sont conservés pour la nouvelle instance du configurateur
    private class ClearAction implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            try{
                // les paramètres actuels sont repris pour la nouvelle configuration
                int number       = configGenerator.getBeanNumber();
                String prefix    = configGenerator.getBeanPrefixName();
                String name      = configGenerator.getBeanName();
                String className = configGenerator.getBeanClassFileName();
                ConfigGeneratorGUI.this.configGenerator = new ConfigGenerator(number,prefix,name,className);
                // actualisation de l'IHM 
                ConfigGeneratorGUI.this.numberBeanField.setText(Integer.toString(number));
                ConfigGeneratorGUI.this.classBeanField.setText(className);
                ConfigGeneratorGUI.this.configTextArea.setText("");
            }catch(Exception e){
                configTextArea.setText(""); // en mode dégradé
            }
        }
    }

    private class DoubleClickActionClear extends MouseAdapter implements MouseListener{
        // cf. https://stackoverflow.com/questions/4051659/identifying-double-click-in-java
        private AtomicBoolean isAlreadyOneClick; // atomic : accès concurrent
        private ActionListener actionListener;

        public DoubleClickActionClear(){
            this.isAlreadyOneClick = new AtomicBoolean(false);
            this.actionListener = new ClearAction(); // idem au patron proxy ...
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if (isAlreadyOneClick.get()) {
                actionListener.actionPerformed(null);
                isAlreadyOneClick.set(false);
            } else {
                isAlreadyOneClick.set(true);
                java.util.Timer t = new java.util.Timer("doubleclickTimer", false);
                t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            isAlreadyOneClick.set(false);
                        }
                    }, 500);
            }
        }
    }

    public static void main(String[] args){
        ConfigGeneratorGUI gui = new ConfigGeneratorGUI();
        gui.setClassBean("config_editor.ConfigGeneratorGUI");
        gui.setNumberBean(1);
        IConfigGenerator configGenerator = new ConfigGenerator();
        configGenerator.setFormatter(new PropertiesFormatter());
        configGenerator.setBeanName("editor");
        configGenerator.setBeanPrefixName("pre_");
        gui.setConfigConfigurator(configGenerator);
    }
}
