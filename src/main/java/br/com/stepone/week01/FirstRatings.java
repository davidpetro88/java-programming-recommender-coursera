package br.com.stepone.week01;

import java.util.ArrayList;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;


/**
 * Created by david on 5/22/16.
 */
public class FirstRatings {

    public FirstRatings() {}

    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
        CSVParser csvParser = getParserFromFile(filename);
        csvParser.forEach(csvRecord -> {
            movieArrayList.add(new Movie(csvRecord.get("id"),csvRecord.get("title"),csvRecord.get("year"),
                    					 csvRecord.get("genre"),csvRecord.get("director"),csvRecord.get("country"),
                    					 csvRecord.get("poster"),Integer.parseInt(csvRecord.get("minutes"))));
        });
        return movieArrayList;
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> raterList = new ArrayList<>();
        FileResource fileResource = new FileResource(filename);

        
        
        for (CSVRecord record : fileResource.getCSVParser()) {
            String rater_id = record.get("rater_id");
            String movie_id = record.get("movie_id");
            Double rating = Double.valueOf(record.get("rating"));
            int idx = raterIndex(raterList, rater_id);
            if (idx == -1) {
                Rater curRater = new Rater(rater_id);
                curRater.addRating(movie_id, rating);
                raterList.add(curRater);
            } else {
                raterList.get(idx).addRating(movie_id, rating);
            }
        }
        return raterList;
    }
    
    private int raterIndex(ArrayList<Rater> raterList, String rater_id) {
        for (int i = 0; i < raterList.size(); i++) {
            if (raterList.get(i).getID().equals(rater_id)) return i;
        }
        return -1;
    }    
  
    private CSVParser getParserFromFile(String filename) {
        FileResource fileResource = new FileResource(filename);
        return fileResource.getCSVParser();
    }
}