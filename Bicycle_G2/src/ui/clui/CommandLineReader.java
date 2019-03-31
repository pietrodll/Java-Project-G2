package ui.clui;

import java.util.Scanner;

public class CommandLineReader {
	
	private Scanner sc;
		
	public CommandLineReader() {
		super();
		sc = new Scanner(System.in);
	}
	
	public String readCommand() {
		String command = sc.nextLine();
		return command;
	}
	
	public String readCommand(String s) {
		System.out.println(s);
		String command = sc.nextLine();
		return command;
	}
	
	public Command parseCommand(String s) throws InvalidCommandException {
		String com = s.split(" ")[0];
		for (Command c : Command.values()) {
			if (com.equals(c.getKeyword())) {
				return c;
			}
		}
		throw new InvalidCommandException(com);
	}
	
	public String[] parseArgs(String s) {
		String s1 = s.substring(s.indexOf('<')+1, s.lastIndexOf('>'));
		return s1.split(">{1} *<{1}");
	}
	
	public void close() {
		sc.close();
	}
	
	public static void main(String[] args) {
		CommandLineController clc = new CommandLineController();
		CommandLineDisplay cld = new CommandLineDisplay();
		CommandLineReader clr = new CommandLineReader();
		cld.display("Welcome to the myVelib system. You can type \"help\" to see the possible commands and \"exit\" to stop the system");
		String instruction = clr.readCommand("Please write your instruction:");
		while (!instruction.equalsIgnoreCase("exit")) {
			Command com = null;
			try {
				com = clr.parseCommand(instruction);
				cld.display("Your command is: " + com.getKeyword());
			} catch (InvalidCommandException e) {
				instruction = clr.readCommand("Your instruction is invalid. Please write another instruction.");
				continue;
			}
			switch (com) {
			case SETUP:
				
			}
			instruction = clr.readCommand("Please write your instruction:");
		}
		cld.display("It has been a pleasure to work for you.");
	}

}
