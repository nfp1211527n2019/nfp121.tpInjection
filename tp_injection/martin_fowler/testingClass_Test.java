package martin_fowler;

import container.*;
import java.util.*;









public class testingClass_Test extends junit.framework.TestCase{

   
   
    public void withoutInjection(){

        MovieFinder finder = new ColonMovieFinder();

        finder.setFilename("movies1.txt");

        MovieLister lister = new MovieLister();

        lister.setFinder(finder);

        List<Movie> movies = lister.moviesDirectedBy("Sergio Leone");

        assertEquals("Once Upon a Time in the West",movies.get(0).getTitle());

    }
   
   
   
    public void withInjection(){
  
        try{

            ApplicationContext ctx = Factory.createApplicationContext("./martin_fowler/README.TXT");


            MovieLister lister = ctx.getBean("movieLister");

            List<Movie> movies = lister.moviesDirectedBy("Sergio Leone");

            assertEquals("Once Upon a Time in the West",movies.get(0).getTitle());



        }catch(Exception e){}
            }

    
}