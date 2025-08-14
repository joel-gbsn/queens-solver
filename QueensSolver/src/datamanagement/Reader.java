package datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ui.UserInterface;
import util.Puzzle;

/**
 * Reader class for loading puzzles from file.
 * @author Joel Gibson
 */
public class Reader {
	
	/**
	 * Creates a puzzle from the given file.
	 * @param filename the file to read
	 * @return the puzzle
	 */
	public Puzzle readPuzzle(String filename) {
		// list of region labels in each row
		List<char[]> rows = new ArrayList<>();
		
		BufferedReader br = null;
		try {	
			br = new BufferedReader(new FileReader(new File(filename)));
			
			// read each line from the file
			String line = "";
			while ((line = br.readLine()) != null) {
				rows.add(line.toCharArray());
			}
		} catch (IOException e) {
			UserInterface.printError("could not read file.");
			return null;
		}
		
		// close the file reader
		try {
			br.close();
		} catch (IOException e) {
			UserInterface.printError("could not close file.");
			return null;
		}
		
		char[][] grid = rows.toArray(new char[0][0]);
		
		// check if the puzzle is valid
		if (!isValidDimension(grid)) {
			UserInterface.printError("invalid puzzle dimensions.");
			return null;
		}
		
		if (!isValidRegionNumber(grid)) {
			UserInterface.printError("invalid number of regions.");
			return null;
		}
		
		// create puzzle using the grid of region labels
		return new Puzzle(grid);
	}
	
	/**
	 * Checks if the dimension of the given puzzle grid is valid (the number of rows and columns should
	 * be equal, and the length of each row/column should be greater than 3).
	 * @param grid the grid of region labels
	 * @return true if grid dimension is valid, otherwise false
	 */
	protected boolean isValidDimension(char[][] grid) {
		int numRows = grid.length;
		
		// check the length of each row
		for (char[] row : grid) {
			if (row.length != numRows) {
				return false;
			}
		}
		
		// puzzles with side length less than 4 have no valid solution
		return numRows > 3;
	}
	
	/**
	 * Checks if the number of regions in the given puzzle grid is valid (the number of regions should
	 * be the same as the number of rows and columns).
	 * @param grid the grid of region labels
	 * @return true is number of regions is valid, otherwise false
	 */
	protected boolean isValidRegionNumber(char[][] grid) {
		// find each region label
		Set<Character> regions = new HashSet<>();
		for (char[] row : grid) {
			for (char elem : row) {
				regions.add(elem);
			}
		}
		return grid.length == regions.size();
	}
}
