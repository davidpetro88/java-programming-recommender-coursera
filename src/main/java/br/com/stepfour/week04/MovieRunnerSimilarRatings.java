package br.com.stepfour.week04;

import java.util.ArrayList;
import java.util.Collections;

import br.com.stepone.week01.Rating;
import br.com.stepthree.week03.AllFilters;
import br.com.stepthree.week03.DirectorsFilter;
import br.com.stepthree.week03.GenreFilter;
import br.com.stepthree.week03.MinutesFilter;
import br.com.stepthree.week03.MovieDatabase;
import br.com.stepthree.week03.YearAfterFilter;

public class MovieRunnerSimilarRatings {
	public static void main(String[] args) {
		printAverageRatings();
		printAverageRatingsByYear();
		printAverageRatingsByGenre();
		printAverageRatingsByMinutes();
		printAverageRatingsByDirectors();
		printAverageRatingsByYearAfterAndGenre();
		printAverageRatingsByDirectorsAndMinutes();
		printSimilarRatings();
		printSimilarRatingsByGenre();
		printSimilarRatingsByDirector();
		printSimilarRatingsByGenreAndMinutes();
		printSimilarRatingsByYearAfterAndMinutes();
	}
	
    public static void printAverageRatings() {
    	FourthRatings fourthRatings = basicCall();
        ArrayList<Rating> averageRatings = fourthRatings.getAverageRatings(1);
        System.out.println("found " + averageRatings.size() + " movies");
        Collections.sort(averageRatings);
        averageRatings.forEach(averageRating -> {
        	System.out.println(averageRating.getValue() + " " + MovieDatabase.getTitle(averageRating.getItem()));
        });
        System.out.println("\n");
    }
    
    public static void printAverageRatingsByYear() {
    	FourthRatings fourthRatings = basicCall();
        ArrayList<Rating> averageRatings = fourthRatings.getAverageRatingsByFilter(1, new YearAfterFilter(2000));
        System.out.println("found " + averageRatings.size() + " movies");
        Collections.sort(averageRatings);
        averageRatings.forEach(averageRating -> {
        	System.out.println(averageRating.getValue() + " " +  MovieDatabase.getYear(averageRating.getItem()) +  " " + MovieDatabase.getTitle(averageRating.getItem()));
        });
        System.out.println("\n");
    }
    
    public static void printAverageRatingsByGenre() {
    	FourthRatings fourthRatings = basicCall();
        ArrayList<Rating> averageRatings = fourthRatings.getAverageRatingsByFilter(1, new GenreFilter("Crime"));
        System.out.println("found " + averageRatings.size() + " movies");
        Collections.sort(averageRatings);
        averageRatings.forEach(averageRating -> {
        	System.out.println(averageRating.getValue() + " " + MovieDatabase.getTitle(averageRating.getItem()) + " \n      " + MovieDatabase.getGenres(averageRating.getItem()));
        });
        System.out.println("\n");
    }

    public static void printAverageRatingsByMinutes() {
    	FourthRatings fourthRatings = basicCall();
        ArrayList<Rating> averageRatings = fourthRatings.getAverageRatingsByFilter(1, new MinutesFilter(110, 170));
        System.out.println("found " + averageRatings.size() + " movies");
        Collections.sort(averageRatings);
        averageRatings.forEach(averageRating -> {
        	System.out.println(averageRating.getValue() + "  Times : " + MovieDatabase.getMinutes(averageRating.getItem())    + "  "    +  MovieDatabase.getYear(averageRating.getItem()) +  " " + MovieDatabase.getTitle(averageRating.getItem()));
        });
        System.out.println("\n");
    }	
    
    public static void printAverageRatingsByDirectors (){
    	FourthRatings fourthRatings = basicCall();
        ArrayList<Rating> averageRatings = fourthRatings.getAverageRatingsByFilter(1, new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze"));
        System.out.println("found " + averageRatings.size() + " movies");
        Collections.sort(averageRatings);
        averageRatings.forEach(averageRating -> {
        	System.out.println(averageRating.getValue() + " " + " " + MovieDatabase.getTitle(averageRating.getItem()) + " \n " + MovieDatabase.getDirector(averageRating.getItem()) );
        });
        System.out.println("\n");
    }
    
    public static void printAverageRatingsByYearAfterAndGenre(){
    	FourthRatings fourthRatings = basicCall();
        AllFilters filters = new AllFilters();
        filters.addFilter(new YearAfterFilter(1980));
        filters.addFilter(new GenreFilter("Romance"));
        ArrayList<Rating> averageRatings = fourthRatings.getAverageRatingsByFilter(1, filters);
        System.out.println(averageRatings.size() + " movie matched");
        Collections.sort(averageRatings);
        averageRatings.forEach(averageRating -> {
            System.out.println(averageRating.getValue() + " " + MovieDatabase.getYear(averageRating.getItem()) + " " + MovieDatabase.getTitle(averageRating.getItem()) + " \n     " + MovieDatabase.getGenres(averageRating.getItem()));
        });
        System.out.println("\n");
    }
    
    public static void printAverageRatingsByDirectorsAndMinutes() {
    	FourthRatings fourthRatings = basicCall();
        AllFilters filters = new AllFilters();
        filters.addFilter(new MinutesFilter(30, 170));
        filters.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));
        ArrayList<Rating> averageRatings = fourthRatings.getAverageRatingsByFilter(1, filters);
        System.out.println(averageRatings.size() + " movie matched");
        Collections.sort(averageRatings);
        averageRatings.forEach(averageRating -> {
            System.out.println(averageRating.getValue() + "  Time:" + MovieDatabase.getMinutes(averageRating.getItem()) + " " + MovieDatabase.getTitle(averageRating.getItem()) + " \n     " + MovieDatabase.getDirector(averageRating.getItem()));
        });
        System.out.println("\n");
    }
    
    public static void printSimilarRatings(){
    	FourthRatings fourthRatings = fullCall();
    	ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings("65", 20, 5);
    	System.out.print("the movie returned with the top rated average is: " );
        System.out.println(MovieDatabase.getTitle(similarRatings.get(0).getItem()));
        System.out.println("\n");
    }
    
    public static void printSimilarRatingsByGenre() {
    	FourthRatings fourthRatings = fullCall();
    	ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter("65", 20, 5, new GenreFilter("Action"));
    	System.out.print("the movie returned with the top rated average is: " );
        System.out.println(MovieDatabase.getTitle(similarRatings.get(0).getItem()));
        System.out.println("\n");
    }
    
    public static void printSimilarRatingsByDirector(){
    	FourthRatings fourthRatings = fullCall();
    	ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter("1034", 10, 3, new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone"));
    	System.out.print("the movie returned with the top rated average is: " );
        System.out.println(MovieDatabase.getTitle(similarRatings.get(0).getItem()));
        System.out.println("\n");    	
    }
        
    public static void printSimilarRatingsByGenreAndMinutes() {
    	FourthRatings fourthRatings = fullCall();
        AllFilters filters = new AllFilters();
        filters.addFilter(new MinutesFilter(100, 200));
        filters.addFilter(new GenreFilter("Adventure"));    	
    	ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter("65", 10, 5, filters);
    	System.out.print("the movie returned with the top rated average is: " );
        System.out.println(MovieDatabase.getTitle(similarRatings.get(0).getItem()));
        System.out.println("\n");    	
    }
    
    public static void printSimilarRatingsByYearAfterAndMinutes() {
    	FourthRatings fourthRatings = fullCall();
        AllFilters filters = new AllFilters();
        filters.addFilter(new YearAfterFilter(2000));
        filters.addFilter(new MinutesFilter(80, 100));    	
    	ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter("65", 10, 5, filters);
    	System.out.print("the movie returned with the top rated average is: " );
        System.out.println(MovieDatabase.getTitle(similarRatings.get(0).getItem()));
        System.out.println("\n");    	
    }

    private static FourthRatings fullCall() {
		FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
		return fourthRatings;
	}
    
	private static FourthRatings basicCall() {
		FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
		return fourthRatings;
	}
}