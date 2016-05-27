package br.com.steptwo.week04;

import java.util.ArrayList;
import java.util.Collections;

import br.com.stepone.week01.Rating;
import br.com.steptwo.week03.AllFilters;
import br.com.steptwo.week03.DirectorsFilter;
import br.com.steptwo.week03.GenreFilter;
import br.com.steptwo.week03.MinutesFilter;
import br.com.steptwo.week03.MovieDatabase;
import br.com.steptwo.week03.YearAfterFilter;

public class MovieRunnerSimilarRatingsShortFile {
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
    
	private static FourthRatings basicCall() {
		FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings_short.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
		return fourthRatings;
	}
}