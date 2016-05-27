package br.com.steptwo.week03;

public class MinutesFilter implements Filter {
	private int min;
	private int max;

    public MinutesFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }

	@Override
	public boolean satisfies(String id) {
        int length = MovieDatabase.getMinutes(id);
        return length >= min && length <= max;
	}
}