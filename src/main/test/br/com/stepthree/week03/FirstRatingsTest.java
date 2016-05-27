package br.com.stepthree.week03;

import org.junit.Before;
import org.junit.Test;

import br.com.stepthree.week03.FirstRatings;

public class FirstRatingsTest {

	private FirstRatings firstPlainRater;
	
	@Before
	public void setup() {
		firstPlainRater = new FirstRatings();
	}

	@Test
	public void testCallLoadMovies () {
		firstPlainRater.testLoadMovies();
	}

	@Test
	public void testCallLoadRaters () {
		firstPlainRater.testLoadRaters();
	}
}