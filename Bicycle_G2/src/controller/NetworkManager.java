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
	
	/**
	 * 
	 * @param N ({@code int}) the number of points
	 * @param s ({@code double} the side of the square
	 * @return an array of {@code Point} where the points are equally distributed on the area
	 */
	private Point[] getPointDistribution(int N, double s) {
		// TODO Write this method
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
