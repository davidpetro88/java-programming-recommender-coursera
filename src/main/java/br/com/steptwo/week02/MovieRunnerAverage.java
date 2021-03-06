package br.com.steptwo.week02;

import java.util.ArrayList;
import java.util.Collections;

import br.com.stepone.week01.Rating;

public class MovieRunnerAverage {

	private static SecondRatings secondRatings;

	public MovieRunnerAverage() {
		secondRatings = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
	}
	
	public static void main(String[] args) {
		new MovieRunnerAverage();
		printAverageRatings();
		getAverageRatingOneMovie();
	}
	
    public static void printAverageRatings() {
        System.out.println("The number of movies is " + secondRatings.getMovieSize());
        System.out.println("The number of raters is " + secondRatings.getRaterSize());
        ArrayList<Rating> averageRatings =  secondRatings.getAverageRatings(3);
        Collections.sort(averageRatings);
        averageRatings.forEach(averageRating -> {
        	System.out.println(averageRating.getValue() + " " + secondRatings.getTitle(averageRating.getItem()));
        });
        System.out.println("\n");
    }

    public static void getAverageRatingOneMovie() {
        String movieToFind = "The Godfather";
        double averageRating = secondRatings.getAverageByID(secondRatings.getID(movieToFind), 0);
        System.out.println("The average rating of " + movieToFind + " is " + averageRating);
    }
}