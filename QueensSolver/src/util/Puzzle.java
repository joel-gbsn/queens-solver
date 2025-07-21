package util;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents the playing grid for a puzzle.
 * @author Joel Gibson
 */
public class Puzzle {
	
	/**
	 * The number of rows, columns and regions in the puzzle.
	 */
	private int size;
	
	/**
	 * The grid of squares making up the puzzle.
	 */
	private Square[][] grid;
	
	/**
	 * The set of currently crowned squares.
	 */
	private Set<Square> crownedSquares = new HashSet<>();
	
	/**
	 * Creates a new puzzle from the given grid of region labels.
	 * @param regions the grid containing the region label of each square
	 */
	public Puzzle(char[][] regions) {
		this.size = regions.length;
		this.grid = new Square[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.grid[i][j] = new Square(i, j, regions[i][j]);
			}
		}
	}
	
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Gets the region label of the square in the given row and column.
	 * @param row the row
	 * @param col the column
	 * @return the region label
	 */
	protected char getRegion(int row, int col) {
		return grid[row][col].getRegion();
	}
	
	/**
	 * Adds a crown to the square in the given row and column, if it does not violate any rules.
	 * @param row the row
	 * @param col the column
	 * @return true if a crown could be added, otherwise false
	 */
	public boolean addCrown(int row, int col) {
		if (isCrownable(row, col)) {
			Square square = grid[row][col];
			square.addCrown();
			crownedSquares.add(square);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes a crown from the square in the given row and column.
	 * @param row the row
	 * @param col the column
	 * @return true if crown could be removed, otherwise false
	 */
	public boolean removeCrown(int row, int col) {
		if (isCrowned(row, col)) {
			Square square = grid[row][col];
			square.removeCrown();
			crownedSquares.remove(square);
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if a crown can be validly placed on the square in the given row and column.
	 * @param row the row
	 * @param col the column
	 * @return true if square can be validly crowned, otherwise false
	 */
	protected boolean isCrownable(int row, int col) {
		// check if row, column or region has already been crowned
		for (Square square : crownedSquares) {
			if (row == square.getRow() || col == square.getCol()) {
				return false;
			}
			
			if (getRegion(row, col) == square.getRegion()) {
				return false;
			}
			
			// check diagonals
			if (Math.abs(row - square.getRow()) == 1 && Math.abs(col - square.getCol()) == 1) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Checks if the square in the given row and column has been crowned.
	 * @param row the row
	 * @param col the column
	 * @return true if square is crowned, otherwise false
	 */
	protected boolean isCrowned(int row, int col) {
		return grid[row][col].isCrowned();
	}
	
	/**
	 * Checks if the required number of squares have been crowned to complete the puzzle.
	 * @return true if the puzzle has been solved, otherwise false
	 */
	public boolean isSolved() {
		return crownedSquares.size() == size;
	}
	
	public String[][] createDisplayGrid() {
		int outputLength = size * 2 + 1;
		String[][] output = new String[outputLength][outputLength];
		
		// initialise with single blank spaces
		for (int i = 0; i < outputLength; i++) {
			for (int j = 0; j < outputLength; j++) {
				output[i][j] = " ";
			}
		}
		
		// add crowns
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				output[2 * i + 1][2 * j + 1] = " " + grid[i][j] + " ";
			}
		}
		
		// add vertical borders
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size - 1; j++) {
				if (getRegion(i, j) != getRegion(i, j + 1)) {
					output[i * 2 + 1][(j + 1) * 2] = "|";
				}
			}
		}
		
		// add horizontal borders
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size; j++) {
				if (getRegion(i, j) != getRegion(i + 1, j)) {
					output[(i + 1) * 2][j * 2 + 1] = "---";
				} else {
					output[(i + 1) * 2][j * 2 + 1] = "   ";
				}
			}
		}
		
		// add outside borders
		for (int i = 1; i < outputLength; i = i + 2) {
			output[i][0] = "|";
			output[i][outputLength - 1] = "|";
			output[0][i] = "---";
			output[outputLength - 1][i] = "---";
		}
		
		return output;
	}
}
