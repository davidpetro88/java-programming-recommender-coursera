package br.com.stepfour.week04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.stepone.week01.Rating;
import br.com.stepthree.week03.Filter;
import br.com.stepthree.week03.MovieDatabase;
import br.com.stepthree.week03.Rater;
import br.com.stepthree.week03.TrueFilter;

public class FourthRatings {

	public double getAverageByID(String idMovie, Integer minimalRaters) {
		ArrayList<Rater> myRaters = RaterDatabase.getRaters();
		Long count = myRaters.stream().filter( rater -> rater.hasRating(idMovie)).count();
		if (count >= minimalRaters) {
			return myRaters.stream().filter(rater -> rater.getRating(idMovie) >= 0 ).map(r -> {
				return r.getRating(idMovie);	
			}).reduce((x,y) -> x+y).get().doubleValue() / count;
		}
		return 0.0;
	}
	
    private Double getAverageByID(String idMovie, Integer minimalRaters, ArrayList<Rating> topRaters) {
    	if(topRaters == null) return null;
        List<Rating> listTopRating = topRaters;
        Long count = listTopRating.stream().map(ratting -> { return RaterDatabase.getRater(ratting.getItem());})
        					  	  .filter(rt -> rt.hasRating(idMovie)).count();
        if (count >= minimalRaters) {
        	return listTopRating.stream().filter(rater ->  (RaterDatabase.getRater(rater.getItem()).getRating(idMovie) >= 0))
        						.map( rt -> { return RaterDatabase.getRater(rt.getItem()).getRating(idMovie) * rt.getValue(); })
        						.reduce((x,y) -> x+y).get().doubleValue() / count;
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
	
	public ArrayList<Rating> getSimilarRatings(String raterId, Integer numSimilarRaters, Integer minimalRaters ) {
        ArrayList<Rating> similar = getSimilarities(raterId);
        ArrayList<Rating> topRaters = getTopRaters(numSimilarRaters, similar);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        return movies.stream().map(movie -> 
        								     {  Double getAverageByID = getAverageByID(movie, minimalRaters, topRaters);
        								     	if (getAverageByID != null && getAverageByID > 0) return new Rating(movie, getAverageByID);
        								     	return new Rating();
        						             }
        						   )
        					  .filter(c -> c.getItem() != null)
        					  .sorted(Collections.reverseOrder())
        				      .collect(Collectors.toCollection(ArrayList::new));
	}

    public ArrayList<Rating> getSimilarRatingsByFilter(String raterId, Integer numSimilarRaters, Integer minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> similar = getSimilarities(raterId);
        ArrayList<Rating> topRaters = getTopRaters(numSimilarRaters, similar);
        return MovieDatabase.filterBy(filterCriteria).stream()
        											 .map(movie -> 
						        									{  
															     		Double getAverageByID = getAverageByID(movie, minimalRaters, topRaters);
															     		if (getAverageByID > 0) return new Rating(movie, getAverageByID);
															     		return new Rating();
															     	})
													 .filter(c -> c.getItem() != null)
													 .sorted(Collections.reverseOrder())
													 .collect(Collectors.toCollection(ArrayList::new));        
    }
	
    private ArrayList<Rating> getTopRaters(Integer numSimilarRaters, ArrayList<Rating> similar) {
    	if (similar.isEmpty()) return null;
		ArrayList<Rating> topRaters = new ArrayList<>();
        for (int i = 0; i < numSimilarRaters; i++) {
            topRaters.add(similar.get(i));
        }
		return topRaters;
	}

    private double dotProduct(Rater me, Rater r) {
    	if (me == null) return 0.0;
        List<String> getItemsRated = me.getItemsRated();
        return getItemsRated.stream()
        					.filter(itemRated -> (r.hasRating(itemRated)))
        					.map(item -> { return (5 - me.getRating(item)) * (5 - r.getRating(item));})
        					.reduce((x,y) -> x+y).orElse(0.0);
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        return RaterDatabase.getRaters().stream()
										.filter(rater -> !rater.equals(RaterDatabase.getRater(id)))
										.map(rat -> 
													{ 
														Double getDotProduct = dotProduct(RaterDatabase.getRater(id), rat);
														if (getDotProduct > 0) return new Rating(rat.getID(), getDotProduct);
														return new Rating();
													 }
										     )
										.filter(c -> c.getItem() != null)
										.sorted(Collections.reverseOrder())
										.collect(Collectors.toCollection(ArrayList::new));
    }
}