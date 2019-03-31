package ui.clui;

import java.util.Scanner;

import controller.ExistingNameException;
import controller.InexistingNetworkNameException;
import controller.InexistingSlotIdException;
import controller.InexistingStationIdException;
import tools.NegativeTimeException;

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
			} catch (InvalidCommandException e) {
				instruction = clr.readCommand("Your instruction is invalid. Please write another instruction.");
				continue;
			}
			String[] arg = clr.parseArgs(instruction);
			switch (com) {
			case SETUP:
				try {
					clc.setup(arg);
				} catch (ExistingNameException | InvalidArgumentsException e) {
					cld.display(e.getMessage());
					instruction = clr.readCommand("Please write your instruction:");
					continue;
				}
				break;
			case RUNTEST:
				cld.display("Not yet implemented");
				break;
			case STATION_ONLINE:
				try {
					clc.stationOnline(arg);
				} catch (InexistingNetworkNameException | InexistingStationIdException | InvalidArgumentsException e) {
					cld.display(e.getMessage());
					instruction = clr.readCommand("Please write your instruction:");
					continue;
				}
				break;
			case STATION_OFFLINE:
				try {
					clc.stationOffline(arg);
				} catch (InexistingNetworkNameException | InexistingStationIdException | InvalidArgumentsException e) {
					cld.display(e.getMessage());
					instruction = clr.readCommand("Please write your instruction:");
					continue;
				}
				break;
			case SLOT_ONLINE:
				try {
					clc.slotOnline(arg);
				} catch (InexistingNetworkNameException | InexistingSlotIdException | NegativeTimeException | InvalidArgumentsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case SLOT_OFFLINE:
				break;
			case ADD_STATION:
				break;
			case ADD_SLOT:
				break;
			case ADD_USER:
				break;
			case ADD_BIKE:
				break;
			case RETURN_BIKE:
				break;
			case RENT_BIKE:
				break;
			case DISPLAY_USER:
				break;
			case DISPLAY_STATION:
				break;
			case DISPLAY:
				break;
			case SORT_STATION:
				break;
			case CALCULATE_ITINERARY:
				break;
			}
			instruction = clr.readCommand("Please write your instruction:");
		}
		cld.display("It has been a pleasure to work for you.");
		clr.close();
	}

}
