package br.com.stepthree.week03;

import java.util.ArrayList;

import br.com.stepone.week01.Rating;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;

	public ThirdRatings() { this("ratings.csv"); }

	public ThirdRatings(String ratingsFile) {
		FirstRatings fr = new FirstRatings();
		this.myRaters = fr.loadRaters(ratingsFile);
	}

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
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		ArrayList<Rating> output = new ArrayList<>();
		movies.forEach(movie -> {
			String getIdMovie = movie;
			double curAverage = getAverageByID(getIdMovie, minimalRaters);
			if (curAverage > 0) output.add(new Rating(getIdMovie, curAverage));
		});
		return output;
	}
	
	public ArrayList<Rating> getAverageRatingsByFilter(Integer minimalRaters, Filter filterCriteria) {
		ArrayList<Rating> output = new ArrayList<>();
		ArrayList<String> idsSatisfied = MovieDatabase.filterBy(filterCriteria);
		idsSatisfied.forEach(id -> {
			double curAverage = getAverageByID(id, minimalRaters);
			if (curAverage > 0) output.add(new Rating(id, curAverage));
		});
		return output;
	}
}