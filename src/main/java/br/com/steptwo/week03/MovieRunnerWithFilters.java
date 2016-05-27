package br.com.steptwo.week03;

import java.util.ArrayList;
import java.util.Collections;

import br.com.stepone.week01.Rating;


public class MovieRunnerWithFilters {

	public static void main(String[] args) {
		printAverageRatings();
		printAverageRatingsByYear();
		printAverageRatingsByGenre();
		printAverageRatingsByMinutes();
		printAverageRatingsByDirectors();
		printAverageRatingsByYearAfterAndGenre();
		printAverageRatingsByDirectorsAndMinutes();
	}
	
    public static void printAverageRatings() {
    	ThirdRatings thirdRatings = basicCall();
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatings(1);
        System.out.println("found " + averageRatings.size() + " movies");
        Collections.sort(averageRatings);
        averageRatings.forEach(averageRating -> {
        	System.out.println(averageRating.getValue() + " " + MovieDatabase.getTitle(averageRating.getItem()));
        });
        System.out.println("\n");
    }
    
    public static void printAverageRatingsByYear() {
    	ThirdRatings thirdRatings = basicCall();
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(1, new YearAfterFilter(2000));
        System.out.println("found " + averageRatings.size() + " movies");
        Collections.sort(averageRatings);
        averageRatings.forEach(averageRating -> {
        	System.out.println(averageRating.getValue() + " " +  MovieDatabase.getYear(averageRating.getItem()) +  " " + MovieDatabase.getTitle(averageRating.getItem()));
        });
        System.out.println("\n");
    }
    
    public static void printAverageRatingsByGenre() {
    	ThirdRatings thirdRatings = basicCall();
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(1, new GenreFilter("Crime"));
        System.out.println("found " + averageRatings.size() + " movies");
        Collections.sort(averageRatings);
        averageRatings.forEach(averageRating -> {
        	System.out.println(averageRating.getValue() + " " + MovieDatabase.getTitle(averageRating.getItem()) + " \n      " + MovieDatabase.getGenres(averageRating.getItem()));
        });
        System.out.println("\n");
    }

    public static void printAverageRatingsByMinutes() {
    	ThirdRatings thirdRatings = basicCall();
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(1, new MinutesFilter(110, 170));
        System.out.println("found " + averageRatings.size() + " movies");
        Collections.sort(averageRatings);
        averageRatings.forEach(averageRating -> {
        	System.out.println(averageRating.getValue() + "  Times : " + MovieDatabase.getMinutes(averageRating.getItem())    + "  "    +  MovieDatabase.getYear(averageRating.getItem()) +  " " + MovieDatabase.getTitle(averageRating.getItem()));
        });
        System.out.println("\n");
    }	
    
    public static void printAverageRatingsByDirectors (){
    	ThirdRatings thirdRatings = basicCall();
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(1, new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze"));
        System.out.println("found " + averageRatings.size() + " movies");
        Collections.sort(averageRatings);
        averageRatings.forEach(averageRating -> {
        	System.out.println(averageRating.getValue() + " " + " " + MovieDatabase.getTitle(averageRating.getItem()) + " \n " + MovieDatabase.getDirector(averageRating.getItem()) );
        });
        System.out.println("\n");
    }
    
    public static void printAverageRatingsByYearAfterAndGenre(){
    	ThirdRatings thirdRatings = basicCall();

        AllFilters filters = new AllFilters();
        filters.addFilter(new YearAfterFilter(1980));
        filters.addFilter(new GenreFilter("Romance"));

        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(1, filters);
        System.out.println(averageRatings.size() + " movie matched");
        
        Collections.sort(averageRatings);
        averageRatings.forEach(averageRating -> {
            System.out.println(averageRating.getValue() + " " + MovieDatabase.getYear(averageRating.getItem()) + " " + MovieDatabase.getTitle(averageRating.getItem()) + " \n     " + MovieDatabase.getGenres(averageRating.getItem()));
        });
        System.out.println("\n");
    	
    }
    
    public static void printAverageRatingsByDirectorsAndMinutes() {
    	ThirdRatings thirdRatings = basicCall();
        AllFilters filters = new AllFilters();
        filters.addFilter(new MinutesFilter(30, 170));
        filters.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));

        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(1, filters);
        System.out.println(averageRatings.size() + " movie matched");
        Collections.sort(averageRatings);
        averageRatings.forEach(averageRating -> {
            System.out.println(averageRating.getValue() + "  Time:" + MovieDatabase.getMinutes(averageRating.getItem()) + " " + MovieDatabase.getTitle(averageRating.getItem()) + " \n     " + MovieDatabase.getDirector(averageRating.getItem()));
        });
        System.out.println("\n");
    }
    
	private static ThirdRatings basicCall() {
		ThirdRatings thirdRatings = new ThirdRatings("data/ratings_short.csv");
        System.out.println("The number of raters is " + thirdRatings.getRaterSize());
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
		return thirdRatings;
	}
}
