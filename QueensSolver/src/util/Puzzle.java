package util;

/**
 * Represents the playing grid for a puzzle.
 * @author Joel Gibson
 */
public class Puzzle {
	
	/**
	 * The number of rows/columns.
	 */
	private int dimension;
	
	/**
	 * The squares of the playing grid.
	 */
	private Square[][] grid;
	
	/**
	 * Creates a new puzzle from the given grid.
	 * @param grid the grid containing the region label of each square
	 */
	public Puzzle(char[][] grid) {
		this.dimension = grid.length;
		this.grid = new Square[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				this.grid[i][j] = new Square(grid[i][j]);
			}
		}
	}
	
	public char getRegion(int row, int col) {
		return grid[row][col].getRegion();
	}

	public boolean isCrowned(int row, int col) {
		return grid[row][col].isCrowned();
	}
	
	public void crown(int row, int col) {
		grid[row][col].crown();
	}
	
	public void removeCrown(int row, int col) {
		grid[row][col].removeCrown();
	}
	
}
