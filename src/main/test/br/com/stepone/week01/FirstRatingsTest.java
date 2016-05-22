package br.com.stepone.week01;

import static org.junit.Assert.assertEquals;

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

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class FirstRatingsTest {
	private FirstRatings firstRatings;

	@Before
	public void setUp() throws Exception {
		firstRatings = new FirstRatings();
	}

	@Test @Ignore
	public void testTotalLoadMovies() {
		ArrayList<Movie> loadMoviesTotal = firstRatings.loadMovies("data/ratedmoviesfull.csv");
		System.out.println("The number of movies is " + loadMoviesTotal.size());
		assertEquals(loadMoviesTotal.size(), 3143);
	}

	@Test @Ignore
	public void testTotalNumberGenreComedy() {
		ArrayList<Movie> loadMoviesTotal = firstRatings.loadMovies("data/ratedmoviesfull.csv");
		String genre = "Comedy"; 
		Long runCountGenre = loadMoviesTotal.stream()
									   		.filter(movie -> (movie.getGenres().contains(genre)))
									   		.count();
		System.out.println("The number of " + genre + " movies is " + runCountGenre);
		assertEquals(runCountGenre.longValue(), 960);
	}
	
	@Test @Ignore
	public void testTotalMoviesGreaterThan150Minutes(){
		ArrayList<Movie> loadMoviesTotal = firstRatings.loadMovies("data/ratedmoviesfull.csv");
		Integer movieMinutes = 150;
		Long countMovieMinutes = loadMoviesTotal.stream().filter(movie -> (movie.getMinutes() > movieMinutes)).count(); 
        System.out.println("The number of movies longer than " + movieMinutes + " minutes is " + countMovieMinutes);
        assertEquals(countMovieMinutes.longValue(), 132);
	}
	
	@Test
	public void testMaximumNumberOfMoviesByAnyDirector() {
		ArrayList<Movie> loadMoviesTotal = firstRatings.loadMovies("data/ratedmoviesfull.csv");
        HashMap<String, Integer> directorNumMap = new HashMap<>(); //key: director's name, value: movie numbers
	    loadMoviesTotal.forEach( movies -> {
	        for (String director : movies.getDirector().split(", ")) {
	        	if (!directorNumMap.containsKey(director)) directorNumMap.put(director, 1);
                else directorNumMap.put(director, directorNumMap.get(director) + 1);
	        }
	    });
	    Integer maxNumMovieByDirector = Collections.max(directorNumMap.entrySet(), Map.Entry.comparingByValue()).getValue();
        System.out.println("The maximun number is " + maxNumMovieByDirector);
        assertEquals(maxNumMovieByDirector.intValue(), 23);
        
        String directorHavingLargestMovie = Collections.max(directorNumMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("The directors having the largest movie number are: " + directorHavingLargestMovie);
        assertEquals(directorHavingLargestMovie, "Woody Allen");
	}
	
	@Test @Ignore
    public void testLoadRatersSize() {
        ArrayList<Rater> raterList = firstRatings.loadRaters("data/ratings.csv");
        System.out.println("The number of raters is " + raterList.size());
        assertEquals(raterList.size(), 1048);
	}
	
	@Test @Ignore
	public void testFindParticularRate() {
		ArrayList<Rater> raterList = firstRatings.loadRaters("data/ratings.csv");
        String id = "193"; //can be modified
        Stream<Rater> filter = raterList.stream().filter(r -> r.getID().equals(id));
        filter.forEach(rate -> {
        	 System.out.println("The number of ratings for rater id-"
			 + id + " is " + rate.getItemsRated().size());
        	 assertEquals(rate.getItemsRated().size(), 119);
        });
	}
	
	@Test @Ignore
	public void testMaximumNumberOfRatingsByAnyRater(){
		ArrayList<Rater> raterList = firstRatings.loadRaters("data/ratings.csv");
        OptionalInt maxRatings = raterList.stream().mapToInt(rater -> rater.numRatings()).max();
        System.out.println("The largest umber of ratings by any raters is " + maxRatings.getAsInt());
        assertEquals(maxRatings.getAsInt(), 314); //  assertEquals(maxRatings.getAsInt(), 314);

        Optional<Rater> findFirst = raterList.stream().filter(r -> r.numRatings() == maxRatings.getAsInt()).findFirst();
        System.out.println("Raters who have the max number of ratings are " + findFirst.get().getID());
        assertEquals(findFirst.get().getID(), "735");
	}
	
	@Test @Ignore
	public void testFindTheNumberOfRatingsParticularMovie() {
		ArrayList<Rater> raterList = firstRatings.loadRaters("data/ratings.csv");
        String movie_id = "1798709";
		List<Rater> listRate = raterList.stream().filter(r -> r.hasRating(movie_id)).collect(Collectors.toList());
        System.out.println("The number of ratings of movie id: " + movie_id + " is " + listRate.size());
        assertEquals(listRate.size(), 4);
	}
	
	@Test @Ignore
	public void testHowManyDifferentMoviesHaveBeenRatedByAllTheseRaters() {
		ArrayList<Rater> raterList = firstRatings.loadRaters("data/ratings.csv");
        HashSet<String> movieSet = new HashSet<>();
        raterList.forEach( r -> {
        	ArrayList<String> curMovList = r.getItemsRated();
            curMovList.stream().filter(s -> !movieSet.contains(s)).forEach(movieSet::add);
        });
        System.out.println("The number of different movies rated is " + movieSet.size());
        //3143
	}
}