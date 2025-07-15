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
		
		List<char[]> lines = new ArrayList<>();
		
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
				lines.add(line.toCharArray());
			}
			
		} catch (IOException e) {
			System.out.println("File error.");
			return null;
			
		} finally {
			// close the file readers
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				System.out.println("File error.");
				return null;
			}
		}
		
		if (!isValidDimension(lines)) {
			System.out.println("Incorrect puzzle dimensions.");
			return null;
		}
		
		if (!isValidRegionNumber(lines)) {
			System.out.println("Incorrect number of regions.");
			return null;
		}
		
		Puzzle puzzle = new Puzzle(lines.size());
		puzzle.setGrid(lines);
		return puzzle;
	}
	
	protected boolean isValidDimension(List<char[]> lines) {
		int numRows = lines.size();
		for (char[] line : lines) {
			if (line.length != numRows) {
				return false;
			}
		}
		return numRows > 3;
	}
	
	protected boolean isValidRegionNumber(List<char[]> lines) {
		int numRows = lines.size();
		Set<Character> regions = new HashSet<>();
		for (char[] line : lines) {
			for (char c : line) {
				regions.add(c);
			}
		}
		return numRows == regions.size();
	}
}
