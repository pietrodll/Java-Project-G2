package station;

import user.UserIDGenerator;

/**
 * A class representing parking slots
 *
 */
public class Slot {
	
	public static final int EMPTY = 0;
	public static final int ELECTRIC = 1;
	public static final int MECHANIC = 2;

	private Station s;
	private double id;
	private boolean isOnline;
	private int bikeType = Slot.EMPTY;
	
	//faire ID générator qui dépend de la station les trois derniers chiffres sont id du slot et l'ID de la station fois 1000

	
	
	public Slot(Station s) {
		this.s = s;
		id = s.getId()*1000 + SlotIDGenerator.getInstance().getNextSlotID();
		isOnline = false;
		
	}
	
	
}
