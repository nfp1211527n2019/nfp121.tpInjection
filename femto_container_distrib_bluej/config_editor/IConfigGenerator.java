package config_editor;
import java.io.FilenameFilter;

/** Interface pour outils d'aide à la génération des fichiers de configuration,
 * fichiers principalement destinés à femtoContainer.<br>
 * Chaque mutateur(<i>setter</i>) est identifié et engendre les items attendus dans le fichier de configuration,
 * un mutateur doit respecter les conventions habituelles : void setXXXX(type elt)<br>
 * - Commencer par set, avoir un seul paramètre et ne pas retourner de résultat (void)<br>
 * Le format en sortie des items engendrés dépendent du <i>formatter</i> choisi.
 */
public interface IConfigGenerator{

    /** Analyse d'un fichier .class ou d'un répertoire. Afin d'engendrer un fichier de configuration à "trous", 
     * dans lequel il ne reste plus qu'à affecter les valeurs des attributs/propriétés.
     * <b>Attention</b> au préalable setBeanClassFileName a été appelée<br>
     */
    public IConfigGenerator analyze() throws Exception;
    
    /** Analyse d'un fichier .class ou d'un répertoire. Afin d'engendrer un fichier de configuration à "trous", 
     * dans lequel il ne reste plus qu'à affecter les valeurs des attributs/propriétés.
     * Par défaut ce sont les fichiers .class d'un répertoire qui sont analysés.
     * @param pathOrFileName un nom de fichier ou un nom de répertoire
     */
    public IConfigGenerator analyze(String pathOrFileName) throws Exception;

    /** Analyse d'un fichier .class ou d'un répertoire. 
     * @param pathOrFileName un nom de fichier ou un nom de répertoire
     * @param filter permet de filtrer les fichiers du répertoire
     */
    public IConfigGenerator analyze(String pathOrFileName, FilenameFilter filter) throws Exception;
  
    public void setBeanNumber(int beanNumber);
    public void setBeanName(String beanName);
    public void setBeanPrefixName(String beanPrefixName);
    
    /** Affectation du nom de la classe, ou du nom d'un répertoire.
     */
    public void setBeanClassFileName(String beanClassName);
    
    /** Choix du format de sortie. cf. le patron Stratégie.
     * @param formatter le format 
     */
    public void setFormatter(Formatter formatter);

    /** Avec des commentaires dans le texte au format choisi.
     * @param true des commentaires seront présents, pour chaque bean, pour chaque mutateur.
     */
    public void setComment(boolean comment);
    
    // accesseurs
    public int getBeanNumber();
    public String getBeanPrefixName();
    public String getBeanName();
    public String getBeanClassFileName();
    public Formatter getFormatter();
}