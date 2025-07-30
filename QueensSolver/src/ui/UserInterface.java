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

}
