package ui.clui;

import java.util.Scanner;

public class CommandLineInterpreter {
		
	public CommandLineInterpreter() { super(); }
	
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

}
