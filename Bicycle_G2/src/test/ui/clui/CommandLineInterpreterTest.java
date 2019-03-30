package test.ui.clui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ui.clui.Command;
import ui.clui.CommandLineInterpreter;
import ui.clui.InvalidCommandException;

class CommandLineInterpreterTest {
	
	static CommandLineInterpreter cli;
	
	@BeforeAll
	static void setup() {
		cli = new CommandLineInterpreter();
	}

	@Test
	void testParseCommand() {
		assertAll(
			() -> {
				String command = "runtest <test1.txt>";
				assertEquals(Command.RUNTEST, cli.parseCommand(command));
			},
			() -> {
				String command = "setup <myVelib>";
				assertEquals(Command.SETUP, cli.parseCommand(command));
			},
			() -> {
				String command = "rentBike <myVelib> <5> <11>";
				assertEquals(Command.RENT_BIKE, cli.parseCommand(command));
			},
			() -> {
				String command = "rentbike <myVelib> <5> <11>";
				assertThrows(InvalidCommandException.class, () -> { cli.parseCommand(command); });
			}
		);
	}

	@Test
	void testParseArgs() {
		assertAll(
			() -> {
				String command = "runtest <test1.txt>";
				String[] args = {"test1.txt"};
				assertArrayEquals(args, cli.parseArgs(command));
			},
			() -> {
				String command = "setup <myVelib>";
				String[] args = {"myVelib"};
				assertArrayEquals(args, cli.parseArgs(command));
			},
			() -> {
				String command = "rentBike <myVelib>   <5><11>";
				String[] args = {"myVelib", "5", "11"};
				assertArrayEquals(args, cli.parseArgs(command));
			}
		);
	}

}
