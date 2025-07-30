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

public class Reader {
	
	public Puzzle readPuzzle(String filename) {
		
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
			UserInterface.printError("could not read file.");
			return null;
		}
		
		char[][] grid = rows.toArray(new char[0][0]);
		
		if (!isValidDimension(grid)) {
			UserInterface.printError("invalid puzzle dimensions.");
			return null;
		}
		
		if (!isValidRegionNumber(grid)) {
			UserInterface.printError("invalid number of regions.");
			return null;
		}
		
		return new Puzzle(grid);
	}
	
	protected boolean isValidDimension(char[][] grid) {
		int numRows = grid.length;
		for (char[] row : grid) {
			if (row.length != numRows) {
				return false;
			}
		}
		return numRows > 3;
	}
	
	protected boolean isValidRegionNumber(char[][] grid) {
		Set<Character> regions = new HashSet<>();
		for (char[] row : grid) {
			for (char elem : row) {
				regions.add(elem);
			}
		}
		return grid.length == regions.size();
	}
}
