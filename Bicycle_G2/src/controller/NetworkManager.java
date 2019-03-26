/**
 * 
 */
package controller;

import ride.Network;
import station.StandardStation;
import station.Station;
import tools.Point;

/**
 * This class 
 * @author Pietro Dellino
 *
 */
public class NetworkManager {
	
	public Network setupNetwork(String name) {
		Network net = new Network(name);
		for (int i = 0; i < 10; i++) {
			Station s = new StandardStation(null);
			net.addStation(s);
		}
		return net;
	}
	
	public void setupNetwork(String name, int nStation, int nSlot, double s, int nBikes) {}
	
	private Point[] getPointDistribution(int N, double s) {
		// TODO Write this method
		return null;
	}

}
