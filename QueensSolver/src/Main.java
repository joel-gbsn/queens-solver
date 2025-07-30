import datamanagement.Reader;
import processor.Processor;
import ui.UserInterface;

public class Main {

	public static void main(String[] args) {
		// set class dependencies
		Reader reader = new Reader();
		Processor processor = new Processor(reader);
		UserInterface ui = new UserInterface(processor);
		ui.start();
	}
}
