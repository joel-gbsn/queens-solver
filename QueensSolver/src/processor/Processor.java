package processor;

import datamanagement.Reader;
import util.GridCreator;
import util.Puzzle;

/**
 * The processor for solving puzzles.
 * @author Joel Gibson
 */
public class Processor {
	
	/**
	 * The reader for loading puzzles from files.
	 */
	private Reader reader;
	
	/**
	 * The puzzle being solved.
	 */
	private Puzzle puzzle;
	
	/**
	 * Creates a new processor using the given reader.
	 * @param reader the reader for loading data
	 */
	public Processor(Reader reader) {
		this.reader = reader;
	}
	
	/**
	 * Loads and sets the puzzle from the given file.
	 * @param filename the file to read
	 * @return true if puzzle could be loaded and was valid, otherwise false
	 */
	public boolean loadPuzzle(String filename) {
		puzzle = reader.readPuzzle(filename);
		if (puzzle == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Solves the puzzle.
	 * @return true if the puzzle could be solved, otherwise false
	 */
	public boolean solvePuzzle() {
		return solveRow(0, puzzle.getSize());
	}
	
	/**
	 * Solves the puzzle starting from the given row.
	 * @param row the row to solve
	 * @param size the size of the puzzle (the number of rows, columns and regions)
	 * @return true if the puzzle could be solved, otherwise false
	 */
	protected boolean solveRow(int row, int size) {
		// check if correct number of squares have been crowned
		if (puzzle.isSolved()) {
			return true;
		}
		
		// test crowning each square in the row
		for (int col = 0; col < size; col++) {
			if (!puzzle.addCrown(row, col)) {
				continue;
			}
			
			// recursively crown the next row
			if (solveRow((row + 1) % size, size)) {
				return true;
			}
			puzzle.removeCrown(row, col);
		}
		return false;
	}
	
	/**
	 * Creates a printable grid of the puzzle.
	 * @return the grid
	 */
	public String[][] getPrintableGrid() {
		GridCreator creator = new GridCreator(puzzle);
		return creator.createGrid();
	}
}
