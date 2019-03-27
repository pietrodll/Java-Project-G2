/**
 * 
 */
package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import bike.Bike;
import bike.BikeFactory;
import ride.Network;
import station.Slot;
import station.Station;
import station.StationFactory;
import station.StationSamePositionException;
import station.TypeStationException;
import tools.NegativeTimeException;
import tools.Point;

/**
 * This class provides the methods to set up a network and manage it, which is useful in the CLUI.
 * @author Pietro Dellino
 *
 */
public class NetworkManager {
	
	public static final LocalDateTime CREATION_DATE = LocalDateTime.of(2000, 1, 1, 0, 0);
	private ArrayList<Network> networks;
	
	public NetworkManager() { this.networks = new ArrayList<Network>(); }
	
	/**
	 * This method returns a {@code Network} object with 10 stations placed in a square area of size 4km, each station has 10 slots and the occupation rate of the network is 75%.
	 * @param name ({@code String}) The name of the network
	 * @return a {@code Network}
	 * @see #setupNetwork(String, int, int, double, int)
	 */
	public Network setupNetwork(String name) {
		Network net = this.setupNetwork(name, 10, 10, 4, 75);
		return net;
	}
	
	/**
	 * This method sets up a network according to the parameters. The stations are placed according to {@code getPointDistribution}. 
	 * @param name The name of the network
	 * @param nStation The number of stations
	 * @param nSlot The number of slots of each station
	 * @param s The side of the square area to place the stations
	 * @param nBikes The total number of bikes
	 * @return a network
	 * @see #getPointDistribution(int, double)
	 */
	public Network setupNetwork(String name, int nStation, int nSlot, double s, int nBikes) {
		Network net = new Network(name);
		Random rand = new Random();
		StationFactory sf = new StationFactory(net);
		Point[] points = this.getPointDistribution(nStation, s);
		for (int i = 0; i < nStation; i++) {
			Station station = null;
			try {
				station = sf.createStation("Standard", points[i]);
			} catch (TypeStationException e) {
				e.printStackTrace();
			} catch (StationSamePositionException e) {
				System.out.println("According to the point distribution function, this should not happen");
				e.printStackTrace();
			}
			station.addSlot(nSlot);
			net.addStation(station);
		}
		for (int i = 0; i < nBikes; i++) {
			Station randomStation = net.getStations().get(rand.nextInt(nStation));
			if (i < 0.3*nBikes) {
				this.addBike(randomStation, BikeFactory.ELECTRIC, CREATION_DATE);
			} else {
				this.addBike(randomStation, BikeFactory.MECHANIC, CREATION_DATE);
			}
		}
		return net;
	}
	
	/**
	 * If the station is not full, this method creates a bike and adds it to the station
	 * @param s
	 * @param bikeType The bike type constant. Has to be taken from {@link BikeFactory}.
	 * @param changeTime The {@code LocalDateTime} object representing the moment when the bike is added to the station
	 */
	private void addBike(Station s, int bikeType, LocalDateTime changeTime) {
		if (!s.isStationFull()) {
			for (Slot slot : s.getParkingSlots()) {
				if (slot.getisOccupied()) {
					try {
						Bike b = BikeFactory.createBike(bikeType);
						slot.setBike(b, changeTime);
					} catch (NegativeTimeException e) {
						System.out.println("This shouldn't happen");
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * This functions returns an array of points in a square of side {@code s}. The area is divided in enough little squares to place all the points, and then each point is placed randomly on a little square. That ensures that each point is at a different place.
	 * @param N ({@code int}) the number of points
	 * @param s ({@code double}) the side of the square
	 * @return an array of {@code Point} where the points are equally distributed on the area
	 */
	private Point[] getPointDistribution(int N, double s) {
		int nSquares = 2;
		Point[] points = new Point[N];
		while (nSquares < N) { nSquares = nSquares*nSquares; }
		double side = s/nSquares;
		double x, y;
		int n = 0, i = 0, j = 0;
		while (i < nSquares && n < N) {
			while (j < nSquares && n < N) {
				x = i*side + Math.random()*side;
				y = j*side + Math.random()*side;
				points[n] = new Point(x, y);
				n = nSquares*i + j;
				i++; j++;
			}
		}
		return points;
	}

}
