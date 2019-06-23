package martin_fowler;

import java.util.*;







public class SemiColonMovieFinder implements MovieFinder {
private String file;



  
  public SemiColonMovieFinder() {}

  
  
  public List<Movie> findAll(){

    System.out.println("SemiColonMovieFinder ...");
    
  List<Movie> list = new ArrayList<Movie>();
   
  list.add(new Movie("Dans les forêts de Sibérie","NEBBOU Safy"));

  list.add(new Movie("Demain","LAURENT Mélanie / DION Cyril"));

  list.add(new Movie("Once Upon a Time in the West","Sergio Leone"));



  return list;
}

  public void setFilename(String file) {
    this.file = file;
  }
  

  public String toString(){
    return "ColonMovieFinder";
  }
}