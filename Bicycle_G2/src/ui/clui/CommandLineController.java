package ui.clui;

import controller.ExistingNameException;
import controller.NetworkManager;

public class CommandLineController {
	
	private CommandLineReader cli;
	private CommandLineDisplay cld;
	private NetworkManager nm;
	
	public CommandLineController() {
		this.cli = new CommandLineReader();
		this.cld = new CommandLineDisplay();
		this.nm = new NetworkManager();
	}
	
	public void setup(String[] args) throws ExistingNameException {
		if (args.length == 1) {
			nm.setupNetwork(args[0]);
		} else if (args.length == 4) {
			String name = args[0];
			int nStat = Integer.parseInt(args[1]);
			int nSlot = Integer.parseInt(args[2]);
			double side = Double.parseDouble(args[3]);
			int nBikes = Integer.parseInt(args[4]);
			nm.setupNetwork(name, nStat, nSlot, side, nBikes);
		}
	}
	
	

}
