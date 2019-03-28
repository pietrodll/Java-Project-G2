package ui.clui;

import bike.ElectricBike;
import card.Card;
import station.Slot;
import station.StandardStation;
import station.Station;
import user.User;

public class CommandLineDisplay {
	
	public String display(Station s) {
		String disp = "";
		disp += "Station: id:" + s.getId() + '\n';
		float x = (float) (Math.round(s.getP().getX()*1000)/1000.0);
		float y = (float) (Math.round(s.getP().getY()*1000)/1000.0);
		disp += '\t' + "Position: x=" + x + " y=" + y + '\n';
		disp += "Slots: " + s.getParkingSlots().size() + '\n';
		for (Slot slot : s.getParkingSlots()) {
			disp += '\t' + "id:" + slot.getId() + '\t' + "Occupied:" + slot.getisOccupied();
			if (slot.getisOccupied()) {
				disp += '\t' + "Bike id:" + slot.getBike().getId();
				String bt = slot.getBike() instanceof ElectricBike ? "Electric" : "Mechanic";
				disp += '\t' + "Type: " + bt;
			}
			disp += '\n';
		}
		System.out.println(disp);
		return disp;
	}
	
	public String display(Card c) {
		String disp = "";
		disp += "Card: id:" + c.getId() + '\n';
		disp += '\t' + "Owner: " + c.getUser().getUserName() + '\n';
		disp += '\t' + "Type: " + ()
		return disp;
	}
	
	public void display(User u) {
		
	}
	
	public void display() {
		
	}
	
}
