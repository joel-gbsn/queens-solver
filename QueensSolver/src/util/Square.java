package util;

/**
 * Represents a square on the playing grid.
 * @author Joel Gibson
 */
public class Square {
	
	/**
	 * The region (colour) the square is part of.
	 */
	private char region;
	
	/**
	 * Whether a crown has been placed on the square.
	 */
	private boolean crowned;
	
	/**
	 * Creates a new square with the given region.
	 * @param region the region/colour of the square
	 */
	public Square(char region) {
		this.region = region;
	}

	/**
	 * @return the region
	 */
	public char getRegion() {
		return region;
	}

	/**
	 * @return whether the square has been crowned
	 */
	public boolean isCrowned() {
		return crowned;
	}
	
	/**
	 * Places a crown on the square.
	 */
	public void crown() {
		this.crowned = true;
	}
	
	/**
	 * Removes the crown from the square.
	 */
	public void removeCrown() {
		this.crowned = false;
	}
}	
