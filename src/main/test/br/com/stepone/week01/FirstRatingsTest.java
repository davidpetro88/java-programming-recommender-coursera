package br.com.stepone.week01;

import org.junit.Before;
import org.junit.Test;

public class FirstRatingsTest {

	private FirstRatings firstRatings;
	
	@Before
	public void setup() {
		firstRatings = new FirstRatings();
	}

	@Test
	public void testCallLoadMovies () {
		firstRatings.testLoadMovies();
	}

	@Test
	public void testCallLoadRaters () {
		firstRatings.testLoadRaters();
	}
}