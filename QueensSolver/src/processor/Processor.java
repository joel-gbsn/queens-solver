package processor;

import datamanagement.Reader;
import util.GridCreator;
import util.Puzzle;

public class Processor {
	
	private Reader reader;
	
	private Puzzle puzzle;
	
	public Processor(Reader reader) {
		this.reader = reader;
		this.puzzle = reader.readPuzzle();
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
	
	public void print() {
		GridCreator creator = new GridCreator(puzzle);
		String[][] grid = creator.createGrid();
		for (String[] row : grid) {
			System.out.println(String.join("", row));
		}
	}

}
