package ui;

import java.util.Scanner;

import processor.Processor;

public class UserInterface {
	
	private Processor processor;
	
	private Scanner scanner;
	
	public UserInterface(Processor processor) {
		this.processor = processor;
		this.scanner = new Scanner(System.in);
	}
	
	public void start() {
		printHeading();
		String filename = getFileName();
		
		if (!processor.loadPuzzle(filename)) {
			return;
		}
		
		if (!processor.solvePuzzle()) {
			return;
		}
		
		printPuzzle();
	}
	
	private void printHeading() {
		String heading = "\u2666 \u2666 Queens Solver \u2666 \u2666";
		System.out.println(heading + "\n");
	}
	
	private String getFileName() {
		System.out.print("Enter filename: ");
		String filename = "";
		while(filename.isEmpty()) {
			filename = scanner.nextLine().trim();
		}
		return filename;
	}

	private void printPuzzle() {
		System.out.println("\nSolution:");
		String[][] grid = processor.getPrintableGrid();
		for (String[] row : grid) {
			System.out.println(String.join("", row));
		}
	}
	
	public static void printError(String message) {
		System.out.println("\nError: " + message);
	}
}
