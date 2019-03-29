package ui.clui;

import java.util.Scanner;

public class CommandLineInterpreter {
		
	public CommandLineInterpreter() {}
	
	public String readCommand() {
		Scanner sc = new Scanner(System.in);
		String command = sc.nextLine();
		sc.close();
		return command;
	}
	
	public String readCommand(String s) {
		Scanner sc = new Scanner(System.in);
		System.out.println(s);
		String command = sc.nextLine();
		sc.close();
		return command;
	}
	
	public Command parseCommand(String s) {
		String[] commandLine = s.split(" ");
		String command = commandLine[0];
		for (Command c : Command.values()) {
			if (command.equals(c.getKeyword())) {
				return c;
			}
		}
		return null;
	}

}
