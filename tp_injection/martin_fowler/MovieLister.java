package martin_fowler;
import java.util.*;

public class MovieLister{
    
  private MovieFinder f;   

  
  public MovieLister() {
  }
  
  public void setFinder(MovieFinder finder) {
    this.f = finder;
  }
  
  public List<Movie> moviesDirectedBy(String arg) {
      
    List<Movie> allMovies = f.findAll();
    for (Iterator<Movie> it = allMovies.iterator(); it.hasNext();) {
        Movie movie = it.next();
        if (!movie.getDirector().equals(arg)) it.remove();
    }
    return allMovies;
  }
}
