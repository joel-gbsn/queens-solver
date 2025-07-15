package util;

public class Cell {
	
	private int row;
	private int col;
	private String region;
	private boolean crowned;
	
	public Cell(int row, int col, String region) {
		this.row = row;
		this.col = col;
		this.region = region;
	}
}
