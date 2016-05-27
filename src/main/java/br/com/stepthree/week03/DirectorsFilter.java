package br.com.stepthree.week03;

import java.util.regex.Pattern;


public class DirectorsFilter implements Filter {
	private String directors;

    public DirectorsFilter(String dir) {
    	this.directors = dir;
    }

    @Override
    public boolean satisfies(String id) {
    	return Pattern.compile(",").splitAsStream(directors)
    							   .filter(director -> MovieDatabase.getDirector(id).contains(director))
    							   .findFirst()
    							   .isPresent();
    }

}
