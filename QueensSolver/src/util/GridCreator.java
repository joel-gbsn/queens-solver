package util;

/**
 * This class contains methods for creating printable puzzle grids.
 * @author Joel Gibson
 */
public class GridCreator {
	
	private Puzzle puzzle;
	
	private String[][] grid;
	
	private int gridSize;
	
	private String verticalLine = "\u2503";
	private String horizontalLine = "\u2501" + "\u2501" + "\u2501";
	
	public GridCreator(Puzzle puzzle) {
		this.puzzle = puzzle;
	}
	
	public String[][] createGrid() {
		gridSize = puzzle.getSize() * 2 + 1;
		grid = new String[gridSize][gridSize];
		
		addBorders();
		addCrowns();
		
		return grid;
	}
	
	protected void addCrowns() {
		for (int i = 0; i < puzzle.getSize(); i++) {
			for (int j = 0; j < puzzle.getSize(); j++) {
				if (puzzle.isCrowned(i, j)) {
					grid[2 * i + 1][2 * j + 1] = " \u00D7 ";
				} else {
					grid[2 * i + 1][2 * j + 1] = "   ";
				}
			}
		}
	}
	
	protected void addMainBorders() {
		// add inner vertical borders
		for (int i = 0; i < puzzle.getSize(); i++) {
			for (int j = 0; j < puzzle.getSize() - 1; j++) {
				if (puzzle.getRegion(i, j) != puzzle.getRegion(i, j + 1)) {
					grid[i * 2 + 1][(j + 1) * 2] = verticalLine;
				} else {
					grid[i * 2 + 1][(j + 1) * 2] = " ";
				}
			}
		}
		
		// add inner horizontal borders
		for (int i = 0; i < puzzle.getSize() - 1; i++) {
			for (int j = 0; j < puzzle.getSize(); j++) {
				if (puzzle.getRegion(i, j) != puzzle.getRegion(i + 1, j)) {
					grid[(i + 1) * 2][j * 2 + 1] = horizontalLine;
				} else {
					grid[(i + 1) * 2][j * 2 + 1] = "   ";
				}
			}
		}
		
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
	
	protected void joinBorders() {
		for (int i = 0; i < gridSize; i = i + 2) {
			for (int j = 0; j < gridSize; j = j + 2) {
				String code = "";
				
				if (isValid(i, j - 1) && grid[i][j - 1].equals(horizontalLine)) {
					code += "L";
				}
				
				if (isValid(i, j + 1) && grid[i][j + 1].equals(horizontalLine)) {
					code += "R";
				}
				
				if (isValid(i - 1, j) && grid[i - 1][j].equals(verticalLine)) {
					code += "U";
				}
				
				if (isValid(i + 1, j) && grid[i + 1][j].equals(verticalLine)) {
					code += "D";
				}
				
				grid[i][j] = getIntersection(code);
				
			}
		}
		
	}
	
	protected String getIntersection(String code) {
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
	
	protected boolean isValid(int row, int col) {
		return row >= 0 && row < gridSize && col >= 0 && col < gridSize;
	}
	
	protected void addBorders() {
		addMainBorders();
		joinBorders();
	}
}
