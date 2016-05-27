package br.com.steptwo.week02;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import br.com.stepone.week01.Rating;
import br.com.stepthree.week03.MovieDatabase;
import br.com.steptwo.week02.SecondRatings;

public class QuizWeek02 {
	private SecondRatings secondRatings;
	private static final double DELTA = 1e-15;
	
	@Before
	public void setup() {
		secondRatings = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
	}
	
	@Test
    public void testQuiz5() {
        String movieToFind = "The Maze Runner";
        String getIdMovie = secondRatings.getID(movieToFind);
        double averageRating = secondRatings.getAverageByID(getIdMovie, 0);
        System.out.println("The average rating of " + movieToFind + " is " + averageRating);
        assertEquals(7.1875, averageRating, DELTA);
    }
    
	@Test
    public void testQuiz6() {
        String movieToFind = "Moneyball";
        String getIdMovie = secondRatings.getID(movieToFind);
        double averageRating = secondRatings.getAverageByID(getIdMovie, 0);
        System.out.println("The average rating of " + movieToFind + " is " + averageRating);
        assertEquals(8.375, averageRating, DELTA);
    }
    
	@Test
    public void testQuiz7() {
        String movieToFind = "Vacation";
        String getIdMovie = secondRatings.getID(movieToFind);
        double averageRating = secondRatings.getAverageByID(getIdMovie, 0);
        System.out.println("The average rating of " + movieToFind + " is " + averageRating);
        assertEquals(6.8, averageRating, DELTA);
    }
    
	@Test
    public void testQuiz8() {
        ArrayList<Rating> averageRatings =  secondRatings.getAverageRatings(50);
        System.out.println("Total : " + averageRatings.size());
        assertEquals(7, averageRatings.size());
    }
    
	@Test
    public void testQuiz9() {
        ArrayList<Rating> averageRatings =  secondRatings.getAverageRatings(20);
        Collections.sort(averageRatings);
        System.out.println(averageRatings.get(0).getValue() + " " + secondRatings.getTitle(averageRatings.get(0).getItem()));
        assertEquals("The Purge", secondRatings.getTitle(averageRatings.get(0).getItem()));
    }

	@Test
    public void testQuiz10() {
        ArrayList<Rating> averageRatings =  secondRatings.getAverageRatings(12);
        Collections.sort(averageRatings);
        System.out.println(averageRatings.get(0).getValue() + " " + secondRatings.getTitle(averageRatings.get(0).getItem()));
        assertEquals("Spring Breakers", secondRatings.getTitle(averageRatings.get(0).getItem()));
    }
}