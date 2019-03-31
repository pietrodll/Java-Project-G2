package ride;


import java.io.Serializable;
import java.util.ArrayList;
import sorting.station.SortingStrategy;
import station.Station;


import card.Card;

public class Network implements Serializable {
	
	/**
	 * Generated by Papyrus
	 */
	private static final long serialVersionUID = -3127825538872149011L;
	
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
	
	
	public ArrayList<Station> getStations() { return stations; }
	public ArrayList<Ride> getRideHistory() { return rideHistory; }
	public ArrayList<Card> getCards() { return cards; }

	
	public String getName() { return this.name; }
	
	public void addStation(Station station) {
		this.stations.add(station);
	}
	
	public void removeStation(Station station) {
		this.stations.remove(station);
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
		System.out.println("44");
		this.rideHistory.add(r);
		System.out.println("55");
	}
}
