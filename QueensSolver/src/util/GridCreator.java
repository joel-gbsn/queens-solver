package util;

/**
 * Contains methods for creating printable puzzle grids.
 * @author Joel Gibson
 */
public class GridCreator {
	
	/**
	 * The puzzle to be displayed.
	 */
	private Puzzle puzzle;
	
	/**
	 * The displayable components of the puzzle.
	 */
	private String[][] grid;
	
	/**
	 * The size of the grid.
	 */
	private int gridSize;
	
	/**
	 * The unicode representation for a single vertical line.
	 */
	private String verticalLine = "\u2503";
	
	/**
	 * The unicode representation for 3 horizontal lines
	 */
	private String horizontalLine = "\u2501" + "\u2501" + "\u2501";
	
	/**
	 * Creates a GridCreator for the given puzzle.
	 * @param puzzle the puzzle to be displayed
	 */
	public GridCreator(Puzzle puzzle) {
		this.puzzle = puzzle;
	}
	
	/**
	 * Creates a display grid representing the puzzle.
	 * @return the display grid
	 */
	public String[][] createGrid() {
		if (grid == null) {
			// create an empty display grid
			gridSize = puzzle.getSize() * 2 + 1;
			grid = new String[gridSize][gridSize];
			
			// add all borders to the display grid
			addOuterBorders();
			addRegionBorders();
			joinBorders();
		}
		
		addCrowns();
		return grid;
	}
	
	/**
	 * Adds symbols for crowned squares to the display grid.
	 */
	protected void addCrowns() {
		for (int i = 0; i < puzzle.getSize(); i++) {
			for (int j = 0; j < puzzle.getSize(); j++) {
				if (puzzle.isCrowned(i, j)) {
					// add a cross symbol to represent a crowned square
					grid[2 * i + 1][2 * j + 1] = " \u00D7 ";
				} else {
					grid[2 * i + 1][2 * j + 1] = "   ";
				}
			}
		}
	}
	
	/**
	 * Adds the outer borders of the puzzle to the display grid.
	 */
	protected void addOuterBorders() {
		// add outer vertical borders
		for (int i = 1; i < gridSize; i = i + 2) {
			grid[i][0] = verticalLine;
			grid[i][gridSize - 1] = verticalLine;
		}
		
		// add outer horizontal borders
		for (int j = 1; j < gridSize; j = j + 2) {
			grid[0][j] = horizontalLine;
			grid[gridSize - 1][j] = horizontalLine;
		}
	}
	
	/**
	 * Adds the inner borders between regions to the display grid.
	 */
	protected void addRegionBorders() {
		int puzzleSize = puzzle.getSize();
		
		// add inner vertical borders
		for (int i = 0; i < puzzleSize; i++) {
			for (int j = 0; j < puzzleSize - 1; j++) {
				if (puzzle.getRegion(i, j) != puzzle.getRegion(i, j + 1)) {
					grid[i * 2 + 1][(j + 1) * 2] = verticalLine;
				} else {
					grid[i * 2 + 1][(j + 1) * 2] = " ";
				}
			}
		}
		
		// add inner horizontal borders
		for (int i = 0; i < puzzleSize - 1; i++) {
			for (int j = 0; j < puzzleSize; j++) {
				if (puzzle.getRegion(i, j) != puzzle.getRegion(i + 1, j)) {
					grid[(i + 1) * 2][j * 2 + 1] = horizontalLine;
				} else {
					grid[(i + 1) * 2][j * 2 + 1] = "   ";
				}
			}
		}
	}
	
	/**
	 * Joins all borders by adding the appropriate line intersection symbols to the display grid.
	 */
	protected void joinBorders() {
		for (int i = 0; i < gridSize; i = i + 2) {
			for (int j = 0; j < gridSize; j = j + 2) {
				// find which borders are present around the current grid location
				String code = "";
				
				// check for left border
				if (isValid(i, j - 1) && grid[i][j - 1].equals(horizontalLine)) {
					code += "L";
				}
				
				// check for right border
				if (isValid(i, j + 1) && grid[i][j + 1].equals(horizontalLine)) {
					code += "R";
				}
				
				// check for upper border
				if (isValid(i - 1, j) && grid[i - 1][j].equals(verticalLine)) {
					code += "U";
				}
				
				// check for lower border
				if (isValid(i + 1, j) && grid[i + 1][j].equals(verticalLine)) {
					code += "D";
				}
				
				grid[i][j] = getLineIntersection(code);
			}
		}
	}
	
	/**
	 * Gets the line intersection symbol required to join the borders represented by the given code.
	 * @param code the code representing which surrounding borders are present. In order: "L" for left,
	 * "R" for right, "U" for up, "D" for down. A missing letter means that border is not present
	 * @return the unicode for the line intersection symbol, or a single space if no surrounding borders
	 * are present
	 */
	protected String getLineIntersection(String code) {
		switch(code) {
		case "LRUD":
			return "\u254B";
		case "LRU":
			return "\u253B";
		case "LRD":
			return "\u2533";
		case "LUD":
			return "\u252B";
		case "RUD":
			return "\u2523";
		case "LR":
			return "\u2501";
		case "UD":
			return "\u2503";
		case "LU":
			return "\u251B";
		case "RU":
			return "\u2517";
		case "LD":
			return "\u2513";
		case "RD":
			return "\u250F";
		default:
			return " ";
		}
	}
	
	/**
	 * Checks if the given row and column is a valid position in the display grid.
	 * @param row the row
	 * @param col the column
	 * @return true if the position is valid, otherwise false
	 */
	protected boolean isValid(int row, int col) {
		return row >= 0 && row < gridSize && col >= 0 && col < gridSize;
	}
}
