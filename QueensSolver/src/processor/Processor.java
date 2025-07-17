package processor;

import java.util.Deque;
import java.util.LinkedList;

import datamanagement.Reader;
import util.Puzzle;

public class Processor {
	
	private Reader reader;
	
	private Puzzle puzzle;
	
	private Deque<Integer> crownedCols = new LinkedList<>();
	private Deque<Character> crownedRegions = new LinkedList<>();
	
	public Processor(Reader reader) {
		this.reader = reader;
		this.puzzle = reader.readPuzzle();
	}
	
	protected boolean isSquareAvailable(int row, int col) {
		if (crownedRegions.contains(puzzle.getRegion(row, col))) {
			return false;
		}
		
		if (crownedCols.contains(col)) {
			return false;
		}
		
		if (!crownedCols.isEmpty() && Math.abs(crownedCols.peek() - col) == 1) {
			return false;
		}
		
		return true;
	}
	
	protected void crown(int row, int col) {
		puzzle.crown(row, col);
		crownedCols.push(col);
		crownedRegions.push(puzzle.getRegion(row, col));
	}
	
	protected void removeCrown(int row, int col) {
		puzzle.removeCrown(row, col);
		crownedCols.pop();
		crownedRegions.pop();
	}
	
	public boolean solvePuzzle() {
		
		if (puzzle.isSolved()) {
			return true;
		}
		
		int row = puzzle.getNumCrowns();
		
		for (int col = 0; col < puzzle.getDimension(); col++) {
			
			if (!isSquareAvailable(row, col)) {
				continue;
			}
			
			crown(row, col);
			
			if (solvePuzzle()) {
				return true;
			}
			
			removeCrown(row, col);
		}
		
		return false;
	}
	
	public void print() {
		puzzle.print();
	}

}
