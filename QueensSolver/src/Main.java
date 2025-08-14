import datamanagement.Reader;
import processor.Processor;
import ui.UserInterface;

/**
 * The entry point of the program.
 * @author Joel Gibson
 */
public class Main {

	public static void main(String[] args) {
		// set class dependencies
		Reader reader = new Reader();
		Processor processor = new Processor(reader);
		UserInterface ui = new UserInterface(processor);
		
		// execute the main pipeline
		ui.start();
	}
}
