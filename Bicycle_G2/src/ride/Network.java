package ride;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import sorting.station.SortingStrategy;
import station.Station;
import java.util.Collections;
import java.util.Comparator;

import card.Card;

public class Network implements Serializable {
	
	/**
	 * Generated by Papyrus
	 */
	private static final long serialVersionUID = -3127825538872149011L;
	

	// private static Network network = null;
	private ArrayList<Station> stations;
	private ArrayList<Card> cards;
	private ArrayList<Ride> rideHistory;
	private String name;
	
	public Network() {
		this.stations = new ArrayList<Station>();
		this.rideHistory = new ArrayList<Ride>();
		this.cards = new ArrayList<Card>();
	}
	
	public Network(String name) {
		this.stations = new ArrayList<Station>();
		this.rideHistory = new ArrayList<Ride>();
		this.cards = new ArrayList<Card>();
		this.name = name;
	}
	
	/*
	
	public static synchronized Network getNetwork() {
		if (network == null) { network = new Network(); }
		return network;
	}
	
	public Object readResolve() throws ObjectStreamException { return network; }
	
	*/
	
	public ArrayList<Station> getStations() { return stations; }
	
	public String getName() { return this.name; }
	
	public void addStation(Station station) {
		this.stations.add(station);
	}
	
	public void removeStation(Station station) {
		for (Station s : this.stations) {
			if (s.equals(station)) {
				this.stations.remove(s);
			}
		}
	}
	
	public void addCard(Card card) {
		this.cards.add(card);
	}
	
	public void removeCard(Card card) {
		this.cards.remove(card);
	}

	public ArrayList<Station> sortingStations (SortingStrategy s) {
		ArrayList<Station> sortedStations = (ArrayList<Station>) this.stations.clone();		
		return s.sorting(sortedStations);
	}
	
	public void archiveRide(Ride r) {
		this.rideHistory.add(r);
	}
}
