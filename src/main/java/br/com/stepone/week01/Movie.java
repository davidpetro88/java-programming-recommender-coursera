package br.com.stepone.week01;

/**
 * Created by david on 5/22/16.
 */
public class Movie {

	private String id;
	private String title;
	private int year;
	private String genres;
	private String director;
	private String country;
	private String poster;
	private int minutes;

	public Movie() {}

	public Movie(String anID, String aTitle, String aYear, String theGenres) {
		// just in case data file contains extra whitespace
		id = anID.trim();
		title = aTitle.trim();
		year = Integer.parseInt(aYear.trim());
		genres = theGenres;
	}

	public Movie(String anID, String aTitle, String aYear, String theGenres, String aDirector, String aCountry,
			String aPoster, int theMinutes) {
		id = anID.trim();
		title = aTitle.trim();
		year = Integer.parseInt(aYear.trim());
		genres = theGenres;
		director = aDirector;
		country = aCountry;
		poster = aPoster;
		minutes = theMinutes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	@Override
	public String toString() {
		return "Movie{" + "id='" + id + '\'' + ", title='" + title + '\'' + ", year=" + year + ", genres='" + genres
				+ '\'' + ", director='" + director + '\'' + ", country='" + country + '\'' + ", poster='" + poster
				+ '\'' + ", minutes=" + minutes + '}';
	}
}