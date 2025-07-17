import datamanagement.Reader;
import processor.Processor;

public class Main {

	public static void main(String[] args) {
		Reader reader = new Reader("0002.txt");
		Processor processor = new Processor(reader);
		processor.solvePuzzle();
		processor.print();
	}

}
