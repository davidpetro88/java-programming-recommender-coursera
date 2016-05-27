package br.com.stepone.week03;

import org.junit.Before;
import org.junit.Test;

import br.com.steptwo.week03.FirstEfficientRater;

public class FirstEfficientRaterTest {
	private FirstEfficientRater firstEfficientRater;
	
	@Before
	public void setup() {
		firstEfficientRater = new FirstEfficientRater();
	}

	@Test
	public void testCallLoadMovies () {
		firstEfficientRater.testLoadMovies();
	}

	@Test
	public void testCallLoadRaters () {
		firstEfficientRater.testLoadRaters();
	}
}