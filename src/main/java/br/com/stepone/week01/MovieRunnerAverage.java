package br.com.stepone.week01;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by david on 5/22/16.
 */

public class MovieRunnerAverage {
    /*
    public void printAverageRatings() {
        SecondRatings sr = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        System.out.println("The number of movies is " + sr.getMovieSize());
        System.out.println("The number of raters is " + sr.getRaterSize());

        //print a list of movies and their average ratings
        //for all those movies that have at least a
        //specified number of ratings, sorted by averages, lowest rating to highest rating
        //one movie per line (print its rating first, followed by its title)
        ArrayList<Rating> averageRatings =  sr.getAverageRatings(3);
        Collections.sort(averageRatings);
        for (Rating r : averageRatings) {
            System.out.println(r.getValue() + " " + sr.getTitle(r.getItem()));
        }
        System.out.println("\n");
    }

    //first create a SecondRatings object, reading in data from the movie and ratings data files
    //print out the average ratings for a specific movie title
    public void getAverageRatingOneMovie() {
        SecondRatings sr = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        String movieToFind = "The Godfather"; //change this to find another movie
        String curID = sr.getID(movieToFind);
        double averageRating = sr.getAverageByID(curID, 0);
        System.out.println("The average rating of " + movieToFind + " is " + averageRating);
    }
    */
}