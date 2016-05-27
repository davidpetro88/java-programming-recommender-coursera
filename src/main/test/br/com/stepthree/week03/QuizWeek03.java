package br.com.stepthree.week03;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.com.stepone.week01.Rating;
import br.com.stepthree.week03.AllFilters;
import br.com.stepthree.week03.DirectorsFilter;
import br.com.stepthree.week03.GenreFilter;
import br.com.stepthree.week03.MinutesFilter;
import br.com.stepthree.week03.MovieDatabase;
import br.com.stepthree.week03.ThirdRatings;
import br.com.stepthree.week03.YearAfterFilter;

public class QuizWeek03 {
	private ThirdRatings thirdRatings;
	
	@Before
	public void setup() {
		thirdRatings = new ThirdRatings("data/ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
	}
	
	@Test
    public void testQuiz4() {
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatings(35);
        System.out.println(averageRatings.size() + " movie matched");
        assertEquals(29, averageRatings.size());
	}
	
	@Test
	public void testQuiz5() {
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(20, new YearAfterFilter(2000));
        System.out.println(averageRatings.size() + " movie matched");		
        assertEquals(88, averageRatings.size());
	}

	@Test
	public void testQuiz6() {
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(20, new GenreFilter("Comedy"));
        System.out.println(averageRatings.size() + " movie matched");		
        assertEquals(19, averageRatings.size());
	}
	
	@Test
	public void testQuiz7() {
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(5, new MinutesFilter(105, 135));
        System.out.println(averageRatings.size() + " movie matched");		
        assertEquals(231, averageRatings.size());
	}
	
	@Test
	public void testQuiz8() {
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(4, new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        System.out.println(averageRatings.size() + " movie matched");		
        assertEquals(22, averageRatings.size());
	}

	@Test
	public void testQuiz9() {
        AllFilters filters = new AllFilters();
        filters.addFilter(new YearAfterFilter(1990));
        filters.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(8, filters);
        System.out.println(averageRatings.size() + " movie matched");		
        assertEquals(132, averageRatings.size());
	}
	
	@Test 
	public void testQuiz10() {
        AllFilters filters = new AllFilters();
        filters.addFilter(new MinutesFilter(90, 180));
        filters.addFilter(new DirectorsFilter( "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(3, filters);
        System.out.println(averageRatings.size() + " movie matched");
        assertEquals(15, averageRatings.size());
	}	
}