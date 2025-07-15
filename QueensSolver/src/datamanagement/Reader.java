package datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import util.Cell;
import util.Puzzle;

public class Reader {
	
	private String filename;
	
	public Reader(String filename) {
		this.filename = filename;
	}
	
	protected Puzzle readPuzzle() {
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
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
