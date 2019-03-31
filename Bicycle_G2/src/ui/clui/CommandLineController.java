package ui.clui;

import java.time.LocalDateTime;

import card.CardFactory;
import controller.*;
import ride.Itinerary;
import ride.Network;
import ride.path.AvoidPlusStrategy;
import ride.path.FastestPathStrategy;
import ride.path.MinimalWalkingStrategy;
import ride.path.PreferPlusStrategy;
import ride.path.UniformityStrategy;
import sorting.station.LeastOccupiedStation;
import sorting.station.MoreUsedStation;
import station.NoBikeAvailableException;
import station.NoOngoingRideException;
import station.NoSlotAvailableException;
import station.OngoingRideException;
import station.Station;
import station.StationOfflineException;
import station.StationSamePositionException;
import station.TypeStationException;
import tools.Date;
import tools.NegativeTimeException;
import tools.NullDateException;
import tools.Point;
import user.User;

/**
 * 
 * @author pietr
 *
 */
public class CommandLineController {
	
	private CommandLineReader clr;
	private CommandLineDisplay cld;
	private NetworkManager nm;
	
	public CommandLineController() {
		this.clr = new CommandLineReader();
		this.cld = new CommandLineDisplay();
		this.nm = new NetworkManager();
	}
	
	/**
	 * This method applies command line instructions of the form: <br>
	 * {@code setup <networkName>} <br>
	 * or <br>
	 * {@code setup <networkName> <nStations> <nSlots> <side> <nBikes>}
	 * @param args an array of {@code String}
	 * @throws ExistingNameException
	 * @throws InvalidArgumentsException
	 */
	public void setup(String[] args) throws ExistingNameException, InvalidArgumentsException {
		if (args.length == 1) {
			nm.setupNetwork(args[0]);
		} else if (args.length == 4) {
			String name = args[0];
			int nStat = Integer.parseInt(args[1]);
			int nSlot = Integer.parseInt(args[2]);
			double side = Double.parseDouble(args[3]);
			int nBikes = Integer.parseInt(args[4]);
			nm.setupNetwork(name, nStat, nSlot, side, nBikes);
		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	/**
	 * This method applies command line instructions of the form: <br>
	 * {@code stationOnline <networkName> <stationID>}
	 * @param args an array of {@code String}
	 * @throws InexistingNetworkNameException
	 * @throws InexistingStationIdException
	 * @throws InvalidArgumentsException
	 */
	public void stationOnline(String[] args) throws InexistingNetworkNameException, InexistingStationIdException, InvalidArgumentsException {
		if (args.length == 2) {
			String netName = args[0];
			int stationId = Integer.parseInt(args[1]);
			nm.setStationOnline(netName, stationId);
		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	/**
	 * This method applies command line instructions of the form: <br>
	 * {@code stationOffline <networkName> <stationID>}
	 * @param args an array of {@code String}
	 * @throws InexistingNetworkNameException
	 * @throws InexistingStationIdException
	 * @throws InvalidArgumentsException
	 */
	public void stationOffline(String[] args) throws InexistingNetworkNameException, InexistingStationIdException, InvalidArgumentsException {
		if (args.length == 2) {
			String netName = args[0];
			int stationId = Integer.parseInt(args[1]);
			nm.setStationOffline(netName, stationId);
		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	/**
	 * This method applies command line instructions of the form: <br>
	 * {@code slotOnline <networkName> <slotID> <time>} <br>
	 * The {@code time} has to be on the following format: {@code YYYY-MM-dd HH:mm}
	 * @param args an array of {@code String}
	 * @throws InexistingNetworkNameException
	 * @throws InexistingSlotIdException
	 * @throws NegativeTimeException
	 * @throws InvalidArgumentsException
	 */
	public void slotOnline(String[] args) throws InexistingNetworkNameException, InexistingSlotIdException, NegativeTimeException, InvalidArgumentsException {
		if (args.length == 3) {
			String netName = args[0];
			int slotId = Integer.parseInt(args[1]);
			LocalDateTime time = Date.dateInput(args[2]);
			nm.setSlotOnline(netName, slotId, time);
		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	/**
	 * This method applies command line instructions of the form: <br>
	 * {@code slotOffline <networkName> <slotID> <time>} <br>
	 * The {@code time} has to be on the following format: {@code YYYY-MM-dd HH:mm}
	 * @param args an array of {@code String}
	 * @throws InexistingNetworkNameException
	 * @throws InexistingSlotIdException
	 * @throws NegativeTimeException
	 * @throws InvalidArgumentsException
	 */
	public void slotOffline(String[] args) throws InexistingNetworkNameException, InexistingSlotIdException, NegativeTimeException, InvalidArgumentsException {
		if (args.length == 3) {
			String netName = args[0];
			int slotId = Integer.parseInt(args[1]);
			LocalDateTime time = Date.dateInput(args[2]);
			nm.setSlotOffline(netName, slotId, time);
		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	/**
	 * This method applies command line instructions of the form: <br>
	 * {@code addStation <networkName> <type> <numSlots> <positionX> <positionY>} <br>
	 * The type has to be {@code stardard} or {@code plus}. The case is ignored.
	 * @param args an array of {@code String}
	 * @throws InexistingNetworkNameException 
	 * @throws StationSamePositionException 
	 * @throws TypeStationException 
	 * @throws InvalidArgumentsException 
	 */
	public void addStation(String[] args) throws InexistingNetworkNameException, TypeStationException, StationSamePositionException, InvalidArgumentsException {
		if (args.length == 5) {
			String name = args[0];
			Network net = nm.findNetworkByName(name);
			int nSlots = Integer.parseInt(args[2]);
			double x = Double.parseDouble(args[3]);
			double y = Double.parseDouble(args[4]);
			if (args[1].equalsIgnoreCase("plus")) {
				nm.addPlusStation(net, nSlots, x, y);
			} else if (args[1].equalsIgnoreCase("standard")) {
				nm.addStandardStation(net, nSlots, x, y);
			} else {
				throw new InvalidArgumentsException();
			}
		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	/**
	 * This method applies command line instructions of the form: <br>
	 * {@code addSlot <networkName> <stationID> <numSlots>}
	 * @param args an array of {@code String}
	 * @throws InexistingNetworkNameException 
	 * @throws InexistingStationIdException 
	 * @throws InvalidArgumentsException 
	 */
	public void addSlot(String[] args) throws InexistingNetworkNameException, InexistingStationIdException, InvalidArgumentsException {
		if (args.length == 3) {
			Network net = nm.findNetworkByName(args[0]);
			int stationID = Integer.parseInt(args[1]);
			int numSlots = Integer.parseInt(args[2]);
			nm.addSlot(net, stationID, numSlots);
		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	/**
	 * This method applies command line instructions of the form: <br>
	 * {@code addBike <networkName> <type> <time>} <br>
	 * The {@code time} has to be on the following format: {@code YYYY-MM-dd HH:mm}
	 * The type has to be {@code electric} or {@code mechanic}. The case is ignored.
	 * @param args an array of {@code String}
	 * @throws InexistingNetworkNameException 
	 * @throws NegativeTimeException 
	 * @throws InvalidArgumentsException 
	 */
	public void addBike(String[] args) throws InexistingNetworkNameException, NegativeTimeException, InvalidArgumentsException {
		if (args.length == 3) {
			Network net = nm.findNetworkByName(args[0]);
			LocalDateTime time = Date.dateInput(args[2]);
			if (args[1].equalsIgnoreCase("electric")) {
				nm.addElectricBike(net, time);
			} else if (args[1].equalsIgnoreCase("mechanic")) {
				nm.addMechanicBike(net, time);
			} else {
				throw new InvalidArgumentsException();
			}
		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	/**
	 * This method applies command line instructions of the form: <br>
	 * {@code addUser <username> <cardType> <networkName>} <br>
	 * The type has to be {@code vlibre}, {@code vmax} or {@code credit}. The case is ignored.
	 * @param args an array of {@code String}
	 * @throws InexistingNetworkNameException 
	 * @throws InvalidArgumentsException 
	 */
	public void addUser(String[] args) throws InexistingNetworkNameException, InvalidArgumentsException {
		if (args.length == 3) {
			if (args[1].equalsIgnoreCase("vlibre")) {
				nm.addUser(args[0], CardFactory.VLIBRE, args[2]);
			} else if (args[1].equalsIgnoreCase("vmax")) {
				nm.addUser(args[0], CardFactory.VMAX, args[2]);
			} else if (args[1].equalsIgnoreCase("credit")) {
				nm.addUser(args[0], CardFactory.CREDIT, args[2]);
			} else {
				throw new InvalidArgumentsException();
			}
		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	/**
	 * This method applies command line instructions of the form: <br>
	 * {@code returnBike <userID> <stationID> <time> <networkName>} <br>
	 * The {@code time} has to be on the following format: {@code YYYY-MM-dd HH:mm}
	 * @param args an array of {@code String}
	 * @throws InexistingNetworkNameException 
	 * @throws NullDateException 
	 * @throws NegativeTimeException 
	 * @throws InexistingStationIdException 
	 * @throws InexistingUserIdException 
	 * @throws InvalidArgumentsException 
	 * @throws StationOfflineException 
	 * @throws NoOngoingRideException 
	 * @throws NoSlotAvailableException 
	 */
	public void returnBike(String[] args) throws InexistingNetworkNameException, InexistingUserIdException, InexistingStationIdException, NegativeTimeException, NullDateException, InvalidArgumentsException, NoSlotAvailableException, NoOngoingRideException, StationOfflineException {
		if (args.length == 4) {
			int userID = Integer.parseInt(args[0]);
			int stationID = Integer.parseInt(args[1]);
			LocalDateTime time = Date.dateInput(args[2]);
			Network net = nm.findNetworkByName(args[3]);
			nm.returnBike(userID, stationID, time, net);
		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	/**
	 * This method applies command line instructions of the form: <br>
	 * {@code rentBike <userID> <stationID> <bikeType> <time> <networkName>} <br>
	 * The {@code time} has to be on the following format: {@code YYYY-MM-dd HH:mm}
	 * The type has to be {@code electric} or {@code mechanic}. The case is ignored. The type is optional, if it is not written, any bike will be rented.
	 * @param args an array of {@code String}
	 * @throws InexistingNetworkNameException 
	 * @throws NegativeTimeException 
	 * @throws InexistingStationIdException 
	 * @throws InexistingUserIdException 
	 * @throws InvalidArgumentsException 
	 * @throws StationOfflineException 
	 * @throws NoBikeAvailableException 
	 * @throws OngoingRideException 
	 */
	public void rentBike(String[] args) throws InexistingNetworkNameException, InexistingUserIdException, InexistingStationIdException, NegativeTimeException, InvalidArgumentsException, OngoingRideException, NoBikeAvailableException, StationOfflineException {
		if (args.length == 4) {
			int userID = Integer.parseInt(args[0]);
			int stationID = Integer.parseInt(args[1]);
			LocalDateTime time = Date.dateInput(args[2]);
			Network net = nm.findNetworkByName(args[3]);
			nm.rentBike(userID, stationID, time, net);
		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	/**
	 * This method applies command line instructions of the form: <br>
	 * {@code displayStation <networkName> <stationID>}
	 * @param args an array of {@code String}
	 * @throws InexistingNetworkNameException
	 * @throws InexistingStationIdException
	 * @throws InvalidArgumentsException
	 */
	public void displayStation(String[] args) throws InexistingNetworkNameException, InexistingStationIdException, InvalidArgumentsException {
		if (args.length == 2) {
			Network net = nm.findNetworkByName(args[0]);
			int id = Integer.parseInt(args[1]);
			Station s = nm.findStationByID(id, net);
			cld.display(s);
		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	/**
	 * This method applies command line instructions of the form: <br>
	 * {@code displayUser <networkName> <userID>}
	 * @param args an array of {@code String}
	 * @throws InexistingNetworkNameException
	 * @throws InexistingUserIdException
	 * @throws InvalidArgumentsException
	 */
	public void displayUser(String[] args) throws InexistingNetworkNameException, InexistingUserIdException, InvalidArgumentsException {
		if (args.length == 2) {
			Network net = nm.findNetworkByName(args[0]);
			int id = Integer.parseInt(args[1]);
			User u = nm.findUserById(id, net);
			cld.display(u);
		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	/**
	 * This method applies command line instructions of the form: <br>
	 * {@code display <networkName>}
	 * @param args an array of {@code String}
	 * @throws InexistingNetworkNameException
	 * @throws InvalidArgumentsException
	 */
	public void display(String[] args) throws InexistingNetworkNameException, InvalidArgumentsException {
		if (args.length == 1) {
			Network net = nm.findNetworkByName(args[0]);
			cld.display(net);
		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	/**
	 * This method applies command line instructions of the form: <br>
	 * {@code sortStation <networkName> <more-used>} <br>
	 * or <br>
	 * {@code sortStation <networkName> <least-occupied> <startTime> <endTime>} <br>
	 * {@code startTime} and {@code endTime} have to be on the following format: {@code YYYY-MM-dd HH:mm}
	 * @param args an array of {@code String}
	 * @throws InexistingNetworkNameException
	 * @throws InvalidArgumentsException
	 */
	public void sortStation(String[] args) throws InexistingNetworkNameException, InvalidArgumentsException {
		if (args.length == 2) {
			Network net = nm.findNetworkByName(args[0]);
			if (args[1].equalsIgnoreCase("more-used")) {
				cld.displaySortedStations(net, new MoreUsedStation());
			} else {
				throw new InvalidArgumentsException();
			}
		} else if (args.length == 4) {
			Network net = nm.findNetworkByName(args[0]);
			LocalDateTime startTime = Date.dateInput(args[2]);
			LocalDateTime endTime = Date.dateInput(args[3]);
			if (args[1].equalsIgnoreCase("least-occupied")) {
				cld.displaySortedStations(net, new LeastOccupiedStation(startTime, endTime));
			} else {
				throw new InvalidArgumentsException();
			}

		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	/**
	 * This method applies command line instructions of the form: <br>
	 * {@code calculateItinerary <networkName> <userID> <startX> <startY> <destinationX> <destinationY> <pathStrategy>} <br>
	 * The {@code pathStrategy} can be {@code minimal-walking}, {@code fastest-path}, {@code prefer-plus}, {@code avoid-plus}, or {@code uniformity}.
	 * @throws InexistingUserIdException
	 * @throws InvalidArgumentsException
	 * @throws InexistingNetworkNameException 
	 */
	public void calculateItinerary(String[] args) throws InexistingUserIdException, InvalidArgumentsException, InexistingNetworkNameException {
		if (args.length == 7) {
			Network net = nm.findNetworkByName(args[0]);
			int id = Integer.parseInt(args[1]);
			double startX = Double.parseDouble(args[2]);
			double startY = Double.parseDouble(args[3]);
			double destX = Double.parseDouble(args[4]);
			double destY = Double.parseDouble(args[5]);
			Point start = new Point(startX, startY);
			Point destination = new Point(destX, destY);
			User u = nm.findUserById(id, net);
			Itinerary it;
			if (args[6].equalsIgnoreCase("minimal-walking")) {
				it = u.calculateItinerary(start, destination, new MinimalWalkingStrategy(net));
			} else if (args[6].equalsIgnoreCase("fastest-path")) {
				it = u.calculateItinerary(start, destination, new FastestPathStrategy(net));
			} else if (args[6].equalsIgnoreCase("prefer-plus")) {
				it = u.calculateItinerary(start, destination, new PreferPlusStrategy(net));
			} else if (args[6].equalsIgnoreCase("avoid-plus")) {
				it = u.calculateItinerary(start, destination, new AvoidPlusStrategy(net));
			} else if (args[6].equalsIgnoreCase("uniformity")) {
				it = u.calculateItinerary(start, destination, new UniformityStrategy(net));
			} else {
				throw new InvalidArgumentsException();
			}
			cld.display("Pickup station:");
			cld.display(it.getStartStation());
			cld.display("Return station:");
			cld.display(it.getEndStation());
			String s = clr.readCommand("Do you want to follow this itinerary? [y/n]");
			if (s.trim().equalsIgnoreCase("y")) {
				u.setItinerary(it);
			}
		} else {
			throw new InvalidArgumentsException();
		}
	}

}
