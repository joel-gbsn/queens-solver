package datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Puzzle;

public class Reader {
	
	private String filename;
	
	public Reader(String filename) {
		this.filename = filename;
	}
	
	public Puzzle readPuzzle() {
		
		List<char[]> rows = new ArrayList<>();
		
		File file = new File(filename);
		FileReader fr = null;
		BufferedReader br = null;
		
		try {	
			// create readers to read the file
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			// read each line from the file
			String line = "";
			while ((line = br.readLine()) != null) {
				rows.add(line.toCharArray());
			}
			
		} catch (IOException e) {
			System.out.println("Error reading file.");
			return null;
			
		} finally {
			// close the file readers
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				System.out.println("Error reading file.");
				return null;
			}
		}
		
		char[][] grid = rows.toArray(new char[0][0]);
		
		if (!isValidDimension(grid)) {
			System.out.println("Invalid puzzle dimensions.");
			return null;
		}
		
		if (!isValidRegionNumber(grid)) {
			System.out.println("Invalid number of regions.");
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
