package ui;

import java.util.Scanner;

import processor.Processor;

/**
 * The user interface for running the program.
 * @author Joel Gibson
 */
public class UserInterface {
	
	/**
	 * The processor for solving puzzles.
	 */
	private Processor processor;
	
	/**
	 * The scanner for getting user input.
	 */
	private Scanner scanner;
	
	/**
	 * Creates an instance of the class using the given processor.
	 * @param processor the processor for solving puzzles
	 */
	public UserInterface(Processor processor) {
		this.processor = processor;
		this.scanner = new Scanner(System.in);
	}
	
	/**
	 * Runs the main pipeline for solving a puzzle.
	 */
	public void start() {
		printHeading();
		String filename = getFileName();
		
		// read the file to load the puzzle
		if (!processor.loadPuzzle(filename)) {
			return;
		}
		
		// solve the puzzle to add all crowns
		if (!processor.solvePuzzle()) {
			return;
		}
		printPuzzle();
	}
	
	/**
	 * Displays the main program heading.
	 */
	private void printHeading() {
		String heading = "\u2666 \u2666 Queens Solver \u2666 \u2666";
		System.out.println(heading + "\n");
	}
	
	/**
	 * Prompts the user to enter the filename of a puzzle.
	 * @return the file name
	 */
	private String getFileName() {
		System.out.print("Enter filename: ");
		String filename = "";
		while(filename.isEmpty()) {
			filename = scanner.nextLine().trim();
		}
		return filename;
	}

	/**
	 * Displays the completed puzzle grid.
	 */
	private void printPuzzle() {
		System.out.println("\nSolution:");
		String[][] grid = processor.getPrintableGrid();
		for (String[] row : grid) {
			System.out.println(String.join("", row));
		}
	}
	
	/**
	 * Displays the given error message.
	 * @param message the message to print
	 */
	public static void printError(String message) {
		System.out.println("\nError: " + message);
	}
}
