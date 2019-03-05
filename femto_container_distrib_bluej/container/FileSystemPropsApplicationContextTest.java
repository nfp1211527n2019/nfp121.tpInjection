package container;

import java.io.*;
import java.util.*;
import java.net.URL;
import javax.swing.JFrame;

public class FileSystemPropsApplicationContextTest extends junit.framework.TestCase{

  public void testLectureFichierProperties() throws Exception{
    InputStream inputStream = new FileInputStream(new File("./container/README.TXT"));
    FileSystemPropsApplicationContext ctx = new FileSystemPropsApplicationContext(inputStream);

    List<String> liste = new ArrayList<String>();
    for(String bean : ctx){
      liste.add(bean);
    }
    assertTrue(liste.contains("list"));
    assertTrue(liste.contains("set"));

    assertEquals(ArrayList.class, ctx.getType("list"));
    assertEquals(TreeSet.class, ctx.getType("set"));

    assertTrue(liste.contains("martin_fowler"));
    assertEquals(FileSystemPropsApplicationContext.class, ctx.getType("martin_fowler"));

    FileSystemPropsApplicationContext ctxExemples = ctx.getBean("martin_fowler");

    liste.clear();
    for(String bean : ctxExemples){
      liste.add(bean);
    }

    assertTrue(liste.contains("movieLister"));
    martin_fowler.MovieLister lister = ctxExemples.getBean("movieLister");
    List<martin_fowler.Movie> movies = lister.moviesDirectedBy("Sergio Leone");
    assertEquals("Once Upon a Time in the West",movies.get(0).getTitle());

  }

  public void testLectureURLFichierProperties() throws Exception{
    // Avec au pr�alable l'exécution du serveur web depuis le r�pertoire
    // femto_container_distrib_bluej\serveurWeb8086>java -cp . ServeurWeb8086
    // serveur HTTP en 192.168.0.30:8086 est en attente d'une requete...
    // Request: GET /config.props HTTP/1.1
    try{
      InputStream inputStream = new URL("http://localhost:8086/config.props").openStream();
      FileSystemPropsApplicationContext ctx = new FileSystemPropsApplicationContext(inputStream);

      List<String> liste = new ArrayList<String>();
      for(String bean : ctx){
        liste.add(bean);
      }
      assertTrue(liste.size()>0);
      System.out.println("liste de beans issue de http://localhost:8086/config.props");
      System.out.println("\t" +liste);
    }catch(Exception e){
        
      System.out.println("Pour ce test, il faut avoir au préalable,");
      System.out.println("\tlancer l'exécution du serveur web depuis ce répertoire:");
      System.out.println("\tfemto_container_distrib_bluej/serveurWeb8086>java -cp . ServeurWeb8086");
    }
  }

}
