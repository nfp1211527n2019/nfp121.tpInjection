package martin_fowler;

import java.util.*;
import container.*;


public class MainClass{
  public static void main(String[] args){

    ApplicationContext ctx = Factory.createApplicationContext("martin_fowler/README.TXT");

    MovieLister lister = (MovieLister) ctx.getBean("movieLister");

    List<Movie> movies = lister.moviesDirectedBy("Sergio Leone");

    System.out.println(movies.get(0).getTitle());
    
  }
}