package station;

import java.util.ArrayList;

import point.Point;

/**
 * An abstract class to represent the stations
 *
 */
public abstract class Station {
	
	private double id;
	private Point p;
	private boolean isOnline;
	private ArrayList<Slot> parkingSlots;
	private int NumberSlots;
	
	private double totalRents;
	private double totalReturns;
	
	// constructeur crée une station avec lieu, id se fait automatiquement, elle est online et on lui crée un nombre de slots
	// comment relier le nombre de slots et la taille de l'array ?
	//les slots sont pas associés à un type de vélo si? 

}
