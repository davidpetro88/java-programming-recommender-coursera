package br.com.steptwo.week03;

import java.util.ArrayList;

import br.com.stepone.week01.Movie;
import br.com.stepone.week01.Rating;

public class SecondRatings {
	private ArrayList<Movie> myMovies;
	private ArrayList<Rater> myRaters;

	public SecondRatings() { this("ratedmoviesfull.csv", "ratings.csv"); }

	public SecondRatings(String movieFile, String ratingsFile) {
		FirstRatings fr = new FirstRatings();
		this.myMovies = fr.loadMovies(movieFile);
		this.myRaters = fr.loadRaters(ratingsFile);
	}

	public int getMovieSize() { return myMovies.size();}

	public int getRaterSize() { return myRaters.size();}

	public double getAverageByID(String idMovie, Integer minimalRaters) {
		Long count = myRaters.stream().filter( rater -> rater.hasRating(idMovie)).count();
		if (count >= minimalRaters) {
			return myRaters.stream().filter(rater -> rater.getRating(idMovie) >= 0 ).map(r -> {
				return r.getRating(idMovie);	
			}).reduce((x,y) -> x+y).get().doubleValue() / count;
		}
		return 0.0;
	}

	public ArrayList<Rating> getAverageRatings(Integer minimalRaters) {
		ArrayList<Rating> output = new ArrayList<>();
		myMovies.forEach(movie -> {
			String getIdMovie = movie.getId();
			double curAverage = getAverageByID(getIdMovie, minimalRaters);
			if (curAverage > 0) output.add(new Rating(getIdMovie, curAverage));
		});
		return output;
	}

	public String getTitle(String idMovie) {
		return myMovies.stream().filter(movie -> (movie.getId().equals(idMovie)))
								.map(mv -> { return mv.getTitle(); })
								.findFirst().orElse("ID not found");
	}

	public String getID(String titleOfMovie) {
		return myMovies.stream().filter(movie -> (movie.getTitle().equals(titleOfMovie)))
								.map(mv -> { return mv.getId(); })
								.findFirst().orElse("No such title");
	}
}