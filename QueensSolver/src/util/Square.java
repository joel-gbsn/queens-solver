package util;

/**
 * Represents a square on the puzzle grid.
 * @author Joel Gibson
 */
public class Square {
	
	/**
	 * The row number.
	 */
	private int row;
	
	/**
	 * The column number.
	 */
	private int col;
	
	/**
	 * The region label.
	 */
	private char region;
	
	/**
	 * Whether the square is currently crowned.
	 */
	private boolean crowned;
	
	/**
	 * Creates a square with the given parameters.
	 * @param row the row number
	 * @param col the column number
	 * @param region the region label
	 */
	public Square(int row, int col, char region) {
		this.row = row;
		this.col = col;
		this.region = region;
	}
	
	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * @return the column
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @return the region
	 */
	public char getRegion() {
		return region;
	}

	/**
	 * @return true if the square is currently crowned, otherwise false
	 */
	public boolean isCrowned() {
		return crowned;
	}
	
	/**
	 * Places a crown on the square.
	 */
	public void addCrown() {
		this.crowned = true;
	}
	
	/**
	 * Removes the crown from the square.
	 */
	public void removeCrown() {
		this.crowned = false;
	}
}
