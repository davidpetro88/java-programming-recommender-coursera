package br.com.stepthree.week03;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import br.com.stepone.week01.Rating;
import br.com.stepthree.week03.SecondRatings;

public class SecondEfficientRaterTest {

	private static final double DELTA = 1e-15;

	@Test
    public void testQuiz5() {
		SecondRatings secondRatings = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        String movieToFind = "The Maze Runner";
        String getIdMovie = secondRatings.getID(movieToFind);
        double averageRating = secondRatings.getAverageByID(getIdMovie, 0);
        System.out.println("The average rating of " + movieToFind + " is " + averageRating);
        assertEquals(7.1875, averageRating, DELTA);
    }
    
	@Test
    public void testQuiz6() {
		SecondRatings secondRatings = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        String movieToFind = "Moneyball";
        String getIdMovie = secondRatings.getID(movieToFind);
        double averageRating = secondRatings.getAverageByID(getIdMovie, 0);
        System.out.println("The average rating of " + movieToFind + " is " + averageRating);
        assertEquals(8.375, averageRating, DELTA);
    }
    
	@Test
    public void testQuiz7() {
		SecondRatings secondRatings = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        String movieToFind = "Vacation";
        String getIdMovie = secondRatings.getID(movieToFind);
        double averageRating = secondRatings.getAverageByID(getIdMovie, 0);
        System.out.println("The average rating of " + movieToFind + " is " + averageRating);
        assertEquals(6.8, averageRating, DELTA);
    }
    
	@Test
    public void testQuiz8() {
		SecondRatings secondRatings = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        ArrayList<Rating> averageRatings =  secondRatings.getAverageRatings(50);
        System.out.println("Total : " + averageRatings.size());
        assertEquals(7, averageRatings.size());
    }
    
	@Test
    public void testQuiz9() {
		SecondRatings secondRatings = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        ArrayList<Rating> averageRatings =  secondRatings.getAverageRatings(20);
        Collections.sort(averageRatings);
        System.out.println(averageRatings.get(0).getValue() + " " + secondRatings.getTitle(averageRatings.get(0).getItem()));
        assertEquals("The Purge", secondRatings.getTitle(averageRatings.get(0).getItem()));
    }

	@Test
    public void testQuiz10() {
		SecondRatings secondRatings = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        ArrayList<Rating> averageRatings =  secondRatings.getAverageRatings(12);
        Collections.sort(averageRatings);
        System.out.println(averageRatings.get(0).getValue() + " " + secondRatings.getTitle(averageRatings.get(0).getItem()));
        assertEquals("Spring Breakers", secondRatings.getTitle(averageRatings.get(0).getItem()));
    }
}