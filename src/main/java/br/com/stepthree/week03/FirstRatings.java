package br.com.stepthree.week03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.csv.CSVParser;

import br.com.stepone.week01.Movie;
import edu.duke.FileResource;

/**
 * Created by david on 5/22/16.
 */
public class FirstRatings {

    public FirstRatings() {}

    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
        CSVParser csvParser = new FileResource(filename).getCSVParser();
        csvParser.forEach(csvRecord -> {
            movieArrayList.add(new Movie(csvRecord.get("id"),csvRecord.get("title"),csvRecord.get("year"),
                    					 csvRecord.get("genre"),csvRecord.get("director"),csvRecord.get("country"),
                    					 csvRecord.get("poster"),Integer.parseInt(csvRecord.get("minutes"))));
        });
      return movieArrayList;
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> raterList = new ArrayList<>();
        FileResource fileResource = new FileResource(filename);
        fileResource.getCSVParser().forEach(record -> {
        	String rater_id = record.get("rater_id");
            String movie_id = record.get("movie_id");
            Double rating = Double.valueOf(record.get("rating"));
            Optional<Rater> raterIdExist = raterList.stream().filter( filterRater -> filterRater.getID().equals(rater_id)).findFirst();            
            if (!raterIdExist.isPresent()) raterList.add(addRater(rater_id, movie_id, rating));
            else  raterList.stream().filter(filterRater -> filterRater.getID().equals(rater_id)).forEach( rater -> rater.addRating(movie_id, rating));
        });
        return raterList;
    }

	private Rater addRater(String rater_id, String movie_id, Double rating) {
		Rater currentRater = new PlainRater(rater_id);
		currentRater.addRating(movie_id, rating);
		return currentRater;
	}

    public void testLoadMovies() {
    	System.out.println("--------------------------------------");
		ArrayList<Movie> loadMoviesTotal = loadMovies("data/ratedmovies_short.csv");
		System.out.println("The number of movies is " + loadMoviesTotal.size());    	
		System.out.println("### How many movies include the Comedy genre. In the file ratedmovies_short.csv, there is only one. ###");
		String genre = "Comedy";
		System.out.println("The number of " + genre + " movies is " + loadMoviesTotal.stream()
																					 .filter(movie -> (movie.getGenres().contains(genre)))
																					 .count());

		System.out.println("### How many movies are greater than 150 minutes in length. ###");
		Integer movieMinutes = 150; 
        System.out.println("The number of movies longer than " + movieMinutes + " minutes is " + loadMoviesTotal.stream()
        																										.filter(movie -> (movie.getMinutes() > movieMinutes))
        																										.count());

        System.out.println("### the maximum number of movies by any director, and who the directors are that directed that many movies. ###");
        HashMap<String, Integer> directorNumMap = new HashMap<>();
	    loadMoviesTotal.forEach( movies -> {
	        for (String director : movies.getDirector().split(", ")) {
	        	if (!directorNumMap.containsKey(director)) directorNumMap.put(director, 1);
                else directorNumMap.put(director, directorNumMap.get(director) + 1);
	        }
	    });
	    Integer maxNumMovieByDirector = Collections.max(directorNumMap.entrySet(), Map.Entry.comparingByValue()).getValue();
	    System.out.println("The maximun number is " + maxNumMovieByDirector);

        String directorHavingLargestMovie = Collections.max(directorNumMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("The directors having the largest movie number are: " + directorHavingLargestMovie);
        System.out.println("--------------------------------------");
    }

    public void testLoadRaters() {
    	System.out.println("--------------------------------------");
    	ArrayList<Rater> raterList = loadRaters("data/ratings_short.csv");
        System.out.println("The number of raters is " + raterList.size());
        
        System.out.println("### find the number of ratings for a particular rater you specify in your code. ###");
        String id = "2";
        Stream<Rater> filter = raterList.stream().filter(r -> r.getID().equals(id));
        filter.forEach(rate -> {
        	 System.out.println("The number of ratings for rater id-"
			 + id + " is " + rate.getItemsRated().size());
        });

        System.out.println("### find the maximum number of ratings by any rater. ###");
        OptionalInt maxRatings = raterList.stream().mapToInt(rater -> rater.numRatings()).max();
        System.out.println("The largest umber of ratings by any raters is " + maxRatings.getAsInt());

        Optional<Rater> findFirst = raterList.stream().filter(r -> r.numRatings() == maxRatings.getAsInt()).findFirst();
        System.out.println("Raters who have the max number of ratings are " + findFirst.get().getID());
        
        System.out.println("### find the number of ratings a particular movie has.. ###");

        String movie_id = "1798709";
		List<Rater> listRate = raterList.stream().filter(r -> r.hasRating(movie_id)).collect(Collectors.toList());
        System.out.println("The number of ratings of movie id: " + movie_id + " is " + listRate.size());
        
        System.out.println("### how many different movies have been rated by all these raters.. ###");
        HashSet<String> movieSet = new HashSet<>();
        raterList.forEach( r -> {
        	ArrayList<String> curMovList = r.getItemsRated();
            curMovList.stream().filter(s -> !movieSet.contains(s)).forEach(movieSet::add);
        });
        System.out.println("The number of different movies rated is " + movieSet.size());
        System.out.println("--------------------------------------");
    }
}