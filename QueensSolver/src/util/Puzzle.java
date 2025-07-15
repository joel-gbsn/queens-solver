package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzle {
	
	private int dimension;
	
	private Cell[][] grid;
	
	private List<List<Cell>> regions;
	
	public Puzzle(int dimension) {
		this.dimension = dimension;
		this.grid = new Cell[dimension][dimension];
	}
	
	public void setGrid(List<char[]> lines) {
		Map<Character, List<Cell>> regions = new HashMap<>();
		
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				char region = lines.get(i)[j];
				Cell cell = new Cell(region);
				grid[i][j] = cell;
				
				if (!regions.containsKey(region)) {
					regions.put(region, new ArrayList<Cell>());
				}
				regions.get(region).add(cell);
			}
		}
	}

}
